import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-user-exam',
  templateUrl: './user-exam.component.html',
  styleUrls: ['./user-exam.component.css']
})
export class UserExamComponent implements OnInit {

  constructor(private service:ServiceService,private router:Router,private activatedRoute:ActivatedRoute) { }
  exams :any
 
  ngOnInit(): void {

    this.listUserExams();
  }


  listUserExams(){
    this.service.listexams().subscribe({
      next:(response:any)=>{
        this.exams=response;
       console.log('success');
        console.log(this.exams);
       },
       error: (error: any) => {
        console.log('error');
        console.log(error);
      },
    });
  }

  setExamId(examId:any){
    this.service.examId=examId;
  }


}
