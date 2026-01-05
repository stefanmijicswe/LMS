import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Subjects {
  private apiUrl = 'http://localhost:8081/public';

  constructor(private http: HttpClient) {}

  getSubjectById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/subjects/${id}`);
  }
}

