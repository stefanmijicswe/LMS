import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectRealisations } from './subject-realisations';

describe('SubjectRealisations', () => {
  let component: SubjectRealisations;
  let fixture: ComponentFixture<SubjectRealisations>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubjectRealisations]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubjectRealisations);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
