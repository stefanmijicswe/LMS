import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ExaminationPeriodDTO {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  active: boolean;
}

export interface CreateExaminationPeriodDTO {
  name: string;
  startDate: string;
  endDate: string;
  active: boolean;
}

export interface UpdateExaminationPeriodDTO {
  name?: string;
  startDate?: string;
  endDate?: string;
  active?: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class ExaminationPeriodService {
  private apiUrl = 'http://localhost:8081/api/admin/examination-periods';

  constructor(private http: HttpClient) {}

  getAllExaminationPeriods(): Observable<ExaminationPeriodDTO[]> {
    return this.http.get<ExaminationPeriodDTO[]>(this.apiUrl);
  }

  createExaminationPeriod(dto: CreateExaminationPeriodDTO): Observable<ExaminationPeriodDTO> {
    return this.http.post<ExaminationPeriodDTO>(this.apiUrl, dto);
  }

  updateExaminationPeriod(id: number, dto: UpdateExaminationPeriodDTO): Observable<ExaminationPeriodDTO> {
    return this.http.put<ExaminationPeriodDTO>(`${this.apiUrl}/${id}`, dto);
  }
}
