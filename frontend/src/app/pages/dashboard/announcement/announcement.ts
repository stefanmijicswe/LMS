import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TextFieldModule } from '@angular/cdk/text-field';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.html',
  styleUrls: ['./announcement.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TextFieldModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class Announcement {
  announcements: string[] = [
    "November exam term will be held from November 24 up to November 29, 2025. Registration for the exams begins on November 14, 2025. In November exam term, students who have defined their study status, i.e., enrolled the academic year 2025/2026, can apply for the exams.",
    "Welcome to Singidunum University!"
  ];

  constructor(private dialog: MatDialog) {}

  openCreateDialog(): void {
    const dialogRef = this.dialog.open(InlineAnnouncementDialog, {
      width: '700px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.announcements.unshift(result);
      }
    });
  }
}

@Component({
  selector: 'inline-announcement-dialog',
  template: `
    <h2 mat-dialog-title>New Announcement</h2>
    <mat-dialog-content>
      <mat-form-field appearance="fill" class="full-width">
        <mat-label>Announcement</mat-label>
        <textarea matInput [(ngModel)]="announcementText" cdkTextareaAutosize cdkAutosizeMinRows="10" cdkAutosizeMaxRows="30" class="large-textarea"></textarea>
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancel</button>
      <button mat-raised-button color="primary" (click)="submit()">Submit</button>
    </mat-dialog-actions>
  `,
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TextFieldModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class InlineAnnouncementDialog {
  announcementText: string = '';

  constructor(private dialogRef: MatDialogRef<InlineAnnouncementDialog>) {}

  submit() {
    if (this.announcementText.trim()) {
      this.dialogRef.close(this.announcementText.trim());
    }
  }
}