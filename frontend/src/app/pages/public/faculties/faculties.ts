import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Faculties as FacultiesService } from '../../../services/faculties/faculties';
import { StudyProgrammes as StudyProgrammesService } from '../../../services/study-programmes/study-programmes';

@Component({
  selector: 'app-faculties',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './faculties.html',
  styleUrls: ['./faculties.css'],
})
export class Faculties implements OnInit {
  faculties: any[] = [];
  selectedFaculty: any = null;
  selectedProgramme: any = null;

  isLoading = false;
  error = '';

  constructor(
    private facultiesService: FacultiesService,
    private studyProgrammesService: StudyProgrammesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadFaculties();

    this.route.paramMap.subscribe(params => {
      const facultyId = params.get('facultyId');
      const studyProgrammeId = params.get('studyProgrammeId');

      if (facultyId && this.faculties.length > 0) {
        this.selectedFaculty = this.faculties.find(f => f.id == +facultyId);

        if (studyProgrammeId) {
          this.loadStudyProgrammeDetails(+studyProgrammeId);
        } else {
          this.selectedProgramme = null;
        }
      }
    });
  }

  loadFaculties(): void {
    this.isLoading = true;
    this.facultiesService.getAllFaculties().subscribe({
      next: (data: any) => {
        this.faculties = data;
        this.isLoading = false;

        const facultyId = this.route.snapshot.paramMap.get('facultyId');
        const studyProgrammeId = this.route.snapshot.paramMap.get('studyProgrammeId');

        if (facultyId) {
          this.selectedFaculty = this.faculties.find(f => f.id == +facultyId);
        }

        if (facultyId && studyProgrammeId) {
          this.loadStudyProgrammeDetails(+studyProgrammeId);
        }
      },
      error: (err: any) => {
        this.error = 'Failed to load faculties.';
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  trackById(index: number, faculty: any): number {
    return faculty.id;
  }

  viewFaculty(faculty: any) {
    this.selectedFaculty = faculty;
    this.selectedProgramme = null;
    this.router.navigate(['/faculties', faculty.id]);
  }

  viewStudyProgramme(facultyId: number, programmeId: number) {
    this.router.navigate(['/faculties', facultyId, 'study-programmes', programmeId]);
  }

  backToFaculty() {
    if (this.selectedFaculty) {
      this.router.navigate(['/faculties', this.selectedFaculty.id]);
      this.selectedProgramme = null;
    }
  }

  loadStudyProgrammeDetails(id: number): void {
    this.isLoading = true;
    this.studyProgrammesService.getStudyProgrammeById(id).subscribe({
      next: (data: any) => {
        this.selectedProgramme = data;
        this.isLoading = false;
      },
      error: (err: any) => {
        this.error = 'Failed to load study programme details.';
        console.error(err);
        this.isLoading = false;
      }
    });
  }
}
