import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-edit-exam',
  templateUrl: './edit-exam.component.html',
  styleUrls: ['./edit-exam.component.css']
})
export class EditExamComponent implements OnInit {
exam:any
  constructor(private router:Router,private service:ServiceService) { }

  editExamForm:FormGroup=new FormGroup({
    name:new FormControl('',[Validators.required]),
    description:new FormControl('',[Validators.required])
    
  });

  ngOnInit(): void {
    this.patchvalues();
  }
  patchvalues(){
    this.service.viewExam(localStorage.getItem('editExamId')).subscribe({
      next:(response:any)=>{
        console.log(response);

        this.editExamForm.patchValue({
          name:response.name,
          description:response.description
        });
        
      },
    });
    
  console.log(this.exam);
  }
  
  editExam(){
    let body={
      name:this.editExamForm.controls['name'].value,
      description:this.editExamForm.controls['description'].value
    };
    this.service.editExam(localStorage.getItem('editExamId'),body).subscribe({
      next:(response:any)=>{
        console.log(response);
        this.router.navigate(['listexams'])
        
      }
    })

  }

}
