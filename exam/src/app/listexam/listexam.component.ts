import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-listexam',
  templateUrl: './listexam.component.html',
  styleUrls: ['./listexam.component.css']
})
export class ListexamComponent implements OnInit {

  title = 'appBootstrap';
   
  closeResult: string = '';
  id: any;


  constructor(private service:ServiceService,private router:Router) { }
  exams:any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 3;
  tableSizes: any = [3, 6, 9, 12];

  
  ngOnInit(): void {
    this.listexams();
    this.fetchExams();
  }

  listexams(){
  
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

  viewExam(examId: any) {

    console.log(examId);
    
    this.service.viewExam(examId).subscribe({
      next: (response: any) => {
        console.log(response);
      },
      error: (error: any) => {
        console.log('error');
        console.log(error);
      },
    });

    localStorage.setItem('viewExamId',examId)
    this.router.navigate(['/viewexam'])
  }

  editExam(examId:any){
    localStorage.setItem('editExamId',examId)
    this.router.navigate(['/editexam'])
  }

  deleteExam(examId:any){
    
  if(confirm("Do you want to delete"))
    this.service.deleteExam(examId).subscribe({
      next: (response: any) => {
        console.log('Deleted',response);
        window.location.reload();
      },
      error: (error: any) => {
        console.log(error);
      },
    });
    
  }

  viewQuestion(examId:any){
    localStorage.setItem('examId',examId);
    this.router.navigate(['listquestions'])
  }



  fetchExams(){

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

  onTableDataChange(event: any) {
    this.page = event;
    this.fetchExams();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.fetchExams();
  }

}
