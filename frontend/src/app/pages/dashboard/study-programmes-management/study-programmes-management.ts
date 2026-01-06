import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-study-programmes-management',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './study-programmes-management.html',
  styleUrls: ['./study-programmes-management.css']
})
export class StudyProgrammesManagement implements OnInit {

  studyProgramme: any;
  isEditing = false;

  tableColumns: string[] = ['name', 'description', 'facultyId', 'actions'];

  private apiUrl = 'http://localhost:8081/api/admin/study-programmes/1';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadStudyProgramme();
  }

  private getAuthHeaders() {
    const token = localStorage.getItem('token');
    return { Authorization: `Bearer ${token}` };
  }

  loadStudyProgramme(): void {
    this.http.get(this.apiUrl, { headers: this.getAuthHeaders() })
      .subscribe(data => this.studyProgramme = data);
  }

  edit(): void {
    this.isEditing = true;
  }

  save(): void {
    this.http.put(this.apiUrl, this.studyProgramme, { headers: this.getAuthHeaders() })
      .subscribe(() => this.isEditing = false);
  }
}
