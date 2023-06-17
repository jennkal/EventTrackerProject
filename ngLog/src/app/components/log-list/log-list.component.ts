import { LogService } from './../../services/log.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Log } from 'src/app/models/log';

@Component({
  selector: 'app-log-list',
  templateUrl: './log-list.component.html',
  styleUrls: ['./log-list.component.css']
})
export class LogListComponent implements OnInit {

  title: string = "log List";
  logList: Log[] = [];
  selected: Log | null = null;
  newLog: Log = new Log();
  editLog: Log | null = null;
  logToEdit: Log | null = null;

  constructor(
    private logService: LogService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
        //call new service method, if logId is present
        let idString = this.route.snapshot.paramMap.get('id');
        if(!this.selected && idString) {
          console.log(idString);

          let logId:number = Number.parseInt(idString);
          if(isNaN(logId)) {
            this.router.navigateByUrl('invalidId');
          }
          else {
            this.logService.show(logId).subscribe({
              //grabing log form the API and display log with that id
              next: (log) => {
               this.displayLog(log);
              },
              error: (someError) => {
                console.error('error getting log')
                console.error(someError)
                this.router.navigateByUrl('logNotFound')
              }
            })
          }
        }

        this.reload();
  }

  reload() {
    this.logService.index().subscribe({
      next: (logList) => {
        this.logList = logList;
      },
      error: (someError) => {
        console.error("LogListComponent.reload(): error getting log list");
        console.error(someError);
      },
    });
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
        this.reload();
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
        this.reload();
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
        this.reload();
      },
      error: (fail) => {
        console.error("LogListComponent.deleteLog(): error deleting");
        console.error(fail);
      },
    });
  }

}
