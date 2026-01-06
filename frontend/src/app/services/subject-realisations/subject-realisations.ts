import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface CreateSubjectRealisationDTO {
  subjectId: number;
}

export interface SubjectRealisationDTO {
  id: number;
  subjectId: number;
}

@Injectable({
  providedIn: 'root',
})
export class SubjectRealisations {
  private apiUrl = 'http://localhost:8081/api/admin/subject-realisations';

  constructor(private http: HttpClient) {}

  createSubjectRealisation(dto: CreateSubjectRealisationDTO): Observable<SubjectRealisationDTO> {
    return this.http.post<SubjectRealisationDTO>(this.apiUrl, dto);
  }

  getSubjectRealisationById(id: number): Observable<SubjectRealisationDTO> {
    return this.http.get<SubjectRealisationDTO>(`${this.apiUrl}/${id}`);
  }
}
