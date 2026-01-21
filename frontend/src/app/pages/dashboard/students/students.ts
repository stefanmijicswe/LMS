import { Component, OnInit, OnDestroy, Inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, takeUntil } from 'rxjs/operators';

import { StudentsService, StudentDTO } from '../../../services/students/students';

@Component({
  selector: 'students',
  standalone: true,
  templateUrl: './students.html',
  styleUrls: ['./students.css'],
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatProgressSpinnerModule
  ]
})
export class Students implements OnInit, OnDestroy {
  displayedColumns: string[] = ['id', 'name', 'surname', 'pin', 'details'];
  dataSource = new MatTableDataSource<StudentDTO>([]);
  allStudents: StudentDTO[] = [];
  searchTerm: string = '';
  isLoading: boolean = false;
  error: string | null = null;

  private searchSubject = new Subject<string>();
  private destroy$ = new Subject<void>();

  constructor(
    private dialog: MatDialog,
    private studentsService: StudentsService
  ) {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntil(this.destroy$)
    ).subscribe(term => {
      this.applyFilter(term);
    });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadStudents(searchTerm: string): void {
    if (!searchTerm || searchTerm.trim() === '') {
      this.dataSource.data = [];
      this.allStudents = [];
      return;
    }

    this.isLoading = true;
    this.error = null;

    this.studentsService.getAllStudents().subscribe({
      next: (students) => {
        this.allStudents = students;
        const filterValue = searchTerm.trim().toLowerCase();
        this.dataSource.data = students.filter(student =>
          student.id.toString().includes(filterValue) ||
          student.name.toLowerCase().includes(filterValue) ||
          student.surname.toLowerCase().includes(filterValue) ||
          student.pin.toLowerCase().includes(filterValue)
        );
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading students:', err);
        this.error = 'Failed to load students';
        this.isLoading = false;
      }
    });
  }

  onSearchChange(): void {
    this.searchSubject.next(this.searchTerm);
  }

  applyFilter(term: string): void {
    this.loadStudents(term);
  }

  openDetails(student: StudentDTO): void {
    this.dialog.open(StudentDetailsDialog, {
      width: '600px',
      data: { student: student, service: this.studentsService }
    });
  }
}

@Component({
  selector: 'student-details-dialog',
  standalone: true,
  template: `
    <h2 mat-dialog-title>Student Details</h2>
    
    <mat-dialog-content style="padding: 20px;">
      <div *ngIf="isLoading" style="text-align: center; padding: 40px;">
        <mat-spinner diameter="30"></mat-spinner>
      </div>
      <div *ngIf="!isLoading && studentData">
        <div style="margin-bottom: 15px;">
          <strong>ID:</strong> {{ studentData.id }}
        </div>
        <div style="margin-bottom: 15px;">
          <strong>Name:</strong> {{ studentData.name }}
        </div>
        <div style="margin-bottom: 15px;">
          <strong>Surname:</strong> {{ studentData.surname }}
        </div>
        <div style="margin-bottom: 15px;">
          <strong>PIN:</strong> {{ studentData.pin }}
        </div>
        <div *ngIf="studentData.address" style="margin-top: 20px; padding-top: 15px; border-top: 1px solid #ddd;">
          <div style="margin-bottom: 10px;"><strong>Address:</strong></div>
          <div style="margin-left: 15px; margin-bottom: 5px;">
            {{ studentData.address.streetName }} {{ studentData.address.streetNumber }}
          </div>
          <div *ngIf="studentData.address.place" style="margin-left: 15px; margin-bottom: 5px;">
            {{ studentData.address.place.name }}
            <span *ngIf="studentData.address.place.country">, {{ studentData.address.place.country.name }}</span>
          </div>
        </div>
        <div *ngIf="!studentData.address" style="margin-top: 20px; padding-top: 15px; border-top: 1px solid #ddd;">
          <div>No address information available.</div>
        </div>
      </div>
      <div *ngIf="!isLoading && error" style="color: red; padding: 20px;">
        {{ error }}
      </div>
    </mat-dialog-content>

    <mat-dialog-actions align="end" style="padding: 15px 20px;">
      <button mat-button (click)="closeDialog()">Close</button>
    </mat-dialog-actions>
  `,
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ]
})
export class StudentDetailsDialog implements OnInit {
  studentData: StudentDTO | null = null;
  isLoading: boolean = true;
  error: string | null = null;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { student: StudentDTO, service: StudentsService },
    private dialogRef: MatDialogRef<StudentDetailsDialog>
  ) {}

  ngOnInit(): void {
    this.data.service.getStudentById(this.data.student.id).subscribe({
      next: (detailedStudent) => {
        this.isLoading = false;
        if (detailedStudent) {
          this.studentData = detailedStudent;
        } else {
          this.studentData = this.data.student;
        }
      },
      error: (err) => {
        console.error('Error loading student details:', err);
        this.isLoading = false;
        this.error = 'Failed to load student details';
        this.studentData = this.data.student;
      }
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
