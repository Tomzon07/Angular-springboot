import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { ServiceService } from '../service.service';


@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  constructor(private router:Router,private service:ServiceService) { }
 Chart:any;

  ngOnInit(): void {
    this.getChartData();
  }
  days:number=7
  dates:Array<string>=new Array<string>;
  counts:Array<number>=new Array<number>;
   
  Attemptdates:Array<string>=new Array<string>;
  AttemptCount:Array<number>=new Array<number>;



  getChartData(){
    // console.log("fffffffffff");
    this.dates=[]
    this.counts=[]
    
    let queryParams = new HttpParams()
    .append('days', this.days);
    this.service.chartData(queryParams).subscribe({
      next: (res: any) => {
        // console.log(res);
        
        const map1 = new Map(Object.entries(res));

        for (let key of map1.keys()) {
          this.dates.push(key);
        }

        for (let key of map1.values()) {
          this.counts.push(parseInt(key as string));

        }

        // console.log(this.counts);
        // console.log(this.dates);
        this.getAttemptData()
      },
      error: (err: any) => {
        console.log(err);
      },
    });
  }
  getAttemptData(){
    console.log("fghjnkm");
    this.Attemptdates=[]
    this.AttemptCount=[]
    
    let queryParams = new HttpParams()
    .append('days', this.days);
    this.service.getAttemptCount(queryParams).subscribe({
      next: (res: any) => {
        console.log(res);
        
        const map2 = new Map(Object.entries(res));

        for (let key of map2.keys()) {
          this.Attemptdates.push(key);
        }

        for (let key of map2.values()) {
          this.AttemptCount.push(parseInt(key as string));

        }

        console.log(this.AttemptCount);
        console.log(this.Attemptdates);
        
        this.createChart();
      },
      error: (err: any) => {
        console.log(err);
      },
    });
  }

  changeDays() {
    console.log(this.days);
    let chartStatus = Chart.getChart('myChart'); // <canvas> id
    if (chartStatus != undefined) {
      chartStatus.destroy();
    }
   
    this.getChartData();
  }

  
  
  createChart(){
    Chart.register(...registerables)
    const myChart = new Chart("myChart", {
      type: 'line', //this denotes tha type of chart

      data: {// values on X-Axis
        labels:this.dates, 
	       datasets: [
          {
            label: "User Registered",
            data: this.counts,
            tension:0.3,
                 backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(255, 159, 64, 0.2)',
                ],fill:true
            
          },
          {
            label: "Exam Attempted",
            data: this.AttemptCount,
            tension:0.3,
                   backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
             ],fill:true
          }
        ]
      },
      options: {
        aspectRatio:3
      }
      
    });
  }

}