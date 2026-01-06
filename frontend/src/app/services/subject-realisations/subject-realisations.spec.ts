import { TestBed } from '@angular/core/testing';

import { SubjectRealisations } from './subject-realisations';

describe('SubjectRealisations', () => {
  let service: SubjectRealisations;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubjectRealisations);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
