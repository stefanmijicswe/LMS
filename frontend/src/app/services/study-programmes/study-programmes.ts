import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface StudyProgrammeDTO {
  id: number;
  name: string;
  description?: string;
  facultyId?: number;
  subjects?: SubjectBasicDTO[];
}

export interface SubjectBasicDTO {
  id: number;
  name: string;
  ects: number;
  mandatory: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class StudyProgrammes {
  private apiUrl = 'http://localhost:8081/public';

  constructor(private http: HttpClient) {}

  getAllStudyProgrammes(): Observable<StudyProgrammeDTO[]> {
    return this.http.get<StudyProgrammeDTO[]>(`${this.apiUrl}/study-programmes`);
  }

  getStudyProgrammeById(id: number): Observable<StudyProgrammeDTO> {
    return this.http.get<StudyProgrammeDTO>(`${this.apiUrl}/study-programmes/${id}`);
  }

}

