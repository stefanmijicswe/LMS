import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { University } from '../../../services/university';

@Component({
  selector: 'app-about',
  imports: [DatePipe],
  templateUrl: './about.html',
  styleUrl: './about.css',
})
export class About implements OnInit {
  
  universities: any[] = [];
  isLoading = false;

  constructor(private universityService: University) {}

  ngOnInit(): void {
    this.loadUniversities();
  }

  loadUniversities(): void {
    this.isLoading = true;
    this.universityService.getAllUniversities().subscribe({
      next: (data) => {
        this.universities = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading universities:', error);
        this.isLoading = false;
      }
    });
  }
}