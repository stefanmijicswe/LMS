import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganisationManagement } from './organisation-management';

describe('OrganisationManagement', () => {
  let component: OrganisationManagement;
  let fixture: ComponentFixture<OrganisationManagement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganisationManagement]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganisationManagement);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
