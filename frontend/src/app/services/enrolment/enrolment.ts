import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface UserDTO {
  id: number;
  username: string;
  roles: string[];
}

export interface EnrolStudentRequest {
  username: string;
  yearOfStudyId: number;
  name: string;
  surname: string;
  pin: string;
  addressId: number;
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
}

