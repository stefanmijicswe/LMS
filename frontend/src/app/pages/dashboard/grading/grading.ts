import { Component, OnInit, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { GradingService, ProfessorCourseDTO, CourseStudentDTO, GradeStudentRequest, ExaminationDTO } from '../../../services/grading/grading';

@Component({
  selector: 'grading',
  templateUrl: './grading.html',
  styleUrls: ['./grading.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatProgressSpinnerModule
  ]
})
export class Grading implements OnInit {
  courses: ProfessorCourseDTO[] = [];
  selectedCourseId: number | null = null;
  studentsDataSource = new MatTableDataSource<CourseStudentDTO>([]);
  displayedColumns: string[] = ['studentName', 'subjectName', 'examinationPeriodName', 'evaluationTypeName', 'points', 'maxPoints', 'note', 'action'];
  isLoadingCourses: boolean = false;
  isLoadingStudents: boolean = false;
  error: string | null = null;

  constructor(
    private dialog: MatDialog,
    private gradingService: GradingService
  ) {}

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.isLoadingCourses = true;
    this.error = null;
    this.gradingService.getMyCourses().subscribe({
      next: (data) => {
        this.courses = data;
        this.isLoadingCourses = false;
      },
      error: (err) => {
        console.error('Error loading courses:', err);
        this.error = 'Failed to load courses.';
        this.isLoadingCourses = false;
      }
    });
  }

  onCourseSelected(): void {
    if (this.selectedCourseId) {
      this.loadStudentsForCourse(this.selectedCourseId);
    } else {
      this.studentsDataSource.data = [];
    }
  }

  loadStudentsForCourse(subjectRealisationId: number): void {
    this.isLoadingStudents = true;
    this.error = null;
    this.gradingService.getCourseStudents(subjectRealisationId).subscribe({
      next: (data) => {
        this.studentsDataSource.data = data;
        this.isLoadingStudents = false;
      },
      error: (err) => {
        console.error('Error loading students for course:', err);
        this.error = 'Failed to load students for the selected course.';
        this.isLoadingStudents = false;
      }
    });
  }

  openGradingDialog(student: CourseStudentDTO): void {
    const dialogRef = this.dialog.open(GradingDialog, {
      width: '450px',
      data: { student: student }
    });

    dialogRef.afterClosed().subscribe((result: { points: number | null; note: string | null } | undefined) => {
      if (result) {
        this.gradeStudent(student.examinationId, result.points, result.note);
      }
    });
  }

  gradeStudent(examinationId: number, points: number | null, note: string | null): void {
    const request: GradeStudentRequest = { points, note };
    this.gradingService.gradeStudent(examinationId, request).subscribe({
      next: (updatedExamination: ExaminationDTO) => {
        if (this.selectedCourseId) {
          this.loadStudentsForCourse(this.selectedCourseId);
        }
      },
      error: (err) => {
        console.error('Error grading student:', err);
        alert('Failed to grade student.');
      }
    });
  }
}

@Component({
  selector: 'grading-dialog',
  template: `
    <h2 mat-dialog-title>Grade Student: {{ data.student.studentName }} {{ data.student.studentSurname }}</h2>
    <mat-dialog-content>
      <p>Subject: {{ data.student.subjectName }}</p>
      <p>Evaluation Type: {{ data.student.evaluationTypeName }}</p>
      <p>Max Points: {{ data.student.maxPoints }}</p>
      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Points</mat-label>
        <input matInput type="number" [(ngModel)]="points" [max]="data.student.maxPoints" min="0">
      </mat-form-field>
      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Note</mat-label>
        <textarea matInput [(ngModel)]="note"></textarea>
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onCancel()">Cancel</button>
      <button mat-raised-button color="primary" (click)="onGrade()" [disabled]="!isFormValid()">Grade</button>
    </mat-dialog-actions>
  `,
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class GradingDialog implements OnInit {
  points: number | null = null;
  note: string | null = null;

  constructor(
    public dialogRef: MatDialogRef<GradingDialog>,
    @Inject(MAT_DIALOG_DATA) public data: { student: CourseStudentDTO }
  ) {}

  ngOnInit(): void {
    this.points = this.data.student.points;
    this.note = this.data.student.note;
  }

  isFormValid(): boolean {
    return this.points !== null && this.points >= 0 && this.points <= this.data.student.maxPoints;
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onGrade(): void {
    this.dialogRef.close({ points: this.points, note: this.note });
  }
}
