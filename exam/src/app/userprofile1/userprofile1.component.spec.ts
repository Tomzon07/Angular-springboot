import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userprofile1Component } from './userprofile1.component';

describe('Userprofile1Component', () => {
  let component: Userprofile1Component;
  let fixture: ComponentFixture<Userprofile1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Userprofile1Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userprofile1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
