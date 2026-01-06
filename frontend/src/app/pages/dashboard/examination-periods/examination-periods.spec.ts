import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationPeriods } from './examination-periods';

describe('ExaminationPeriods', () => {
  let component: ExaminationPeriods;
  let fixture: ComponentFixture<ExaminationPeriods>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExaminationPeriods]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExaminationPeriods);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
