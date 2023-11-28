import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-user-profile',
  templateUrl: './add-user-profile.component.html',
  styleUrls: ['./add-user-profile.component.css']
})
export class AddUserProfileComponent implements OnInit {
  

 
  constructor(private router:Router,private service:ServiceService) { }

  addContactForm:FormGroup=new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    dob: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    
    address: new FormControl('', [Validators.required]),
   
    state: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    zipCode: new FormControl('', [Validators.required]),

    // work: new FormControl('', [Validators.required]),
   
    // place: new FormControl('', [Validators.required]),
  })

  ngOnInit(): void {
    
  }

  addContact() {

    if (!this.addContactForm.valid) {
      this.addContactForm.markAllAsTouched();
      return;
    }


    let requestBody = {
      firstname: this.addContactForm.controls['firstName'].value,
      lastname: this.addContactForm.controls['lastName'].value,
      dob: this.addContactForm.controls['dob'].value,
      phone:
        this.addContactForm.controls['phone'].value,
      email: 
        this.addContactForm.controls['email'].value,
      address: this.addContactForm.controls['address'].value,
      zipcode: this.addContactForm.controls['zipCode'].value,
      state: this.addContactForm.controls['state'].value,
      country: this.addContactForm.controls['country'].value,
      // work:this.addContactForm.controls['work'].value,
      // place:this.addContactForm.controls['place'].value
      
    };

    console.log(requestBody);
    

    this.service.addContacts(requestBody).subscribe({
      next: (response: any) => {
        console.log(response);
        this.router.navigate(['/userProfile'])
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }
}


