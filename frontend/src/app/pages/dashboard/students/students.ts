import { Component, ViewChild, AfterViewInit, Inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'students',
  standalone: true,
  templateUrl: './students.html',
  styleUrls: ['./students.css'],
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatPaginator,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatExpansionModule,
    MatCardModule,
    MatDialogModule
  ]
})
export class Students implements AfterViewInit {
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'details'];
  dataSource = new MatTableDataSource<Student>([
    { id: 101, firstName: 'John', lastName: 'Doe' },
    { id: 102, firstName: 'Anna', lastName: 'Smith' },
    { id: 103, firstName: 'Mark', lastName: 'Brown' }
  ]);
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private dialog: MatDialog) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: any) {
    this.dataSource.filter = event.target.value.trim().toLowerCase();
  }

  openDetails(student: Student): void {
    this.dialog.open(StudentDetailsDialog, {
      width: '800px',
      data: student
    });
  }
}

interface Student {
  id: number;
  firstName: string;
  lastName: string;
}

@Component({
  selector: 'student-details-dialog',
  standalone: true,
  template: `
    <h2 mat-dialog-title>Student Details</h2>
    
    <mat-dialog-content>
      <mat-accordion>
        <mat-expansion-panel expanded>
          <mat-expansion-panel-header>
            <span>1. Basic Info</span>
          </mat-expansion-panel-header>
          <p><strong>Student Number:</strong> {{ data.id }}</p>
          <p><strong>First Name:</strong> {{ data.firstName }}</p>
          <p><strong>Last Name:</strong> {{ data.lastName }}</p>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>2. Academic Summary</span>
          </mat-expansion-panel-header>
          <p><strong>Average Grade:</strong> 8.5</p>
          <p><strong>ECTS Points:</strong> 180</p>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>3. Enrolments</span>
          </mat-expansion-panel-header>
          <ul>
            <li>Year 1 — Attempt 1</li>
            <li>Year 1 — Attempt 2</li>
            <li>Year 2 — Attempt 1</li>
            <li>Year 3 — Attempt 1</li>
          </ul>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>4. Passed Subjects</span>
          </mat-expansion-panel-header>
          <mat-card class="subject-card">
            <strong>Mathematics</strong><br>Grade: 9 | Points: 7
          </mat-card>
          <mat-card class="subject-card">
            <strong>Physics</strong><br>Grade: 8 | Points: 6
          </mat-card>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>5. Unsuccessful Exams</span>
          </mat-expansion-panel-header>
          <mat-card class="subject-card">
            <strong>Algorithms</strong><br>Grade: 5 | Points: 0
          </mat-card>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>6. Cheating History</span>
          </mat-expansion-panel-header>
          <p>No previous incidents.</p>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>7. Exam Enrollments</span>
          </mat-expansion-panel-header>
          <ul>
            <li>Operating Systems — 2025-01-15</li>
          </ul>
        </mat-expansion-panel>

        <mat-expansion-panel>
          <mat-expansion-panel-header>
            <span>8. Final Thesis</span>
          </mat-expansion-panel-header>
          <p>Machine Learning Applications</p>
        </mat-expansion-panel>
      </mat-accordion>
    </mat-dialog-content>

    <mat-dialog-actions align="end">
      <button mat-button (click)="closeDialog()">Close</button>
    </mat-dialog-actions>
  `,
  imports: [
    CommonModule,
    MatDialogModule,
    MatExpansionModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class StudentDetailsDialog {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Student,
    private dialogRef: MatDialogRef<StudentDetailsDialog>
  ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}