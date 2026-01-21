import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { GradingService, ProfessorCourseDTO } from '../../../services/grading/grading';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './courses.html',
  styleUrl: './courses.css',
})
export class Courses implements OnInit {
  courses: ProfessorCourseDTO[] = [];
  displayedColumns: string[] = ['subjectName', 'studyProgrammeName', 'yearNumber', 'ects', 'teachingTypeName', 'numberOfClasses'];
  isLoading: boolean = false;
  error: string | null = null;

  constructor(private gradingService: GradingService) {}

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.isLoading = true;
    this.error = null;

    this.gradingService.getMyCourses().subscribe({
      next: (data) => {
        this.courses = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading courses:', err);
        this.error = 'Failed to load courses';
        this.isLoading = false;
      }
    });
  }
}
