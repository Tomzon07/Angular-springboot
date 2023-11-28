import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-exam',
  templateUrl: './add-exam.component.html',
  styleUrls: ['./add-exam.component.css']
})
export class AddExamComponent implements OnInit {

  constructor(private service:ServiceService,private router:Router) { }
  addExamForm:FormGroup=new FormGroup({
    name:new FormControl('',[Validators.required]),
    description:new FormControl('',[Validators.required])
    
  })

 
  
  ngOnInit(): void {}

  
addExam(){
  if(!this.addExamForm.valid){
    this.addExamForm.markAllAsTouched();
    return;
  }

  let body={
    name:this.addExamForm.controls['name'].value,
    description:this.addExamForm.controls['description'].value
  };

  this.service.addExams(body).subscribe({
    next:(response: any) => {
      console.log('Success', response);
      this.router.navigate(['listexams']);
    },
    error: (error: any) => {
      console.log('error', error);
    },
    complete: () => {
      console.log('Completed');
    },
    
  })
}

}
