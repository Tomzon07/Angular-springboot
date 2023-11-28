import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserQuestionListComponent } from './user-question-list.component';

describe('UserQuestionListComponent', () => {
  let component: UserQuestionListComponent;
  let fixture: ComponentFixture<UserQuestionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserQuestionListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserQuestionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
