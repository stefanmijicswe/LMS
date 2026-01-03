import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-organisation-management',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './organisation-management.html',
  styleUrls: ['./organisation-management.css']
})
export class OrganisationManagement implements OnInit {

  university: any;
  faculty: any;

  isEditingUniversity = false;
  isEditingFaculty = false;

  universityColumns: string[] = [
    'name',
    'description',
    'phoneNumber',
    'officialEmail',
    'website',
    'actions'
  ];

  facultyColumns: string[] = [
    'name',
    'description',
    'phoneNumber',
    'officialEmail',
    'website',
    'actions'
  ];

  private universityApi = 'http://localhost:8081/api/admin/universities/1';
  private facultyApi = 'http://localhost:8081/api/admin/faculties/1';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadUniversity();
    this.loadFaculty();
  }

  private getAuthHeaders() {
    const token = localStorage.getItem('token');
    return {
      Authorization: `Bearer ${token}`
    };
  }

  loadUniversity(): void {
    this.http.get(this.universityApi, {
      headers: this.getAuthHeaders()
    }).subscribe(data => {
      this.university = data;
    });
  }

  editUniversity(): void {
    this.isEditingUniversity = true;
  }

  saveUniversity(): void {
    this.http.put(this.universityApi, this.university, {
      headers: this.getAuthHeaders()
    }).subscribe(() => {
      this.isEditingUniversity = false;
    });
  }

  loadFaculty(): void {
    this.http.get(this.facultyApi, {
      headers: this.getAuthHeaders()
    }).subscribe(data => {
      this.faculty = data;
    });
  }

  editFaculty(): void {
    this.isEditingFaculty = true;
  }

  saveFaculty(): void {
    this.http.put(this.facultyApi, this.faculty, {
      headers: this.getAuthHeaders()
    }).subscribe(() => {
      this.isEditingFaculty = false;
    });
  }
}
