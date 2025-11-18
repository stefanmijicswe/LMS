import { TestBed } from '@angular/core/testing';

import { StudyProgrammes } from './study-programmes';

describe('StudyProgrammes', () => {
  let service: StudyProgrammes;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudyProgrammes);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
