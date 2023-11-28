import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {
question:any
  constructor(private router:Router,private service:ServiceService) { }

editQuestionForm:FormGroup=new FormGroup({
  question:new FormControl('',[Validators.required]),
  option_A:new FormControl('',Validators.required),
  option_B:new FormControl('',Validators.required),
  option_C:new FormControl('',Validators.required),
  option_D:new FormControl('',[Validators.required]),
  answer:new FormControl('',[Validators.required])
});

  ngOnInit(): void {
    this.patchValues();
  }


  patchValues(){
    console.log(localStorage.getItem('editQuestion'));
    
    this.service.viewQuestion(localStorage.getItem('editQuestion')).subscribe({
      next:(response:any)=>{
        console.log(response);

        this.editQuestionForm.patchValue({
          question:response.question,
          option_A:response.optionsA,
          option_B:response.optionsB,
          option_C:response.optionsC,
          option_D:response.optionsD,
          answer:response.answer


        });
        
      },
    });
    
  }

  editQuesiton(){
    let body={
      question:this.editQuestionForm.controls['question'].value,
      optionsA:this.editQuestionForm.controls['option_A'].value,
      optionsB:this.editQuestionForm.controls['option_B'].value,
      optionsC:this.editQuestionForm.controls['option_C'].value,
      optionsD:this.editQuestionForm.controls['option_D'].value,
      answer:this.editQuestionForm.controls['answer'].value
    };

    this.service.editQuestion(localStorage.getItem('editQuestion'),body).subscribe({
      next:(response:any)=>{
        console.log(response);
        this.router.navigate(['listquestions'])
        
      }
    })


  }

}
