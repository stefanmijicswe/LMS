import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { StudyProgrammes as StudyProgrammesService } from '../../../services/study-programmes/study-programmes';

@Component({
  selector: 'app-study-programmes',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './study-programmes.html',
  styleUrl: './study-programmes.css',
})
export class StudyProgrammes implements OnInit {

  studyProgrammes: any[] = [];
  programme: any = null;

  isLoading = false;
  error: string | null = null;

  constructor(
    private studyProgrammesService: StudyProgrammesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.loadStudyProgrammes(id ? +id : null);
    });
  }

  loadStudyProgrammes(id: number | null): void {
    this.isLoading = true;
    this.error = null;

        if (id) {
      this.studyProgrammesService.getStudyProgrammeById(id).subscribe({
        next: (data: any) => {
          if (data && data.facultyId) {
            this.router.navigate(['/faculties', data.facultyId, 'study-programmes', id]);
          } else {
            this.error = 'Study programme not found.';
            this.isLoading = false;
          }
        },
        error: () => {
          this.error = 'Failed to load study programme.';
          this.isLoading = false;
        }
      });
        } else {
      this.studyProgrammesService.getAllStudyProgrammes().subscribe({
        next: (data: any[]) => {
          this.studyProgrammes = data;
        this.isLoading = false;
      },
      error: () => {
        this.error = 'Failed to load study programmes.';
        this.isLoading = false;
      }
    });
    }
  }
}
