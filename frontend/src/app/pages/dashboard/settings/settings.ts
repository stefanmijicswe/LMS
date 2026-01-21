import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { AuthService } from '../../../auth.service';

import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatDialogModule
  ],
  templateUrl: './settings.html',
  styleUrls: ['./settings.css']
})
export class Settings {
  authService = inject(AuthService); 
  constructor(private dialog: MatDialog) {}

  openPasswordDialog() {
    this.dialog.open(PasswordDialogComponent, {
      width: '380px'
    });
  }
}

@Component({
  selector: 'password-dialog',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule
  ],
  template: `
    <h2 mat-dialog-title>Change Password</h2>

    <mat-dialog-content>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Current Password</mat-label>
        <input matInput type="password" [(ngModel)]="form.current">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>New Password</mat-label>
        <input matInput type="password" [(ngModel)]="form.newPass">
      </mat-form-field>

      <mat-form-field appearance="outline" style="width: 100%;">
        <mat-label>Confirm New Password</mat-label>
        <input matInput type="password" [(ngModel)]="form.confirm">
      </mat-form-field>

    </mat-dialog-content>

    <mat-dialog-actions align="end">
      <button mat-button (click)="close()">Cancel</button>
      <button mat-raised-button color="primary" (click)="submit()">Confirm</button>
    </mat-dialog-actions>
  `
})
export class PasswordDialogComponent {
  form = {
    current: '',
    newPass: '',
    confirm: ''
  };

  constructor(
    private dialogRef: MatDialogRef<PasswordDialogComponent>,
    private http: HttpClient
  ) {}

  close() {
    this.dialogRef.close();
  }

  submit() {
    if (this.form.newPass !== this.form.confirm) {
      alert('Passwords do not match');
      return;
    }
    if (!this.form.newPass || this.form.newPass.trim() === '') {
      alert('New password cannot be empty');
      return;
    }
    
    this.http.put('http://localhost:8081/api/me/password', {
      newPassword: this.form.newPass
    }).subscribe({
      next: () => {
        alert('Password changed successfully');
        this.dialogRef.close();
      },
      error: () => {
        alert('Failed to change password');
      }
    });
  }
}