import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteUserProfileComponent } from './delete-user-profile.component';

describe('DeleteUserProfileComponent', () => {
  let component: DeleteUserProfileComponent;
  let fixture: ComponentFixture<DeleteUserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteUserProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
