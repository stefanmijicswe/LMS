import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface StudentActivityDTO {
  id: number;
  subjectName: string;
  points: number;
  maxPoints: number;
  startTime: string;
  endTime: string;
  evaluationTypeName: string;
  examinationPeriodName: string;
  knowledgeEvaluationId: number;
}

@Injectable({
  providedIn: 'root'
})
export class Activities {
  private apiUrl = 'http://localhost:8081/api/students/me/activities';

  constructor(private http: HttpClient) {}

  getMyActivities(): Observable<StudentActivityDTO[]> {
    return this.http.get<StudentActivityDTO[]>(this.apiUrl);
  }
}
