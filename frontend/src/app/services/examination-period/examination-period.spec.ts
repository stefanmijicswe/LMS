import { TestBed } from '@angular/core/testing';

import { ExaminationPeriod } from './examination-period';

describe('ExaminationPeriod', () => {
  let service: ExaminationPeriod;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExaminationPeriod);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
