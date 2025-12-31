import { Component, OnInit, Inject } from '@angular/core';
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

import { StudyProgrammes } from '../../../services/study-programmes/study-programmes';

interface EnrolmentRecord {
  email: string;
  firstName: string;
  lastName: string;
}

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
    MatSelectModule
  ],
  templateUrl: './enrolment.html',
  styleUrls: ['./enrolment.css'],
})
export class Enrolment implements OnInit {

  displayedColumns: string[] = ['email', 'firstName', 'lastName', 'actions'];
  dataSource = new MatTableDataSource<EnrolmentRecord>([]);

  constructor(private dialog: MatDialog) {}

  ngOnInit() {
    this.dataSource.data = [
      { email: 'dejan@example.rs', firstName: 'Dejan', lastName: 'Marković' },
      { email: 'ana@example.rs', firstName: 'Ana', lastName: 'Živković' },
      { email: 'ivana@example.rs', firstName: 'Ivana', lastName: 'Marković' }
    ];
  }

  applyFilter(event: Event) {
    const value = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = value;
  }

  enrol(row: EnrolmentRecord) {
    this.dialog.open(EnrolDialog, {
      width: '420px',
      data: row
    });
  }

  addStudent() {
    this.dialog.open(EnrolDialog, {
      width: '420px',
      data: null
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
        <mat-label>First Name</mat-label>
        <input matInput [(ngModel)]="form.firstName">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Last Name</mat-label>
        <input matInput [(ngModel)]="form.lastName">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Personal identification number</mat-label>
        <input matInput [(ngModel)]="form.personal">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Email Address</mat-label>
        <input matInput [(ngModel)]="form.email">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Study Programme</mat-label>
        <mat-select [(ngModel)]="form.studyProgrammeId">
          <mat-option *ngFor="let sp of studyProgrammes" [value]="sp.id">
            {{ sp.name }}
          </mat-option>
        </mat-select>
      </mat-form-field>

    </mat-dialog-content>

    <mat-dialog-actions align="end">
      <button mat-button (click)="close()">Cancel</button>
      <button mat-raised-button color="primary" (click)="submit()">Enrol</button>
    </mat-dialog-actions>
  `
})
export class EnrolDialog implements OnInit {

  studyProgrammes: any[] = [];

  form = {
    firstName: '',
    lastName: '',
    personal: '',
    email: '',
    studyProgrammeId: null
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<EnrolDialog>,
    private studyProgrammesService: StudyProgrammes
  ) {
    if (data) {
      this.form.firstName = data.firstName || '';
      this.form.lastName = data.lastName || '';
      this.form.email = data.email || '';
    }
  }

  ngOnInit() {
    this.studyProgrammesService.getAllStudyProgrammes().subscribe({
      next: (result) => {
        this.studyProgrammes = result;
      },
      error: () => {
        console.error("Failed to load study programmes");
      }
    });
  }

  close() {
    this.dialogRef.close();
  }

  submit() {
    console.log("Submitted:", this.form);
    this.dialogRef.close(this.form);
  }
}