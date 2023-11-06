import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Log } from '../models/log';

@Injectable({
  providedIn: 'root' //this makes it possible to access throughout application
})
export class LogService {

  private logs: Log[] = [];

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

  createLog(log: Log): Observable<Log> {

     return this.http.post<Log>(this.url, log).pipe(
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

  const url = `${this.url}/${log.id}`;
    return this.http.put<Log>(this.url, log).pipe(

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

      const url = `${this.url}/${logId}`;
     return this.http.delete<void>(url).pipe(

       catchError((err: any) => {
         console.log(err);
         return throwError(
           () => new Error('LogService.index(): error retrieving log: ' + err)
         );
       })

     )
   }

}
