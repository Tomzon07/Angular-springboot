import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-list-question',
  templateUrl: './list-question.component.html',
  styleUrls: ['./list-question.component.css']
})
export class ListQuestionComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService) { }
questions:any
id:any
page: number = 1;
count: number = 0;
tableSize: number = 3;
tableSizes: any = [3, 6, 9, 12];


  ngOnInit(): void {

    this.listQuestions();
  }

  listQuestions(){
  this. id= localStorage.getItem('examId')
    this.service.listQuestions(this.id).subscribe({
      next:(response:any)=>{
        this.questions=response;
        console.log('success');
     
      },
      error:(error:any)=>{
        console.log('error');
        console.log(error);
        
        
      },
      
    });
  }

  viewQuestion(questionId:any){
    console.log(questionId);


    this.service.viewQuestion(questionId).subscribe({
      next:(response:any)=>{
        console.log(response);
        
      },
      error:(error:any)=>{
        console.log('error');
        console.log(error);
      },
    });
    

    localStorage.setItem('viewQuestionId',questionId)
    this.router.navigate(['/viewquestions'])
  }

editQuestion(questionId:any){

  localStorage.setItem('editQuestion',questionId)
  this.router.navigate(['/editQuestion'])

}

deleteQuestion(questionId:any){
  this.service.deleteQuestion(questionId).subscribe({
    next:(response:any)=>{
      console.log('deleted',response);
      window.location.reload();
      
    },

    error:(error:any)=>{
      console.log(error);
      
    }
  });
}
onTableDataChange(event: any) {
  this.page = event;
  this.listQuestions();
}
onTableSizeChange(event: any): void {
  this.tableSize = event.target.value;
  this.page = 1;
  this.listQuestions();
}


  }


