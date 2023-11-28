import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

passwordResetToken:any;
  constructor(private activaterouter:ActivatedRoute,private service:ServiceService,private router:Router) { }

  ngOnInit(): void {
    this.passwordResetToken = this.activaterouter.snapshot.params['url'];
  }
  resetPasswordForm:FormGroup=new FormGroup({
    password:new FormControl('',[Validators.required]),
    cPassword:new FormControl('',[Validators.required])

  })


  resetPassword(){
    let data={
      password:this.resetPasswordForm.value.password
    }
    this.resetPasswordForm.markAllAsTouched();
    this.service.resetPassword(this.passwordResetToken,data).subscribe({
      next:(response:any)=>{
        console.log(response);
        alert("Password successfully reset")
        this.router.navigate(['login'])
      },
      error:(error:any)=>{
        console.log(error);
        alert("time out !!!!")
      }
    })
  }
}


