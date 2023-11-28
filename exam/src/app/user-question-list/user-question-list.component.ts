import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-user-question-list',
  templateUrl: './user-question-list.component.html',
  styleUrls: ['./user-question-list.component.css']
})
export class UserQuestionListComponent implements OnInit {

  questions: any
  marksGot = 0;
  correctAnswers = 0;
  attempted = 0;
  timer: any
  // givenAnswer:any
  type: any
  givenAnswer : {id : number,answer:string}[]=[]
  temp: any;


  constructor(private router: Router, private service: ServiceService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    console.log(this.activatedRoute.snapshot.params['qId']);




    this.listQuestions();
  }

  listQuestions() {
    this.service.listQuestions(this.activatedRoute.snapshot.params['qId']).subscribe({
      next: (response: any) => {
        this.questions = response;
        this.questions.forEach((q: any) => {

          q['givenAnswer'] = '';

        }),

          this.timer = this.questions.length * 1 * 60;
        console.log(this.questions);

        this.startTimer();
      },
      error: (error: any) => {
        console.log('error');
        Swal.fire('error', 'Error in loading exams', 'error')
      },
    });
  }

  startTimer() {
    let t = window.setInterval(() => {
      if (this.timer <= 0) {
        this.submitExam();
        clearInterval(t);
      } else {
        this.timer--;
      }


    }, 1000)
  }

  getFormattedTime() {
    let mm = Math.floor(this.timer / 60);
    let ss = this.timer - mm * 60;
    return `${mm} min : ${ss} sec`;
  }



  submitExam() {

    Swal.fire({
      title: 'Do you want to submit ?',
      showCancelButton: true,
      confirmButtonText: 'SUBMIT',
      icon: 'info',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

    }).then((e) => {


      if (e.isConfirmed) {

        

        this.service.evalExam(this.activatedRoute.snapshot.params['qId'],this.givenAnswer).subscribe({
          next: (res:any) => {
            console.log(res);
            this.service.examStatus=res;
            this.router.navigate(['result'])

          },

          error:(err:any)=>{
            console.log(err.error.text);
            this.service.examStatus=err.error.text;

            this.router.navigate(['result'])
            
          }
        })
      }
      else if (e.isDenied) {
        Swal.fire('No Changes', '', 'info')
      }

    })
    
  
    if (this.timer == 0){
        this.submitExam
    }
  };

  onChange(e: any, i: any) {
    this.type = e.target.value;
    // this.type = option;
    if (this.givenAnswer[0] != null) {
      const objIndex = this.givenAnswer.findIndex((obj => obj.id == i));
      if (objIndex != -1) {
        console.log(objIndex);
        // this.givenAnswer[objIndex-1] = { id: i, answer: option }
        this.givenAnswer.splice(objIndex, 1)

      }



    }




    if (this.givenAnswer[i] == null) {
      this.givenAnswer.push({ id: i, answer: this.type })

    } else {
      this.givenAnswer.splice(i, 1);
      this.givenAnswer.splice(1, 0, { id: i, answer: this.type })
    }


    console.log(this.givenAnswer);

  }

}

