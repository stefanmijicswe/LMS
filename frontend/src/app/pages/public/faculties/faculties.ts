import { Component, OnInit } from '@angular/core';
import { Faculties as FacultiesService } from '../../../services/faculties/faculties';

@Component({
  selector: 'app-faculties',
  imports: [],
  templateUrl: './faculties.html',
  styleUrl: './faculties.css',
})
export class Faculties implements OnInit{

  faculties: any[] = [];
  isLoading = false;

  constructor(private facultiesService: FacultiesService) {}
  

  ngOnInit(): void {
    this.loadFaculties();
  }

    loadFaculties(): void {
    this.isLoading = true;
    this.facultiesService.getAllFaculties().subscribe({
      next: (data) => {
        console.log(data);
        this.faculties = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading study programmes:', error);
        this.isLoading = false;
      }
    });
  }

}
