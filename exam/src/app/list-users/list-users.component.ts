import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {


  title = 'appBootstrap';

  closeResult: string = '';


  constructor(private service: ServiceService, private router: Router) { }

  users: any
  page: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [3, 6, 9, 12];

  ngOnInit(): void {
    this.listusers();
  }

  listusers() {


    this.service.listusers().subscribe({
      next: (response: any) => {
        this.users = response;
        console.log('success');
        console.log(this.users);


      },
      error: (error: any) => {
        console.log(error);

      },
    });
  }

  viewUser(userId: any) {
    this.service.viewUsers(userId).subscribe({
      next: (res: any) => {
        console.log(res);

      },
      error: (error: any) => {
        console.log(error);

      },
    });
  }


  deleteUser(userId: any) {

    if (confirm('Do you want to delete this user ?'))
      this.service.deleteUser(userId).subscribe({
        next: (response: any) => {
          console.log('Deleted', response);
          window.location.reload();
        },
        error: (error: any) => {
          console.log(error);
        },

      });

  }
  onTableDataChange(event: any) {
    this.page = event;
    this.listusers();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.listusers();
  }

  file: any
  onChange(event: any) {
    this.file = event.target.files[0];
    console.log('Changed');

    this.upload()

  }


  upload() {

    let data = new FormData();

    data.append('file', this.file);

    this.service.csvUpload(data).subscribe({
      next: (res: any) => {
        console.log(res);


      }
    })

  }
}
