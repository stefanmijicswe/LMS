import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';

interface DecodedToken {
  id: number;
  sub: string;
  roles: string[];
  exp: number;
  iat: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = 'http://localhost:8081/api';
  userRoles: string[] = [];
  username: string = "";

  constructor() {
    this.loadRolesFromToken();
  }

  private loadRolesFromToken(): void {
  const token = localStorage.getItem('token');
  if (token) {
    const decodedToken: DecodedToken = jwtDecode(token);
    this.userRoles = decodedToken.roles || [];
    this.username = decodedToken.sub || '';
  }
}

 login(username: string, password: string): Observable<boolean> {
  return this.http.post<{ token: string }>(`${this.apiUrl}/auth/signin`, { 
    username,
    password 
  }).pipe(
    tap(response => {
      localStorage.setItem('token', response.token);
      const decodedToken: DecodedToken = jwtDecode(response.token);
      this.userRoles = decodedToken.roles || [];
      this.username = decodedToken.sub;
      console.log('Roles loaded:', this.userRoles);
    }),
    map(() => true),
    catchError((error) => {
      console.error('Login error:', error);
      this.userRoles = [];
      this.username = "";
      return of(false);
    })
  );
}

register(username: string, password: string): Observable<boolean> {
  return this.http.post<{ token: string }>(`${this.apiUrl}/auth/signup`, { 
    username,
    password 
  }).pipe(
    tap(response => {
      localStorage.setItem('token', response.token);
      const decodedToken: DecodedToken = jwtDecode(response.token);
      this.userRoles = decodedToken.roles || [];
      this.username = decodedToken.sub;
      console.log('Roles loaded:', this.userRoles);
    }),
    map(() => true),
    catchError((error) => {
      console.error('Register error:', error);
      this.userRoles = [];
      this.username = "";
      return of(false);
    })
  );
}

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this.userRoles = [];
    this.router.navigate(['/login']);
    console.log("Logged out successfully.")
  }

  hasRole(requiredRole: string): boolean {
    return this.userRoles.includes(requiredRole);
  }
}
