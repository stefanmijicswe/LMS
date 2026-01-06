import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { University } from '../../../services/university';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-landing-page',
  imports: [CommonModule, MatButtonModule, MatIconModule, MatCardModule, RouterLink],
  templateUrl: './landing-page.html',
  styleUrl: './landing-page.css',
})
export class LandingPage implements OnInit {

  universityName: string = 'University';
  private authService = inject(AuthService);

  constructor(private universityService: University) {}

  ngOnInit(): void {
    this.loadUniversityName();
  }

  loadUniversityName(): void {
    this.universityService.getAllUniversities().subscribe({
      next: (data) => {
        if (data && data.length > 0) {
          this.universityName = data[0].name;
        }
      },
      error: (error) => {
        console.error('Error loading university:', error);
      }
    });
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
