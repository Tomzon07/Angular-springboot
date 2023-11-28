import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';
import { MatIcon } from '@angular/material/icon';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
profile:any
file:any

  constructor(private router:Router,private service:ServiceService) { }

  ngOnInit(): void {
    this.userProfile();
    this.getProfilePic();
  }

 

  userProfile(){
  this.service.userProfileList().subscribe({
    next:(response:any)=>{
      this.profile=response;
      console.log(this.profile);
      console.log('success');      
    },
    error:(error:any)=>{
      console.log('error');
      
      
    }
  })
  }

  getProfilePic(): void {
    this.service.getProfile().subscribe({
      next: (response: any) => {
        console.log(response);

        (document.getElementById('profilePicture') as HTMLImageElement).src =
          URL.createObjectURL(new Blob([response], { type: response.type }));
      },
      error(err) {
        console.log(err);
      },
    });
  }


  onChange(event: any) {
    this.file = event.target.files[0];
    console.log('Changed');
    this.uploadProfilePic();
  }

  uploadProfilePic(): void {
    let data = new FormData();

    data.append('image', this.file);

    this.service.uploadProfile(data).subscribe({
      next: (response: any) => {
        console.log(response);
        this.getProfilePic();
      },
      error(err:any) {
        console.log(err);
      },
    });
  }

}
