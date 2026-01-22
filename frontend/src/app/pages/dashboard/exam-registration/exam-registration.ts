import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ExamRegistration as ExamRegistrationService, ActiveExaminationPeriod } from '../../../services/exam-registration/exam-registration';

@Component({
  selector: 'app-exam-registration',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    DatePipe
  ],
  templateUrl: './exam-registration.html',
  styleUrl: './exam-registration.css',
})
export class ExamRegistration implements OnInit {
  examinationPeriods: ActiveExaminationPeriod[] = [];
  isLoading: boolean = false;
  error: string | null = null;

  constructor(private examRegistrationService: ExamRegistrationService) {}

  ngOnInit(): void {
    this.loadExaminationPeriods();
  }

  loadExaminationPeriods(): void {
    this.isLoading = true;
    this.error = null;

    this.examRegistrationService.getActivePeriodsWithSubjects().subscribe({
      next: (periods: ActiveExaminationPeriod[]) => {
        this.examinationPeriods = periods;
        console.log(periods);
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading examination periods:', err);
        this.error = 'Failed to load examination periods';
        this.isLoading = false;
      }
    });
  }

  registerForExam(subject: any): void {
    if (!subject.knowledgeEvaluationId) {
      return;
    }

    this.examRegistrationService.registerForExam(subject.knowledgeEvaluationId).subscribe({
      next: () => {
        this.loadExaminationPeriods();
      },
      error: (err) => {
        console.error('Error registering for exam:', err);
        alert('Failed to register for exam. You may already be registered or the registration period may be closed.');
      }
    });
  }

  isCurrentlyActive(period: ActiveExaminationPeriod): boolean {
    const today = new Date();
    if (period.startDate && period.endDate) {
      const start = new Date(period.startDate);
      const end = new Date(period.endDate);
      return today >= start && today <= end;
    }
    return true;
  }
}
