import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Historic } from '../models/historic.model';

@Injectable({
  providedIn: 'root'
})
export class RisksService {

  stringJson: any;
  constructor(private httpClient: HttpClient, private router: Router){};
  private historic!:Observable<Historic>;

  // Http Options
  httpOptions = {
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'accept': 'text/plain',
          'Access-Control-Allow-Origin':'origin-list'
        })
  }

  private apiUrl: string = 'http://localhost:8080/assess';

  getRisks(id: number): Observable<string> {
    let API_URL = `${this.apiUrl}/${id}`;
    return this.httpClient.get(API_URL, { responseType: 'text' });
  }

  // Handle Errors
  handleError(error: HttpErrorResponse) {
      let errorMessage = 'Unknown error!';
      if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
      } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
      console.log(errorMessage);
      return throwError(errorMessage);
}

}
