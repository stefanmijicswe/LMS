import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ProfessorCourseDTO {
  id: number;
  subjectName: string;
  ects: number;
  subjectRealisationId: number;
  studyProgrammeName: string;
  yearNumber: number;
  teachingTypeName: string;
  numberOfClasses: number;
}

export interface CourseStudentDTO {
  examinationId: number;
  studentId: number;
  studentName: string;
  studentSurname: string;
  studentPin: string;
  points: number | null;
  maxPoints: number;
  note: string | null;
  subjectName: string;
  examinationPeriodName: string;
  evaluationTypeName: string;
}

export interface GradeStudentRequest {
  points: number | null;
  note: string | null;
}

export interface ExaminationDTO {
  id: number;
  points: number;
  note: string | null;
  studentOnYearId: number;
  knowledgeEvaluationId: number;
}

@Injectable({
  providedIn: 'root',
})
export class GradingService {
  private apiUrl = 'http://localhost:8081/api/professors';

  constructor(private http: HttpClient) {}

  getMyCourses(): Observable<ProfessorCourseDTO[]> {
    return this.http.get<ProfessorCourseDTO[]>(`${this.apiUrl}/me/courses`);
  }

  getCourseStudents(subjectRealisationId: number): Observable<CourseStudentDTO[]> {
    return this.http.get<CourseStudentDTO[]>(`${this.apiUrl}/courses/${subjectRealisationId}/students`);
  }

  gradeStudent(examinationId: number, request: GradeStudentRequest): Observable<ExaminationDTO> {
    return this.http.put<ExaminationDTO>(`${this.apiUrl}/examinations/${examinationId}/grade`, request);
  }
}
