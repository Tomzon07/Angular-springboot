import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instruction',
  templateUrl: './instruction.component.html',
  styleUrls: ['./instruction.component.css']
})
export class InstructionComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService,private activatedRoute:ActivatedRoute) { }

exams:any
examId:any
  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot.params['eid']);
this. examId=this.activatedRoute.snapshot.params['eid']
    this.listUserExams(this.examId);
  }

  listUserExams(examId:any){
    this.service.viewExam(examId).subscribe({
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
  
  Attend()
  {
    Swal.fire({
      title: 'Do you want to start the '+this.exams.name+' Exam ?',
      showCancelButton: true,
      confirmButtonText: 'Start',
      denyButtonText: `Close`,
      confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
      icon:'info'
    }).then((result) => {
     
      if (result.isConfirmed) {
        this.router.navigateByUrl("userQuestionList/"+this.examId)
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }
}