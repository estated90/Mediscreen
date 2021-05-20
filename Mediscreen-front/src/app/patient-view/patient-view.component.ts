import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../models/patient.model';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-patient-view',
  templateUrl: './patient-view.component.html',
  styleUrls: ['./patient-view.component.scss']
})
export class PatientViewComponent implements OnInit {

	patients!: any[];

  constructor(private patientService: PatientService, private router: Router) {
  }

  ngOnInit(): void {
    this.patientService.getPatientFromServer().subscribe((patients: Patient[]) => {
      console.log('Getting list of patients');
      this.patients= patients;
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

}
