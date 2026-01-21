import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CreateKnowledgeEvaluationDTO, KnowledgeEvaluations as KnowledgeEvaluationsService, SubjectRealisationDTO, ExaminationPeriodDTO, EvaluationTypeDTO } from '../../../services/knowledge-evaluations/knowledge-evaluations';

@Component({
  selector: 'app-knowledge-evaluations',
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
  templateUrl: './knowledge-evaluations.html',
  styleUrl: './knowledge-evaluations.css',
})
export class KnowledgeEvaluations implements OnInit {
  private knowledgeEvaluationsService = inject(KnowledgeEvaluationsService);

  form = {
    subjectRealisationId: null as number | null,
    examinationPeriodId: null as number | null,
    evaluationTypeId: null as number | null,
    startTime: '',
    endTime: '',
    points: null as number | null,
  };

  subjectRealisations: SubjectRealisationDTO[] = [];
  examinationPeriods: ExaminationPeriodDTO[] = [];
  evaluationTypes: EvaluationTypeDTO[] = [];
  isLoading = false;

  ngOnInit() {
    this.loadSubjectRealisations();
    this.loadExaminationPeriods();
    this.loadEvaluationTypes();
  }

  loadSubjectRealisations() {
    this.knowledgeEvaluationsService.getAllSubjectRealisations().subscribe({
      next: (data) => {
        this.subjectRealisations = data;
      },
      error: (error) => {
        console.error('Failed to load subject realisations:', error);
      }
    });
  }

  loadExaminationPeriods() {
    this.knowledgeEvaluationsService.getAllExaminationPeriods().subscribe({
      next: (data) => {
        this.examinationPeriods = data;
      },
      error: (error) => {
        console.error('Failed to load examination periods:', error);
      }
    });
  }

  loadEvaluationTypes() {
    this.knowledgeEvaluationsService.getAllEvaluationTypes().subscribe({
      next: (data) => {
        this.evaluationTypes = data;
      },
      error: (error) => {
        console.error('Failed to load evaluation types:', error);
      }
    });
  }

  createKnowledgeEvaluation() {
    if (
      this.form.subjectRealisationId == null ||
      this.form.examinationPeriodId == null ||
      this.form.evaluationTypeId == null ||
      !this.form.startTime ||
      !this.form.endTime ||
      this.form.points == null
    ) {
      alert('All fields are required');
      return;
    }

    const dto: CreateKnowledgeEvaluationDTO = {
      subjectRealisationId: this.form.subjectRealisationId,
      examinationPeriodId: this.form.examinationPeriodId,
      evaluationTypeId: this.form.evaluationTypeId,
      startTime: new Date(this.form.startTime),
      endTime: new Date(this.form.endTime),
      points: this.form.points,
    };

    this.isLoading = true;

    this.knowledgeEvaluationsService.createKnowledgeEvaluation(dto).subscribe({
      next: () => {
        alert('Knowledge evaluation created');
        this.resetForm();
        this.isLoading = false;
      },
      error: (error) => {
        const message = error?.error?.message || 'Failed to create knowledge evaluation';
        alert(message);
        this.isLoading = false;
      },
    });
  }

  resetForm() {
    this.form = {
      subjectRealisationId: null,
      examinationPeriodId: null,
      evaluationTypeId: null,
      startTime: '',
      endTime: '',
      points: null,
    };
  }
}
