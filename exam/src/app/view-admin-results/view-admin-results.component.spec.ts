import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAdminResultsComponent } from './view-admin-results.component';

describe('ViewAdminResultsComponent', () => {
  let component: ViewAdminResultsComponent;
  let fixture: ComponentFixture<ViewAdminResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAdminResultsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAdminResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
