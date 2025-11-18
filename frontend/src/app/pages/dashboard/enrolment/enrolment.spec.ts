import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Enrolment } from './enrolment';

describe('Enrolment', () => {
  let component: Enrolment;
  let fixture: ComponentFixture<Enrolment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Enrolment]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Enrolment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
