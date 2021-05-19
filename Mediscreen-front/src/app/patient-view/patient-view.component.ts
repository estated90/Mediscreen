import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Patient } from '../models/patient.model';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.scss']
})
export class PatientViewComponent implements OnInit, AfterViewInit{

	patients!: any[];
  listDisplay: boolean = false;
  dataSource!: MatTableDataSource<Patient[]>;

  constructor(private patientService: PatientService, private router: Router) {
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.patientService.getPatientFromServer().subscribe((patients: Patient[]) => {
      console.log('Getting list of patients');
      this.patients= patients;
       if(this.patients.length!=0){
        this.listDisplay = true;
        this.dataSource=new MatTableDataSource(this.patients);
      }
    });
  }

  goToNewPatient(){
    this.router.navigate(['/patient', 'add']);
  }

  goToPatient(id: number){
    this.router.navigate(['/patient', id]);
  }

  goToEditPatient(id: number){
    this.router.navigate(['/patient','edit', id]);
  }

  goToHistoric(id: number){
    this.router.navigate(['/patient', 'historic', id]);
  }

  goToEvaluation(id: number){
    this.router.navigate(['/patient', 'evaluate', id]);
  }

  displayedColumns: string[] = ['firstName', 'lastName', 'dob', 'sex','address','phone', 'actions'];

  @ViewChild(MatSort) sort!: MatSort;

}
