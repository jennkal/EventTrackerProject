import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Log } from '../models/log';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  url: string = environment.baseUrl + 'api/logs';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Log[]> {
    return this.http.get<Log[]>(this.url).pipe(
      catchError((err: any) => {
        console.error('Error fetching log list.');
        return throwError(
          () =>
          new Error(
            "LogService.index(): error " + err)
        );
      })
    );
  }

  public show(logId: number): Observable<Log> {

    return this.http.get<Log>(this.url + '/' + logId).pipe(
      catchError((err: any) => {
        console.error('Error fetching log.');
        return throwError(
          () =>
          new Error(
            "logService.show(): error " + err)
        );
      })
    );
  }

  create(newLog: Log): Observable<Log> {
    //generates new Id
     newLog.details = '';
     return this.http.post<Log>(this.url, newLog).pipe(
       catchError((err: any) => {
         console.error('Error fetching log list.');
         return throwError(
           () =>
           new Error(
             "LogService.index(): error " + err)
         );
       })
     )
   }

     update(log: Log): Observable<Log> {
  //   if(log.completed) {
  //     todo.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
  // } else {
  //   todo.completeDate = '';
  // }
    return this.http.put<Log>(this.url + '/' + log.id, log).pipe(
      catchError((err: any) => {
        console.error('Error PUTING updated log.');
        return throwError(
          () =>
          new Error(
            "LogService.index(): error " + err)
        );
      })
    )
  }

  destroy(logId: number): Observable<void> {
    // const deletePath = '${this.url}/${id}';

     return this.http.delete<void>(`${this.url}/${logId}`).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError(
           () => new Error('LogService.index(): error retrieving log: ' + err)
         );
       })

     )
   }

}
