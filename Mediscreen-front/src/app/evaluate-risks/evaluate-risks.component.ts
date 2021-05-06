import { RisksService } from './../services/risks.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../models/patient.model';
import { HistoricService } from '../services/historic-service';
import { PatientService } from '../services/patient-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-evaluate-risks',
  templateUrl: './evaluate-risks.component.html',
  styleUrls: ['./evaluate-risks.component.scss']
})
export class EvaluateRisksComponent implements OnInit {

  id = this.route.snapshot.params['id'];
  patient: Patient = <Patient>{};
  value:any;
  obsTextMsg!: Observable<string>;

  constructor(private patientService: PatientService,
              private risksService: RisksService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.patientService.getPatientById(+this.id).subscribe(patient => {
      console.log(patient);
      this.patient= patient;
    });
    this.risksService.getRisks(this.id).subscribe(
      msg => {console.log(msg);
      this.value=msg;
      });
  }

}
