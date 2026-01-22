import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
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
    MatTableModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './study-programmes-management.html',
  styleUrls: ['./study-programmes-management.css']
})
export class StudyProgrammesManagement implements OnInit {

  studyProgrammes: any[] = [];
  editingId: number | null = null;
  editedProgrammes: { [key: number]: any } = {};
  faculties: any[] = [];

  tableColumns: string[] = ['name', 'description', 'facultyId', 'actions'];

  private publicApiUrl = 'http://localhost:8081/public/study-programmes';
  private adminApiUrl = 'http://localhost:8081/api/admin/study-programmes';
  private facultiesApiUrl = 'http://localhost:8081/public/faculties';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadStudyProgrammes();
    this.loadFaculties();
  }

  loadStudyProgrammes(): void {
    this.http.get<any[]>(this.publicApiUrl)
      .subscribe({
        next: (data) => {
          this.studyProgrammes = data;
          this.editedProgrammes = {};
          data.forEach((sp) => {
            this.http.get<any>(`${this.publicApiUrl}/${sp.id}`)
              .subscribe({
                next: (detailed) => {
                  const index = this.studyProgrammes.findIndex(p => p.id === detailed.id);
                  if (index !== -1) {
                    this.studyProgrammes[index].description = detailed.description;
                  }
                },
                error: (err) => {
                  console.error(`Error loading description for programme ${sp.id}:`, err);
                }
              });
          });
        },
        error: (err) => {
          console.error('Error loading study programmes:', err);
        }
      });
  }

  getFacultyName(facultyId: number | null): string {
    if (!facultyId) return '-';
    const faculty = this.faculties.find(f => f.id === facultyId);
    return faculty ? faculty.name : `ID: ${facultyId}`;
  }

  edit(studyProgramme: any): void {
    this.editingId = studyProgramme.id;
    if (this.faculties.length === 0) {
      this.loadFaculties();
    }
    this.editedProgrammes[studyProgramme.id] = {
      name: studyProgramme.name,
      description: studyProgramme.description || '',
      facultyId: studyProgramme.facultyId != null ? Number(studyProgramme.facultyId) : null
    };
  }

  cancel(studyProgramme: any): void {
    this.editingId = null;
    delete this.editedProgrammes[studyProgramme.id];
  }

  save(studyProgramme: any): void {
    const edited = this.editedProgrammes[studyProgramme.id];
    if (!edited) return;

    const updateData: any = {
      name: edited.name || null,
      description: edited.description || null,
      coordinatorId: studyProgramme.coordinator?.id || null
    };

    if (edited.facultyId != null) {
      updateData.facultyId = Number(edited.facultyId);
    } else {
      updateData.facultyId = null;
    }

    this.http.put<any>(`${this.adminApiUrl}/${studyProgramme.id}`, updateData)
      .subscribe({
        next: (updated: any) => {
          const index = this.studyProgrammes.findIndex(sp => sp.id === studyProgramme.id);
          if (index !== -1) {
            this.studyProgrammes[index].name = updated.name;
            this.studyProgrammes[index].description = updated.description;
            this.studyProgrammes[index].facultyId = updated.facultyId;
          }
          this.editingId = null;
          delete this.editedProgrammes[studyProgramme.id];
          this.loadStudyProgrammes();
        },
        error: (err) => {
          console.error('Error updating study programme:', err);
          if (err.status === 403) {
            alert('You do not have permission to update study programmes.');
          } else {
            alert('Failed to update study programme. Status: ' + err.status);
          }
        }
      });
  }

  loadFaculties(): void {
    this.http.get<any[]>(this.facultiesApiUrl)
      .subscribe({
        next: (data) => {
          this.faculties = data;
        },
        error: (err) => {
          console.error('Error loading faculties:', err);
        }
      });
  }
}
