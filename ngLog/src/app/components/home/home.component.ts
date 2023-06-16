import { LogService } from './../../services/log.service';
import { Component, OnInit } from '@angular/core';
import { Log } from 'src/app/models/log';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  logList: Log[] = [];

  constructor(
    private logService: LogService
  ) {}

  ngOnInit(): void {
    this.loadLogs();
  }

  loadLogs() {
    this.logService.index().subscribe({
      next: (logList) => {
        this.logList = logList;
      },
      error: (someError) => {
        console.error('HomeComponent.');
        console.error(someError);
      }
     })
  }
}
