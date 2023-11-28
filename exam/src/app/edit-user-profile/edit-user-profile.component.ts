import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService) { }

  editContactForm:FormGroup=new FormGroup({
    firstName: new FormControl('', [Validators.required,Validators.pattern('^[a-zA-Z0-9]{6,12}$')]),
    lastName: new FormControl('', [Validators.required,Validators.pattern('^[a-zA-Z0-9]{6,12}$')]),
    dob: new FormControl('', [Validators.required]),
    email: new FormControl('',  [
      Validators.required,
      Validators.pattern('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}'),
    ]),
    phone: new FormControl('', [Validators.required,Validators.pattern('[0-9]{10}')]),
    
    address: new FormControl('', [Validators.required]),
   
    state: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    zipCode: new FormControl('', [Validators.required,Validators.pattern('[0-9]{6}')]),
    // work: new FormControl('', [Validators.required]),
    // place: new FormControl('', [Validators.required]),
  })



  ngOnInit(): void {
    this.patchvalues();
  }

  patchvalues(){
    this.service.userProfileList().subscribe({
      next: (response :any)=>{
        console.log(response);


        this.editContactForm.patchValue({
          firstName:response.firstname,
          lastName:response.lastname,
          dob:response.dob,
          email:response.email,
          phone:response.phone,
          address:response.adddress,
          zipCode:response.zipcode,
          state:response.state,
          country:response.country,
          // work:response.work,
          // place:response.place

        })
        
      }
    })
  }


  editContact(){

    if(!this.editContactForm.valid){
      this.editContactForm.markAllAsTouched();
      return;
    }
    
    let body={
      firstname:this.editContactForm.controls['firstName'].value,
      lastname:this.editContactForm.controls['lastName'].value,
      dob:this.editContactForm.controls['dob'].value,
      email:this.editContactForm.controls['email'].value,
      phone:this.editContactForm.controls['phone'].value,
      address:this.editContactForm.controls['address'].value,
      zipcode:this.editContactForm.controls['zipCode'].value,
      state:this.editContactForm.controls['state'].value,
      country:this.editContactForm.controls['country'].value,
      // work:this.editContactForm.controls['work'].value,
      // place:this.editContactForm.controls['place'].value
    };

    this.service.editUserProfile(body).subscribe({
      next:(res:any)=>{
        console.log(res);
        this.router.navigate(['userProfile'])
      }
      
    })
  }
  

}
