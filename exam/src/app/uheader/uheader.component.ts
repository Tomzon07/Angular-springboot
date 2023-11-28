import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-uheader',
  templateUrl: './uheader.component.html',
  styleUrls: ['./uheader.component.css']
})
export class UheaderComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService) { }

  ngOnInit(): void {

  }

  
  logout(): void {


    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    console.log('Logged Out...');
    this.router.navigate(['login'])
  }


}
