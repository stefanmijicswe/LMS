import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Faculties {
  
  private apiUrl = 'http://localhost:8081/public';

  constructor(private http: HttpClient) {}

  getAllFaculties(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/faculties`);
  }
}
