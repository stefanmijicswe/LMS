import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { TeacherAssignment as TeacherAssignmentService, TeacherDTO, SubjectRealisationDTO, TeachingTypeDTO } from '../../../services/teacher-assignment/teacher-assignment';
import { StudyProgrammes, StudyProgrammeDTO } from '../../../services/study-programmes/study-programmes';

@Component({
  selector: 'app-teacher-assignment',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './teacher-assignment.html',
  styleUrl: './teacher-assignment.css',
})
export class TeacherAssignment implements OnInit {
  private teacherAssignmentService = inject(TeacherAssignmentService);
  private studyProgrammesService = inject(StudyProgrammes);

  form = {
    teacherId: null as number | null,
    subjectRealisationId: null as number | null,
    teachingTypeId: null as number | null,
    numberOfClasses: null as number | null
  };

  teachingTypes: TeachingTypeDTO[] = [];
  isLoadingTeachingTypes = false;

  studyProgrammes: StudyProgrammeDTO[] = [];
  selectedStudyProgrammeId: number | null = null;
  isLoadingStudyProgrammes = false;

  teachers: TeacherDTO[] = [];
  subjectRealisations: SubjectRealisationDTO[] = [];
  isLoading = false;
  isLoadingTeachers = false;
  isLoadingSubjectRealisations = false;

  ngOnInit() {
    this.loadTeachers();
    this.loadSubjectRealisations();
    this.loadTeachingTypes();
    this.loadStudyProgrammes();
  }

  loadTeachers() {
    this.isLoadingTeachers = true;
    this.teacherAssignmentService.getAllTeachers().subscribe({
      next: (teachers) => {
        this.teachers = teachers;
        this.isLoadingTeachers = false;
      },
      error: (error) => {
        console.error('Failed to load teachers:', error);
        this.isLoadingTeachers = false;
      }
    });
  }

  loadTeachingTypes() {
    this.isLoadingTeachingTypes = true;
    this.teacherAssignmentService.getAllTeachingTypes().subscribe({
      next: (teachingTypes) => {
        this.teachingTypes = teachingTypes;
        this.isLoadingTeachingTypes = false;
      },
      error: (error) => {
        console.error('Failed to load teaching types:', error);
        this.isLoadingTeachingTypes = false;
      }
    });
  }

  loadSubjectRealisations() {
    this.isLoadingSubjectRealisations = true;
    this.teacherAssignmentService.getAllSubjectRealisations(this.selectedStudyProgrammeId || undefined).subscribe({
      next: (subjectRealisations) => {
        this.subjectRealisations = subjectRealisations;
        this.isLoadingSubjectRealisations = false;
      },
      error: (error) => {
        console.error('Failed to load subject realisations:', error);
        this.isLoadingSubjectRealisations = false;
      }
    });
  }

  loadStudyProgrammes() {
    this.isLoadingStudyProgrammes = true;
    this.studyProgrammesService.getAllStudyProgrammes().subscribe({
      next: (studyProgrammes) => {
        this.studyProgrammes = studyProgrammes;
        this.isLoadingStudyProgrammes = false;
      },
      error: (error) => {
        console.error('Failed to load study programmes:', error);
        this.isLoadingStudyProgrammes = false;
      }
    });
  }

  onStudyProgrammeChange() {
    this.form.subjectRealisationId = null;
    this.loadSubjectRealisations();
  }

  assignTeacher() {
    if (!this.form.teacherId || !this.form.subjectRealisationId || 
        !this.form.teachingTypeId || !this.form.numberOfClasses) {
      alert('All fields are required');
      return;
    }

    this.isLoading = true;

    const dto = {
      teacherId: this.form.teacherId!,
      subjectRealisationId: this.form.subjectRealisationId!,
      teachingTypeId: this.form.teachingTypeId!,
      numberOfClasses: this.form.numberOfClasses!
    };

    this.teacherAssignmentService.assignTeacher(dto).subscribe({
      next: () => {
        alert('Teacher assigned successfully!');
        this.resetForm();
        this.isLoading = false;
      },
      error: (error) => {
        const errorMessage = error.error?.message || 'Failed to assign teacher';
        alert(errorMessage);
        this.isLoading = false;
      }
    });
  }

  resetForm() {
    this.form = {
      teacherId: null,
      subjectRealisationId: null,
      teachingTypeId: null,
      numberOfClasses: null
    };
    this.selectedStudyProgrammeId = null;
    this.loadSubjectRealisations();
  }
}
