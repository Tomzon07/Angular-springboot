import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private service:ServiceService,private router:Router) { }

  ngOnInit(): void {
  }



  logout(): void {


    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    console.log('Logged Out...');
    this.router.navigate(['login'])
    localStorage.removeItem('role');
    localStorage.clear();
  }

  listUsers(){
    this.router.navigate(['listusers']);
  }
  

  route(){
    this.router.navigate(['home']);
  }
}
