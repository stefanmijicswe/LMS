import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'activities',
  templateUrl: './activities.html',
  styleUrls: ['./activities.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
  ]
})
export class Activities {
  subjects = [
    { number: 1, name: 'Mathematics', attempts: 2, finalPoints: 90, finalGrade: 10, ects: 6 },
    { number: 2, name: 'Discrete Mathematics', attempts: 1, finalPoints: 80, finalGrade: 9, ects: 6 },
    { number: 3, name: 'Object Oriented Programming 1', attempts: 2, finalPoints: 85, finalGrade: 8, ects: 6 },
    { number: 4, name: 'Computer Networks', attempts: 1, finalPoints: 70, finalGrade: 6, ects: 6 }
  ];

  displayedColumns: string[] = ['number', 'name', 'attempts', 'finalPoints', 'finalGrade', 'ects'];

  ngOnInit() {
  }

  get totalEcts(): number {
    return this.subjects.reduce((acc, sub) => acc + sub.ects, 0);
  }
  
  get averageGrade(): number {
    const totalGrades = this.subjects.reduce((acc, sub) => acc + sub.finalGrade, 0);
    return totalGrades / this.subjects.length;
  }
}