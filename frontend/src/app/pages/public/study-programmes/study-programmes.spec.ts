import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyProgrammes } from './study-programmes';

describe('StudyProgrammes', () => {
  let component: StudyProgrammes;
  let fixture: ComponentFixture<StudyProgrammes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyProgrammes]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyProgrammes);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
