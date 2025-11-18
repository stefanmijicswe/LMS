import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class University {
  
  private apiUrl = 'http://localhost:8081/public';

  constructor(private http: HttpClient) {}

  getAllUniversities(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/universities`);
  }
}