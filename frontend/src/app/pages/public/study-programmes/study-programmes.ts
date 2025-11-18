import { Component, OnInit } from '@angular/core';
import { StudyProgrammes as StudyProgrammesService } from '../../../services/study-programmes/study-programmes';

@Component({
  selector: 'app-study-programmes',
  imports: [],
  templateUrl: './study-programmes.html',
  styleUrl: './study-programmes.css',
})
export class StudyProgrammes implements OnInit{

  studyProgrammes: any[] = [];
  isLoading = false;

  constructor(private studyProgrammesService: StudyProgrammesService) {}

  ngOnInit(): void {
    this.loadStudyProgrammes();
  }

  loadStudyProgrammes(): void {
    this.isLoading = true;
    this.studyProgrammesService.getAllStudyProgrammes().subscribe({
      next: (data) => {
        console.log(data);
        this.studyProgrammes = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading study programmes:', error);
        this.isLoading = false;
      }
    });
  }

}
