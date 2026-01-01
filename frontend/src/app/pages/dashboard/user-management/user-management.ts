import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';

import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

export interface User {
  id: number;
  username: string;
  roles: string[];
}

@Component({
  selector: 'app-user-management',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    MatTableModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatChipsModule,
    MatButtonModule,
    FormsModule,
    MatInputModule
  ],
  templateUrl: './user-management.html',
  styleUrls: ['./user-management.css']
})
export class UserManagement implements OnInit {

  displayedColumns: string[] = ['id', 'username', 'roles', 'actions'];
  users: User[] = [];

  loading = false;
  errorMessage = '';

  private apiUrl = 'http://localhost:8081/api/admin/users';

  editingUser: User | null = null;
  updateUsername = '';
  updatePassword = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading = true;
    this.errorMessage = '';

    const token = localStorage.getItem('token');

    if (!token) {
      this.errorMessage = 'You are not authenticated.';
      this.loading = false;
      return;
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    this.http.get<User[]>(this.apiUrl, { headers }).subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('API ERROR:', err);
        this.errorMessage = 'Failed to load users (403).';
        this.loading = false;
      }
    });
  }

  formatRole(role: string): string {
    return role.replace('ROLE_', '').replace('_', ' ');
  }

  startEdit(user: User): void {
    this.editingUser = user;
    this.updateUsername = user.username;
    this.updatePassword = '';
  }

  cancelEdit(): void {
    this.editingUser = null;
    this.updateUsername = '';
    this.updatePassword = '';
  }

  saveUpdate(userId: number): void {
    const token = localStorage.getItem('token');
    if (!token) {
      this.errorMessage = 'You are not authenticated.';
      return;
    }

    if (!this.updateUsername || !this.updatePassword) {
      alert('Username and password are required.');
      return;
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const body = {
      username: this.updateUsername,
      password: this.updatePassword
    };

    this.http.put(`${this.apiUrl}/${userId}`, body, { headers }).subscribe({
      next: () => {
        this.loadUsers();
        this.cancelEdit();
      },
      error: (err) => {
        console.error('Update failed:', err);
        alert('Failed to update user.');
      }
    });
  }

  deleteUser(userId: number): void {
    if (!confirm('Are you sure you want to delete this user?')) {
      return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
      this.errorMessage = 'You are not authenticated.';
      return;
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    this.http.delete(`${this.apiUrl}/${userId}`, { headers }).subscribe({
      next: () => {
        this.loadUsers();
      },
      error: (err) => {
        console.error('Delete failed:', err);
        alert('Failed to delete user.');
      }
    });
  }
}