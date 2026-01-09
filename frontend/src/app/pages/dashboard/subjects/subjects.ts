import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogModule,
  MAT_DIALOG_DATA,
  MatDialogRef
} from '@angular/material/dialog';

import {
  Subjects as SubjectsService,
  SubjectDTO
} from '../../../services/subjects/subjects';

@Component({
  selector: 'subjects',
  templateUrl: './subjects.html',
  styleUrls: ['./subjects.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule
  ]
})
export class Subjects implements OnInit {
  subjects: SubjectDTO[] = [];

  displayedColumns: string[] = [
    'name',
    'ects',
    'studyProgrammeName',
    'yearNumber',
    'mandatory',
    'register'
  ];

  constructor(
    private subjectsService: SubjectsService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadSubjects();
  }

  loadSubjects(): void {
    this.subjectsService.getMySubjects().subscribe({
      next: (data: SubjectDTO[]) => {
        this.subjects = data;
      },
      error: (err: unknown) => {
        console.error('Error loading subjects', err);
      }
    });
  }

  openRegisterDialog(subject: SubjectDTO): void {
    this.dialog.open(SubjectDialog, {
      width: '400px',
      data: {
        subject,
        examTerm: 'November 2025'
      }
    });
  }
}

@Component({
  selector: 'subject-dialog',
  template: `
    <h2 mat-dialog-title>Register for {{ data.subject.name }}</h2>

    <mat-dialog-content>
      <p><strong>Programme:</strong> {{ data.subject.studyProgrammeName }}</p>
      <p><strong>Year:</strong> {{ data.subject.yearNumber }}</p>
      <p><strong>ECTS:</strong> {{ data.subject.ects }}</p>
      <p><strong>Exam term:</strong> {{ data.examTerm }}</p>
    </mat-dialog-content>
  `,
  standalone: true,
  imports: [MatDialogModule, MatButtonModule]
})
export class SubjectDialog {
  constructor(
    public dialogRef: MatDialogRef<SubjectDialog>,
    @Inject(MAT_DIALOG_DATA) public data: {
      subject: SubjectDTO;
      examTerm: string;
    }
  ) {}

  close(): void {
    this.dialogRef.close();
  }

  register(): void {
    this.dialogRef.close(true);
  }
}
