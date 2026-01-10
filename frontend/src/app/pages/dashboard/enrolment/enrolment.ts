import { Component, OnInit, OnDestroy, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import {
  MatDialogModule,
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA
} from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { EnrolmentService, UserDTO, EnrolStudentRequest } from '../../../services/enrolment/enrolment';
import { Subject, debounceTime, distinctUntilChanged, takeUntil } from 'rxjs';

@Component({
  selector: 'app-enrolment',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './enrolment.html',
  styleUrls: ['./enrolment.css'],
})
export class Enrolment implements OnInit, OnDestroy {

  displayedColumns: string[] = ['username', 'actions'];
  dataSource = new MatTableDataSource<UserDTO>([]);
  searchTerm: string = '';
  isLoading: boolean = false;
  private searchSubject = new Subject<string>();
  private destroy$ = new Subject<void>();

  constructor(
    private dialog: MatDialog,
    private enrolmentService: EnrolmentService
  ) {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntil(this.destroy$)
    ).subscribe(term => {
      this.searchUsers(term);
    });
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
    this.searchSubject.complete();
  }

  onSearchChange(event: Event) {
    const value = (event.target as HTMLInputElement).value;
    this.searchTerm = value;
    if (value.trim().length > 0) {
      this.searchSubject.next(value);
    } else {
      this.dataSource.data = [];
      this.isLoading = false;
  }
  }

  searchUsers(username: string) {
    if (!username || username.trim().length === 0) {
      this.dataSource.data = [];
      this.isLoading = false;
      return;
    }

    this.isLoading = true;
    this.enrolmentService.getUsersWithOnlyRoleUser(username.trim()).subscribe({
      next: (users) => {
        this.dataSource.data = users;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error fetching users:', error);
        this.isLoading = false;
        this.dataSource.data = [];
      }
    });
  }

  enrol(user: UserDTO) {
    this.dialog.open(EnrolDialog, {
      width: '500px',
      data: user
    }).afterClosed().subscribe(() => {
      if (this.searchTerm && this.searchTerm.trim().length > 0) {
        this.searchUsers(this.searchTerm);
      }
    });
  }
}

@Component({
  selector: 'enrol-dialog',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatSelectModule
  ],
  template: `
    <h2 mat-dialog-title>Enrol Student</h2>

    <mat-dialog-content>
      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Username</mat-label>
        <input matInput [(ngModel)]="form.username" disabled>
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Name</mat-label>
        <input matInput [(ngModel)]="form.name" required>
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Surname</mat-label>
        <input matInput [(ngModel)]="form.surname" required>
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>PIN</mat-label>
        <input matInput [(ngModel)]="form.pin" required>
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Year of Study ID</mat-label>
        <input matInput type="number" [(ngModel)]="form.yearOfStudyId" required>
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Address ID</mat-label>
        <input matInput type="number" [(ngModel)]="form.addressId" required>
      </mat-form-field>
    </mat-dialog-content>

    <mat-dialog-actions align="end">
      <button mat-button (click)="close()">Cancel</button>
      <button mat-raised-button color="primary" (click)="submit()" [disabled]="!isFormValid() || isSubmitting">
        {{ isSubmitting ? 'Enrolling...' : 'Enrol' }}
      </button>
    </mat-dialog-actions>
  `
})
export class EnrolDialog implements OnInit {

  form: EnrolStudentRequest = {
    username: '',
    name: '',
    surname: '',
    pin: '',
    yearOfStudyId: 0,
    addressId: 0
  };

  isSubmitting: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: UserDTO,
    private dialogRef: MatDialogRef<EnrolDialog>,
    private enrolmentService: EnrolmentService
  ) {
    if (data) {
      this.form.username = data.username;
    }
  }

  ngOnInit() {
  }

  isFormValid(): boolean {
    return !!(this.form.username && 
              this.form.name && 
              this.form.surname && 
              this.form.pin && 
              this.form.yearOfStudyId > 0 && 
              this.form.addressId > 0);
  }

  close() {
    this.dialogRef.close();
  }

  submit() {
    if (!this.isFormValid() || this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;
    this.enrolmentService.enrolStudent(this.form).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: () => {
        this.isSubmitting = false;
      }
    });
  }
}
