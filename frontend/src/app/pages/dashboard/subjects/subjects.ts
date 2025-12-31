import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'subjects',
  templateUrl: './subjects.html',
  styleUrls: ['./subjects.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule
  ]
})
export class Subjects {
  subjects = [
    { name: 'Mathematics', ects: 6, attempts: 2, professor: 'Dr. John Doe' },
    { name: 'Physics', ects: 6, attempts: 1, professor: 'Dr. Jane Smith' },
    { name: 'Computer Science', ects: 6, attempts: 2, professor: 'Dr. Alice Brown' },
    { name: 'Literature', ects: 6, attempts: 1, professor: 'Dr. Bob White' }
  ];

  displayedColumns: string[] = ['name', 'ects', 'attempts', 'professor', 'register'];

  constructor(private dialog: MatDialog) {}

  openRegisterDialog(subject: any): void {
    const dialogRef = this.dialog.open(SubjectDialog, {
      width: '400px',
      data: { subject: subject, examTerm: 'November 2025' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Registered for subject: ' + subject.name);
      }
    });
  }
}

@Component({
  selector: 'subject-dialog',
  template: `
    <h2 mat-dialog-title>Register for {{ data.subject.name }}</h2>
    <mat-dialog-content>
      <p><strong>Exam Term:</strong> {{ data.examTerm }}</p>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onCancel()">Cancel</button>
      <button mat-raised-button color="primary" (click)="onRegister()">Register</button>
    </mat-dialog-actions>
  `,
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatDialogModule,
  ]
})
export class SubjectDialog {
  constructor(
    public dialogRef: MatDialogRef<SubjectDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onRegister(): void {
    this.dialogRef.close(true);
  }
}