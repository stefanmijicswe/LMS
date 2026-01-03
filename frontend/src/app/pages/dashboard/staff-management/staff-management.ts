import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-staff-management',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule
  ],
  templateUrl: './staff-management.html',
  styleUrls: ['./staff-management.css']
})
export class StaffManagement {
  
  username: string = '';
  password: string = '';
  role: string = 'STUDENT_SERVICE';

  pin: string = '';
  biography: string = '';
  name: string = '';
  surname: string = '';

  private studentServiceApiUrl = 'http://localhost:8081/api/admin/student-services';
  private teacherApiUrl = 'http://localhost:8081/api/admin/teachers';

  constructor(private http: HttpClient) {}

  createUser() {
    const token = localStorage.getItem('token');
    if (!token) {
      alert('You are not logged in!');
      return;
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });

    let apiUrl = '';
    let payload: any = {};

    if (this.role === 'STUDENT_SERVICE') {
      apiUrl = this.studentServiceApiUrl;
      payload = {
        username: this.username,
        password: this.password
      };
    } else if (this.role === 'TEACHER') {
      apiUrl = this.teacherApiUrl;
      payload = {
        username: this.username,
        password: this.password,
        pin: this.pin,
        biography: this.biography,
        name: this.name,
        surname: this.surname
      };
    }

    this.http.post(apiUrl, payload, { headers })
      .pipe(
        catchError(err => {
          if (err.status === 403) {
            alert('You do not have permission to create this user.');
          } else {
            alert('An error occurred while creating the user.');
          }
          console.error('API error:', err);
          return of(null);
        })
      )
      .subscribe((res: any) => {
        if (res) {
          alert(`User ${res.username} created successfully!`);
          this.resetForm();
        }
      });
  }

  resetForm() {
    this.username = '';
    this.password = '';
    this.pin = '';
    this.biography = '';
    this.name = '';
    this.surname = '';
  }
}
