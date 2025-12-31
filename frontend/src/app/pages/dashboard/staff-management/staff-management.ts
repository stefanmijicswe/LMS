import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-staff-management',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    HttpClientModule
  ],
  templateUrl: './staff-management.html',
  styleUrls: ['./staff-management.css']
})
export class StaffManagement {
  username: string = '';
  password: string = '';
  role: string = 'STUDENT_SERVICE';
  apiUrl: string = 'http://localhost:8081/api/admin/student-services';

  constructor(private http: HttpClient) {}

  createUser() {
    const payload = {
      username: this.username,
      password: this.password
    };

    const token = localStorage.getItem('token');
    if (!token) {
      alert('You are not logged in!');
      return;
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    this.http.post(this.apiUrl, payload, { headers })
      .pipe(
        catchError(err => {
          if (err.status === 403) {
            alert('You do not have permission to create a user.');
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
          this.username = '';
          this.password = '';
        }
      });
  }
}