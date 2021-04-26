import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Historic } from '../models/historic.model';

@Injectable()
export class HistoricService {

    stringJson: any;
    constructor(private httpClient: HttpClient, private router: Router){};

    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    }

    private apiUrl: string = 'http://localhost:8082/historic';

    getPatientHistoric(id: number): Observable<Historic[]>{
        return this.httpClient.get<Historic[]>(this.apiUrl + '/' + id, { responseType: 'json' }).pipe(retry(3), catchError(this.handleError));
    }

    addHistoric(historic:Historic): Observable<Historic>{
        console.log('Sending new historic to service '+historic)
        let API_URL = `${this.apiUrl}/create`;
        this.stringJson = JSON.stringify(historic);
        return this.httpClient.put<Historic>(API_URL, this.stringJson,this.httpOptions).pipe(retry(1), catchError(this.handleError));
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
