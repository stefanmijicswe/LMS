import { TestBed } from '@angular/core/testing';

import { Faculties } from './faculties';

describe('Faculties', () => {
  let service: Faculties;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Faculties);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
