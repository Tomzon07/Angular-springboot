import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-view-exam',
  templateUrl: './view-exam.component.html',
  styleUrls: ['./view-exam.component.css']
})
export class ViewExamComponent implements OnInit {

  constructor(private service:ServiceService,private router:Router) { }
exam:any
  ngOnInit(): void {
    this.viewExam();
  }

  viewExam(){
    this.service.viewExam(localStorage.getItem('viewExamId')).subscribe({
      next:(response:any)=>{
        console.log(response);
        this.exam=response;
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }
}
  



