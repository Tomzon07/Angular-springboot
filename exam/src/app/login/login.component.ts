import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  display="none";
  closeResult: string = '';

constructor(private router:Router,private service:ServiceService  ) { }

loginForm:FormGroup=new FormGroup({
  username:new FormControl('',[Validators.required]),
  password:new FormControl('',[Validators.required]),
})
  


changePasswordForm:FormGroup=new FormGroup({
  email: new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}'),
  ])
})


  ngOnInit(): void {
  }

  login() {
    if (!this.loginForm.valid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    let body = {
      email: this.loginForm.controls['username'].value,
      password: this.loginForm.get('password')?.value,
    };

    this.service.login(body).subscribe({
      next: (response: any) => {
        console.log('Success');
        if(response.role==0){
          this.router.navigate(['dashboard'])
          localStorage.setItem('accessToken',response.accessToken.value);

          localStorage.setItem("role",response.role)
        }
      else{
        this.router.navigate(['home'])
        localStorage.setItem('accessToken',response.accessToken.value);
        localStorage.setItem("role",response.role)
      }
        
      },
      error: (error: any) => alert(error.error.message)    
  });
  
}


openModal() {
  this.display = "block";
}
onCloseHandled() {
  this.display = "none";
} 


sendResetPswrdLink(){
  console.log(this.changePasswordForm.value, "=====");
  
    this.service.sendResetPswrdLink(this.changePasswordForm.value).subscribe({
      next: (response: any) => {
        console.log("success",response);
        alert("Reset Password link has been send to your mail")
        this.router.navigate(['login'])
      },
      error: (error: any) => {
        console.log("error", error);
        
      }
    })

  }
}







