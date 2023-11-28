import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-view-admin-results',
  templateUrl: './view-admin-results.component.html',
  styleUrls: ['./view-admin-results.component.css'],
  styles: [
    `
      .main-panel {
        height: 100px;
        overflow-y: scroll;
      }
    `,
  ],
  template: `
    <div class="main-panel">
      <div
        infiniteScroll
        [infiniteScrollDistance]="2"
        [infiniteScrollThrottle]="50"
        [infiniteScrollContainer]="selector"
        [fromRoot]="true"
        (scrolled)="onScroll()"
      ></div>
    </div>
  `,
})
export class ViewAdminResultsComponent implements OnInit {

  constructor(private route: Router, private service: ServiceService, private act: ActivatedRoute) { }
  searchs: any
  results: any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 6;
  tableSizes: any = [3, 6, 9, 12];


  throttle = 300;
  scrollDistance = 1;
  scrollUpDistance = 2;
  limit: number = 20;

  ngOnInit(): void {
    // this.listResult();

    this.search()

    console.log(this.act.snapshot.params['eid']);
  }

  listResult() {
    this.service.listAdminResults().subscribe({
      next: (response: any) => {
        this.results = response;
        console.log(this.results);

      },
      error: (error: any) => {
        console.log(error);

      }
    })
  }



  onTableDataChange(event: any) {
    this.page = event;
    this.listResult();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.listResult();
  }


  downloadReasults() {
    this.service.resultDownload().subscribe({
      next: (response: any) => {
        let a = document.createElement('a');
        a.download = 'results.csv';
        a.href = window.URL.createObjectURL(response);
        a.click();
      },
      error: (error) => console.log(error),
    });
  }


  onScroll() {
    console.log("scrolled!!");
  }


  searchKey: string = ''
  search() {
    this.page = 1;
    this.results = [];

    let params = new HttpParams().append('search', this.searchKey)
    this.service.searchOption(params).subscribe({
      next: (response: any) => {
        this.results = response;
      }
    })
  }
}




