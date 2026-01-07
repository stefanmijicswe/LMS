import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import {
  Activities as ActivitiesService,
  StudentActivityDTO
} from '../../../services/activities/activities';

@Component({
  selector: 'activities',
  templateUrl: './activities.html',
  styleUrls: ['./activities.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule
  ]
})
export class Activities implements OnInit {
  activities: StudentActivityDTO[] = [];

  displayedColumns: string[] = [
    'subjectName',
    'points',
    'startTime',
    'endTime'
  ];

  constructor(private activitiesService: ActivitiesService) {}

  ngOnInit(): void {
    this.loadActivities();
  }

  loadActivities(): void {
    this.activitiesService.getMyActivities().subscribe({
      next: (data: StudentActivityDTO[]) => {
        this.activities = data;
      },
      error: (err: unknown) => {
        console.error(err);
      }
    });
  }
}
