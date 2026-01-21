import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface KnowledgeEvaluationDTO {
  id: number;
  startTime: string;
  endTime: string;
  points: number;
  evaluationTypeId: number;
  subjectRealisationId: number;
  examinationPeriodId: number;
}

export interface CreateKnowledgeEvaluationDTO {
  startTime: Date;
  endTime: Date;
  points: number;
  evaluationTypeId: number;
  subjectRealisationId: number;
  examinationPeriodId: number;
}

export interface SubjectRealisationDTO {
  id: number;
  subjectId: number;
  subjectName: string;
}

export interface ExaminationPeriodDTO {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  active: boolean;
}

export interface EvaluationTypeDTO {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root',
})
export class KnowledgeEvaluations {
  private apiUrl = 'http://localhost:8081/api/admin/knowledge-evaluations';

  constructor(private http: HttpClient) {}

  createKnowledgeEvaluation(dto: CreateKnowledgeEvaluationDTO): Observable<KnowledgeEvaluationDTO> {
    return this.http.post<KnowledgeEvaluationDTO>(this.apiUrl, dto);
  }

  getAllSubjectRealisations(): Observable<SubjectRealisationDTO[]> {
    return this.http.get<SubjectRealisationDTO[]>('http://localhost:8081/api/admin/subject-realisations');
  }

  getAllExaminationPeriods(): Observable<ExaminationPeriodDTO[]> {
    return this.http.get<ExaminationPeriodDTO[]>('http://localhost:8081/api/admin/examination-periods');
  }

  getAllEvaluationTypes(): Observable<EvaluationTypeDTO[]> {
    return this.http.get<EvaluationTypeDTO[]>('http://localhost:8081/api/admin/evaluation-types');
  }
}
