import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  id:any
  

  constructor(private router:Router,private service :ServiceService) { }

  addQuestionForm:FormGroup=new FormGroup({
    question:new FormControl('',[Validators.required]),
    option_A:new FormControl('',Validators.required),
    option_B:new FormControl('',Validators.required),
    option_C:new FormControl('',Validators.required),
    option_D:new FormControl('',[Validators.required]),
    answer:new FormControl('',[Validators.required])
  })

  ngOnInit(): void {
  }

  addQuestion(){
    if(!this.addQuestionForm.valid){
      
      
      this.addQuestionForm.markAllAsTouched();
      return;
    }
    let body={
      question:this.addQuestionForm.controls['question'].value,
      optionsA:this.addQuestionForm.controls['option_A'].value,
      optionsB:this.addQuestionForm.controls['option_B'].value,
      optionsC:this.addQuestionForm.controls['option_C'].value,
      optionsD:this.addQuestionForm.controls['option_D'].value,
      answer:this.addQuestionForm.controls['answer'].value,

    };

    
this.id=localStorage.getItem('examId');
console.log(this.id);

    this.service.addQuestions(this.id,body).subscribe({
      next:(response:any)=>{
        console.log('success',response);
        this.router.navigate(['listquestions']);
        
      },

      error:(error:any)=>{
        console.log(error);
        
      },
      complete:()=>{
        console.log('completed');
        
      }
    })


  }

}
