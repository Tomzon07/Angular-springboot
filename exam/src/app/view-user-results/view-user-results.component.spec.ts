import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUserResultsComponent } from './view-user-results.component';

describe('ViewUserResultsComponent', () => {
  let component: ViewUserResultsComponent;
  let fixture: ComponentFixture<ViewUserResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewUserResultsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewUserResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
