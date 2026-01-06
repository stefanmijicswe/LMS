import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { SubjectRealisations as SubjectRealisationsService } from '../../../services/subject-realisations/subject-realisations';
import { StudyProgrammes, StudyProgrammeDTO, SubjectBasicDTO } from '../../../services/study-programmes/study-programmes';

@Component({
  selector: 'app-subject-realisations',
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
  templateUrl: './subject-realisations.html',
  styleUrl: './subject-realisations.css',
})
export class SubjectRealisations implements OnInit {
  private subjectRealisationsService = inject(SubjectRealisationsService);
  private studyProgrammesService = inject(StudyProgrammes);

  studyProgrammes: StudyProgrammeDTO[] = [];
  subjects: SubjectBasicDTO[] = [];
  
  form = {
    studyProgrammeId: null as number | null,
    subjectId: null as number | null
  };

  isLoading = false;

  ngOnInit() {
    this.loadStudyProgrammes();
  }

  loadStudyProgrammes() {
    this.studyProgrammesService.getAllStudyProgrammes().subscribe({
      next: (result) => {
        this.studyProgrammes = result;
      },
      error: (error) => {
        console.error('Failed to load study programmes:', error);
        alert('Failed to load study programmes');
      }
    });
  }

  onStudyProgrammeChange() {
    if (this.form.studyProgrammeId) {
      this.isLoading = true;
      this.subjects = [];
      this.form.subjectId = null;

      this.studyProgrammesService.getStudyProgrammeById(this.form.studyProgrammeId).subscribe({
        next: (result) => {
          this.subjects = result.subjects || [];
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Failed to load subjects:', error);
          alert('Failed to load subjects');
          this.isLoading = false;
        }
      });
    } else {
      this.subjects = [];
      this.form.subjectId = null;
    }
  }

  createSubjectRealisation() {
    if (!this.form.subjectId) {
      alert('Subject is required');
      return;
    }

    this.isLoading = true;

    const dto = {
      subjectId: this.form.subjectId!
    };

    this.subjectRealisationsService.createSubjectRealisation(dto).subscribe({
      next: (result) => {
        alert(`Subject Realisation created successfully! ID: ${result.id}`);
        this.resetForm();
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        
        if (error.status === 403) {
          alert('Access denied. Please check your permissions.');
        } else if (error.status === 401) {
          alert('Session expired. Please login again.');
        } else {
          const errorMessage = error.error?.message || 'Failed to create subject realisation';
          alert(errorMessage);
        }
      }
    });
  }

  resetForm() {
    this.form = {
      studyProgrammeId: null,
      subjectId: null
    };
    this.subjects = [];
  }
}
