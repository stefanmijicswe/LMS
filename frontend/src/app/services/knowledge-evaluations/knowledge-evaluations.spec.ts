import { TestBed } from '@angular/core/testing';

import { KnowledgeEvaluations } from './knowledge-evaluations';

describe('KnowledgeEvaluations', () => {
  let service: KnowledgeEvaluations;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KnowledgeEvaluations);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
