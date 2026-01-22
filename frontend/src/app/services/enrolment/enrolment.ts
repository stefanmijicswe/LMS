import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface UserDTO {
  id: number;
  username: string;
  roles: string[];
  yearNumber?: number;
  studyProgrammeName?: string;
}

export interface EnrolStudentRequest {
  username: string;
  yearOfStudyId: number;
  name: string;
  surname: string;
  pin: string;
  addressId: number;
}

export interface AddressDTO {
  id: number;
  streetName: string;
  streetNumber: string;
  place?: {
    id: number;
    name: string;
    country?: {
      id: number;
      name: string;
    };
  };
}

export interface StudyProgrammeDTO {
  id: number;
  name: string;
}

export interface YearOfStudyOptionDTO {
  id: number;
  yearNumber: number;
}

@Injectable({
  providedIn: 'root',
})
export class EnrolmentService {
  private apiUrl = 'http://localhost:8081/api/enrolment';

  constructor(private http: HttpClient) {}

  getUsersWithOnlyRoleUser(username?: string): Observable<UserDTO[]> {
    let params = new HttpParams();
    if (username && username.trim().length > 0) {
      params = params.set('username', username.trim());
    }
    return this.http.get<UserDTO[]>(`${this.apiUrl}/users`, { params });
  }

  enrolStudent(request: EnrolStudentRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/students`, request);
  }

  getAddresses(): Observable<AddressDTO[]> {
    return this.http.get<AddressDTO[]>('http://localhost:8081/public/addresses');
  }

  getStudyProgrammes(): Observable<StudyProgrammeDTO[]> {
    return this.http.get<StudyProgrammeDTO[]>('http://localhost:8081/public/study-programmes');
  }

  getYearsForStudyProgramme(studyProgrammeId: number): Observable<YearOfStudyOptionDTO[]> {
    return this.http.get<YearOfStudyOptionDTO[]>(`http://localhost:8081/public/study-programmes/${studyProgrammeId}/years`);
  }
}

