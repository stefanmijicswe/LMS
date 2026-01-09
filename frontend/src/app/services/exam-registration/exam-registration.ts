import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface SubjectForRegistration {
  subjectId: number;
  subjectName: string;
  knowledgeEvaluationId: number;
  evaluationType: string;
  points: number;
  isRegistered: boolean;
}

export interface ActiveExaminationPeriod {
  examinationPeriodId: number;
  examinationPeriodName: string;
  startDate: string;
  endDate: string;
  subjects: SubjectForRegistration[];
}

@Injectable({
  providedIn: 'root',
})
export class ExamRegistration {
  private apiUrl = 'http://localhost:8081/api/students';

  constructor(private http: HttpClient) {}

  getActivePeriodsWithSubjects(): Observable<ActiveExaminationPeriod[]> {
    return this.http.get<ActiveExaminationPeriod[]>(`${this.apiUrl}/me/examination-periods/active`).pipe(
      catchError((err) => {
        console.error('Error loading examination periods:', err);
        return of([]);
      })
    );
  }

  registerForExam(knowledgeEvaluationId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/me/register-exam`, {
      knowledgeEvaluationId: knowledgeEvaluationId
    });
  }
}
