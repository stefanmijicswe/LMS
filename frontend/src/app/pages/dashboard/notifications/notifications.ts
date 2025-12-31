import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.html',
  styleUrls: ['./notifications.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule
  ]
})
export class Notifications {
  notifications: string[] = [
    "November exam term will be held from November 24 up to November 29, 2025. Registration for the exams begins on November 14, 2025. In November exam term, students who have defined their study status, i.e., enrolled the academic year 2025/2026, can apply for the exams.",
    "Welcome to Singidunum University!"
  ];
}