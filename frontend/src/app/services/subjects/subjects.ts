import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface SubjectDTO {
  id: number;
  name: string;
  ects: number;
  mandatory: boolean;
  subjectRealisationId: number | null;
  studyProgrammeName: string;
  yearNumber: number;
}

@Injectable({
  providedIn: 'root'
})
export class Subjects {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}

  getMySubjects(): Observable<SubjectDTO[]> {
    return this.http.get<SubjectDTO[]>(
      `${this.baseUrl}/api/students/me/subjects`
    );
  }

  getSubjectById(id: number): Observable<SubjectDTO> {
    return this.http.get<SubjectDTO>(
      `${this.baseUrl}/public/subjects/${id}`
    );
  }
}
