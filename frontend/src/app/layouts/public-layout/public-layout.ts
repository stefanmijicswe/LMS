import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink, RouterOutlet } from '@angular/router';
import { University } from '../../services/university';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-public-layout',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, RouterOutlet, RouterLink],
  templateUrl: './public-layout.html',
  styleUrl: './public-layout.css',
})
export class PublicLayout {

  universityName: string = 'University';
  constructor(private universityService: University, private authService: AuthService) {}


  ngOnInit(): void {
    this.loadUniversityName();
  }

  async login() {
    await this.authService.login();
  }
  
  async register() {
    await this.authService.register();
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
}
