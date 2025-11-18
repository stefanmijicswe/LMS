import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyProgrammesManagement } from './study-programmes-management';

describe('StudyProgrammesManagement', () => {
  let component: StudyProgrammesManagement;
  let fixture: ComponentFixture<StudyProgrammesManagement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyProgrammesManagement]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyProgrammesManagement);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
