import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Subjects as SubjectsService } from '../../../services/subjects/subjects';

@Component({
  selector: 'app-subject',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './subject.html',
  styleUrl: './subject.css',
})
export class Subject implements OnInit {
  subject: any = null;
  isLoading = false;
  error: string | null = null;

  constructor(
    private subjectsService: SubjectsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadSubject(+id);
      }
    });
  }

  loadSubject(id: number): void {
    this.isLoading = true;
    this.error = null;

    this.subjectsService.getSubjectById(id).subscribe({
      next: (data: any) => {
        this.subject = data;
        this.isLoading = false;
      },
      error: () => {
        this.error = 'Failed to load subject.';
        this.isLoading = false;
      }
    });
  }
}

