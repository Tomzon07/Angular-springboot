import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private service:ServiceService,private router :Router) {}

  registrationForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}'),
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.pattern(
        '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$'
      ),
    ]),
  });

  ngOnInit(): void {}

  register() {
    console.log('hiiiii');
    if (!this.registrationForm.valid) {
      console.log('helloooo');
      this.registrationForm.markAllAsTouched();
      return;
    }

    let param = {
      name: this.registrationForm.controls['name'].value,
      email: this.registrationForm.get('email')?.value,
      password: this.registrationForm.controls['password'].value,
      role:1
    };

    this.service.register(param).subscribe({
      next: (response: any) => {
        console.log('Success', response);
        this.router.navigate(['login']);
      },
      error: (error: any) => {
        console.log('error',error.error);
        if (error.error.status == 400) {
          if (error.error.message == 'User Already Exists') {
            console.log('User Already Exists');
          }
        }
      },
      complete: () => {
        console.log('Completed');
      },
    });
  }

}
