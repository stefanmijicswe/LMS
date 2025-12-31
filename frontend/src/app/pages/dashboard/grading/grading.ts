import { Component, Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'grading',
  templateUrl: './grading.html',
  styleUrls: ['./grading.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ]
})
export class Grading {
  students = [
    { name: 'John Doe', subject: 'Mathematics', examTerm: 'November 2025' },
    { name: 'Jane Smith', subject: 'Physics', examTerm: 'November 2025' },
    { name: 'Alice Brown', subject: 'Computer Science', examTerm: 'November 2025' },
    { name: 'Bob White', subject: 'Literature', examTerm: 'November 2025' }
  ];

  displayedColumns: string[] = ['name', 'subject', 'examTerm', 'grade'];

  constructor(private dialog: MatDialog) {}

  openGradingDialog(student: any): void {
    const dialogRef = this.dialog.open(GradingDialog, {
      width: '400px',
      data: { student: student }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.students = this.students.filter(s => s !== student);
      }
    });
  }
}

@Component({
  selector: 'grading-dialog',
  template: `
    <h2 mat-dialog-title>Grade for {{ data.student.name }}</h2>
    <mat-dialog-content>
      <div>
        <mat-form-field>
          <mat-label>Midterm 1</mat-label>
          <input matInput [(ngModel)]="midterm1" type="number">
        </mat-form-field>
        <mat-form-field>
          <mat-label>Midterm 2</mat-label>
          <input matInput [(ngModel)]="midterm2" type="number">
        </mat-form-field>
        <mat-form-field>
          <mat-label>Final Exam</mat-label>
          <input matInput [(ngModel)]="finalExam" type="number">
        </mat-form-field>
        <mat-form-field>
          <mat-label>Final Grade</mat-label>
          <input matInput [(ngModel)]="finalGrade" type="number">
        </mat-form-field>
      </div>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onCancel()">Cancel</button>
      <button mat-raised-button color="primary" (click)="onGrade()">Grade</button>
    </mat-dialog-actions>
  `,
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    FormsModule
  ]
})
export class GradingDialog {
  midterm1: number = 0;
  midterm2: number = 0;
  finalExam: number = 0;
  finalGrade: number = 0;

  constructor(
    public dialogRef: MatDialogRef<GradingDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onGrade(): void {
    this.dialogRef.close(true);
  }
}