import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
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
    MatTableModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './organisation-management.html',
  styleUrls: ['./organisation-management.css']
})
export class OrganisationManagement implements OnInit {

  university: any;
  originalUniversity: any;
  faculties: any[] = [];
  editingFacultyId: number | null = null;
  editedFaculties: { [key: number]: any } = {};

  isEditingUniversity = false;

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
  private facultiesApi = 'http://localhost:8081/public/faculties';
  private facultyApiBase = 'http://localhost:8081/api/admin/faculties';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadUniversity();
    this.loadFaculties();
  }

  loadUniversity(): void {
    this.http.get(this.universityApi).subscribe({
      next: (data) => {
        this.university = data;
        this.originalUniversity = JSON.parse(JSON.stringify(data));
      },
      error: (err) => {
        console.error('Error loading university:', err);
      }
    });
  }

  editUniversity(): void {
    this.isEditingUniversity = true;
  }

  cancelUniversity(): void {
    this.isEditingUniversity = false;
    this.university = JSON.parse(JSON.stringify(this.originalUniversity));
  }

  saveUniversity(): void {
    this.http.put(this.universityApi, this.university).subscribe({
      next: () => {
        this.isEditingUniversity = false;
        this.loadUniversity();
      },
      error: (err) => {
        console.error('Error updating university:', err);
        if (err.status === 403) {
          alert('You do not have permission to update university.');
        } else {
          alert('Failed to update university. Status: ' + err.status);
        }
      }
    });
  }

  loadFaculties(): void {
    this.http.get<any[]>(this.facultiesApi).subscribe({
      next: (data) => {
        this.faculties = data;
      },
      error: (err) => {
        console.error('Error loading faculties:', err);
      }
    });
  }

  editFaculty(faculty: any): void {
    this.editingFacultyId = faculty.id;
    this.editedFaculties[faculty.id] = {
      name: faculty.name,
      description: faculty.description || '',
      phoneNumber: faculty.phoneNumber || '',
      officialEmail: faculty.officialEmail || '',
      website: faculty.website || ''
    };
  }

  cancelFaculty(faculty: any): void {
    this.editingFacultyId = null;
    delete this.editedFaculties[faculty.id];
  }

  saveFaculty(faculty: any): void {
    const edited = this.editedFaculties[faculty.id];
    if (!edited) return;

    const updateData = {
      name: edited.name || null,
      description: edited.description || null,
      phoneNumber: edited.phoneNumber || null,
      officialEmail: edited.officialEmail || null,
      website: edited.website || null,
      addressId: faculty.address?.id || null,
      deanId: faculty.dean?.id || null,
      universityId: faculty.university?.id || null
    };

    this.http.put<any>(`${this.facultyApiBase}/${faculty.id}`, updateData)
      .subscribe({
        next: () => {
          this.editingFacultyId = null;
          delete this.editedFaculties[faculty.id];
          this.loadFaculties();
        },
        error: (err) => {
          console.error('Error updating faculty:', err);
          if (err.status === 403) {
            alert('You do not have permission to update faculties.');
          } else {
            alert('Failed to update faculty. Status: ' + err.status);
          }
        }
      });
  }
}
