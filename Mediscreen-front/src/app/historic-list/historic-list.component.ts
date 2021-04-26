import { HistoricService } from './../services/historic-service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../models/patient.model';
import { Historic } from '../models/historic.model';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-historic-list',
  templateUrl: './historic-list.component.html',
  styleUrls: ['./historic-list.component.scss']
})
export class HistoricListComponent implements OnInit {

  patient: Patient = <Patient>{};
  id = this.route.snapshot.params['id'];
  historics!: any[];

  constructor(private patientService: PatientService, private historicService: HistoricService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.historicService.getPatientHistoric(this.id).subscribe((historics: Historic[]) => {
      console.log('Getting patient list of history');
      this.historics= historics;
    });
  }

  goToNewHistoric(){
    this.router.navigate(['/patient', 'historic', 'add', this.id]);
  }

  returnToPatient(){
    this.patientService.returnToPatient();
  }

}
