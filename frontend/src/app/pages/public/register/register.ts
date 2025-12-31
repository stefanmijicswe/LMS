import { NgOptimizedImage } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'ngm-dev-block-login-email-password',
  templateUrl: './register.html',
  imports: [
    MatCard,
    MatCardContent,
    MatCardActions,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    NgOptimizedImage,
  ],
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  form = new FormGroup({
    username: new FormControl('', []),
    password: new FormControl('', []),
  });

  isLoading: boolean = false;
  errorMessage: string = '';

  onSubmit() {
    if (this.form.valid) {
      const username = this.form.value.username ?? '';
      const password = this.form.value.password ?? '';

      this.isLoading = true;
      this.errorMessage = '';

      this.authService.register(username, password).subscribe({
        next: (isRegistered) => {
          this.isLoading = false;
          
          if (isRegistered) {
            console.log('Register successful!');
            console.log('User roles:', this.authService.userRoles);
            
            this.router.navigate(['/app']);
          } else {
            this.errorMessage = 'Incorrect username or password.';
            console.log('Register failed');
          }
        },
        error: (error) => {
          this.isLoading = false;
          console.error('Register error:', error);
          
          if (error.status === 401 || error.status === 403) {
            this.errorMessage = 'Incorrect username or password.';
          } else if (error.status === 0) {
            this.errorMessage = 'No connection to server. Please check your internet connection.';
          } else {
            this.errorMessage = 'An error occurred. Please try again.';
          }
        }
      });
    } else {
      this.form.markAllAsTouched();
    }
  }
}
