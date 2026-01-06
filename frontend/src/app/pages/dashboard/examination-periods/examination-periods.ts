import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { ExaminationPeriodService, ExaminationPeriodDTO, CreateExaminationPeriodDTO, UpdateExaminationPeriodDTO } from '../../../services/examination-period/examination-period';

@Component({
  selector: 'app-examination-periods',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule
  ],
  templateUrl: './examination-periods.html',
  styleUrl: './examination-periods.css',
})
export class ExaminationPeriods implements OnInit {
  private examinationPeriodService = inject(ExaminationPeriodService);

  examinationPeriods: ExaminationPeriodDTO[] = [];
  dataSource = new MatTableDataSource<ExaminationPeriodDTO>([]);
  displayedColumns: string[] = ['name', 'startDate', 'endDate', 'active', 'actions'];

  form = {
    name: '',
    startDate: null as Date | null,
    endDate: null as Date | null,
    active: false
  };

  isLoading = false;
  isLoadingList = false;

  ngOnInit() {
    this.loadExaminationPeriods();
  }

  loadExaminationPeriods() {
    this.isLoadingList = true;
    this.examinationPeriodService.getAllExaminationPeriods().subscribe({
      next: (periods: ExaminationPeriodDTO[]) => {
        this.examinationPeriods = periods;
        this.dataSource.data = periods;
        this.isLoadingList = false;
      },
      error: (error: any) => {
        console.error('Failed to load examination periods:', error);
        this.isLoadingList = false;
      }
    });
  }

  createExaminationPeriod() {
    if (!this.form.name || !this.form.startDate || !this.form.endDate) {
      alert('All fields are required');
      return;
    }

    if (this.form.startDate >= this.form.endDate) {
      alert('Start date must be before end date');
      return;
    }

    this.isLoading = true;

    const dto: CreateExaminationPeriodDTO = {
      name: this.form.name,
      startDate: this.formatDate(this.form.startDate!),
      endDate: this.formatDate(this.form.endDate!),
      active: this.form.active
    };

    this.examinationPeriodService.createExaminationPeriod(dto).subscribe({
      next: () => {
        alert('Examination Period created successfully!');
        this.resetForm();
        this.loadExaminationPeriods();
        this.isLoading = false;
      },
      error: (error: any) => {
        const errorMessage = error.error?.message || 'Failed to create examination period';
        alert(errorMessage);
        this.isLoading = false;
      }
    });
  }

  resetForm() {
    this.form = {
      name: '',
      startDate: null,
      endDate: null,
      active: false
    };
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  formatDateDisplay(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString();
  }

  toggleActive(period: ExaminationPeriodDTO) {
    const dto: UpdateExaminationPeriodDTO = {
      active: !period.active
    };

    this.examinationPeriodService.updateExaminationPeriod(period.id, dto).subscribe({
      next: () => {
        this.loadExaminationPeriods();
      },
      error: (error: any) => {
        const errorMessage = error.error?.message || 'Failed to update examination period';
        alert(errorMessage);
      }
    });
  }
}
