import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface StudentDTO {
  id: number;
  name: string;
  surname: string;
  pin: string;
  address?: AddressDTO | null;
}

export interface AddressDTO {
  id: number;
  streetName: string;
  streetNumber: string;
  place?: PlaceDTO | null;
}

export interface PlaceDTO {
  id: number;
  name: string;
  country?: CountryDTO | null;
}

export interface CountryDTO {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root',
})
export class StudentsService {
  private apiUrl = 'http://localhost:8081/api/students';

  constructor(private http: HttpClient) {}

  getAllStudents(): Observable<StudentDTO[]> {
    return this.http.get<StudentDTO[]>(this.apiUrl).pipe(
      catchError((err) => {
        console.error('Error loading students:', err);
        return of([]);
      })
    );
  }

  getStudentById(id: number): Observable<StudentDTO | null> {
    return this.http.get<StudentDTO>(`${this.apiUrl}/${id}`).pipe(
      catchError((err) => {
        console.error('Error loading student details:', err);
        return of(null);
      })
    );
  }
}
