import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface CreateTeacherAssignmentDTO {
  teacherId: number;
  subjectRealisationId: number;
  teachingTypeId: number;
  numberOfClasses: number;
}

export interface TeachingTypeDTO {
  id: number;
  name: string;
}

export interface TeacherAssignmentDTO {
  id: number;
  teacherId: number;
  subjectRealisationId: number;
  teachingTypeId: number;
  numberOfClasses: number;
}

export interface TeacherDTO {
  id: number;
  name: string;
  surname: string;
  biography: string;
  userId: number;
}

export interface SubjectRealisationDTO {
  id: number;
  subjectId: number;
  subjectName: string;
}

@Injectable({
  providedIn: 'root',
})
export class TeacherAssignment {
  private apiUrl = 'http://localhost:8081/api/admin/teacher-in-courses';

  constructor(private http: HttpClient) {}

  assignTeacher(dto: CreateTeacherAssignmentDTO): Observable<TeacherAssignmentDTO> {
    return this.http.post<TeacherAssignmentDTO>(this.apiUrl, dto);
  }

  getTeacherAssignmentById(id: number): Observable<TeacherAssignmentDTO> {
    return this.http.get<TeacherAssignmentDTO>(`${this.apiUrl}/${id}`);
  }

  getAllTeachers(): Observable<TeacherDTO[]> {
    return this.http.get<TeacherDTO[]>('http://localhost:8081/api/admin/teachers');
  }

  getAllSubjectRealisations(studyProgrammeId?: number): Observable<SubjectRealisationDTO[]> {
    const params = studyProgrammeId ? `?studyProgrammeId=${studyProgrammeId}` : '';
    return this.http.get<SubjectRealisationDTO[]>(
      `http://localhost:8081/api/admin/subject-realisations${params}`
    );
  }

  getAllTeachingTypes(): Observable<TeachingTypeDTO[]> {
    return this.http.get<TeachingTypeDTO[]>('http://localhost:8081/api/admin/teaching-types');
  }
}
