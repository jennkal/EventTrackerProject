import { LogService } from './../../services/log.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Log } from 'src/app/models/log';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  title: string = "log List";
  logList: Log[] = [];
  selected: Log | null = null;
  newLog: Log = new Log();
  editLog: Log | null = null;

  constructor(
    private logService: LogService,
    private route: ActivatedRoute,
    private router: Router
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

  displayLog(log: Log): void {
    this.selected = log;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditLog(): void {
    this.editLog = Object.assign({}, this.selected);
  }

  addLog(log: Log) {
    // this.logService.create(log);
    this.logService.create(log).subscribe({
      next: (createdLog) => {
        this.newLog = new Log();
        this.loadLogs();
      },
      error: (badNews) => {
        console.error("LogComponent.addLog(): error creating log");
        console.error(badNews);
      },
    });
  }

  updateLog(log: Log, goToDetails: boolean = true): void {
    this.logService.update(log).subscribe({
      next: (updatedLog) => {
        if (goToDetails) {
          this.selected = updatedLog;
        }
        this.editLog = null;
        this.loadLogs();
      },
      error: (noJoy) => {
        console.error("LogListComponent.updateLog(): error on update");
        console.error(noJoy);
      },
    });
  }

  deleteLog(logId: number) {
    this.logService.destroy(logId).subscribe({
      next: () => {
        this.loadLogs();
      },
      error: (fail) => {
        console.error("LogListComponent.deleteLog(): error deleting");
        console.error(fail);
      },
    });
  }
}
