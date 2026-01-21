import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowledgeEvaluations } from './knowledge-evaluations';

describe('KnowledgeEvaluations', () => {
  let component: KnowledgeEvaluations;
  let fixture: ComponentFixture<KnowledgeEvaluations>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KnowledgeEvaluations]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KnowledgeEvaluations);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
