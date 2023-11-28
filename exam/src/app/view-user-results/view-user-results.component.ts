import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-view-user-results',
  templateUrl: './view-user-results.component.html',
  styleUrls: ['./view-user-results.component.css']
})
export class ViewUserResultsComponent implements OnInit {

  constructor(private route:Router,private service:ServiceService,private act:ActivatedRoute) { }

  results:any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 7 ;
  tableSizes: any = [3, 6, 9, 12];


  ngOnInit(): void {
    this.listResults();

    console.log(this.act.snapshot.params['eid']);
    
  }
listResults(){
  this.service.listResults().subscribe({
    next:(response:any)=>{
      this.results=response;
      console.log(this.results);
      
    },
    error:(error:any)=>{
      console.log(error);
      
    }
  })
}



  onTableDataChange(event: any) {
    this.page = event;
    this.listResults();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.listResults();
  }
}
