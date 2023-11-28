import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  result:any;
  correctAnswer:any
  Attempted:any

  constructor(private service:ServiceService,private router:Router) { }

  ngOnInit(): void {
   this.result= this.service.examStatus;
   
  }


  print(){
    window.print();
  }
}
