import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, Subject, throwError } from "rxjs";
import { Patient } from "../models/patient.model";
import { retry, catchError } from 'rxjs/operators';

@Injectable()
export class PatientService {

  stringJson: any;
  constructor(private httpClient: HttpClient, private router: Router){};

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  private apiUrl: string = 'http://localhost:8081/patient';

  getPatientById(id: number): Observable<Patient>{
    return this.httpClient.get<Patient>(this.apiUrl + '/' + id, { responseType: 'json' }).pipe(retry(3), catchError(this.handleError));
  }

  getPatientFromServer(): Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(this.apiUrl+'/list', {responseType: 'json'}).pipe(retry(3), catchError(this.handleError));
  }

  addPatient(patient:Patient): Observable<Patient>{
    console.log('Sending new patient to service '+patient)
    let API_URL = `${this.apiUrl}/add`;
    this.stringJson = JSON.stringify(patient);
    return this.httpClient.put<Patient>(API_URL, this.stringJson,this.httpOptions).pipe(retry(1), catchError(this.handleError));
  }

  editPatient(patient:Patient): Observable<Patient>{
    console.log('Sending new patient to service '+patient);
    let API_URL = `${this.apiUrl}/update`;
    this.stringJson = JSON.stringify(patient);
    return this.httpClient.post<Patient>(API_URL, this.stringJson, this.httpOptions).pipe(retry(1), catchError(this.handleError));
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

  returnToPatient(){
    this.router.navigate(['/patient']);
  }

}

