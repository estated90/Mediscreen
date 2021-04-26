import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Historic } from '../models/historic.model';
import { Patient } from '../models/patient.model';
import { HistoricService } from '../services/historic-service';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-historic-add',
  templateUrl: './historic-add.component.html',
  styleUrls: ['./historic-add.component.scss']
})
export class HistoricAddComponent implements OnInit {

  id = this.route.snapshot.params['id'];
  historicForm!: FormGroup;
  patient: Patient = <Patient>{};
  historic: Historic = <Historic>{};
  

  constructor(private formBuilder: FormBuilder, 
              private patientService: PatientService,
              private historicService: HistoricService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.patientService.getPatientById(+this.id).subscribe(patient => {
      console.log(patient);
      this.patient= patient;
    });
    this.initForm();
  }

  initForm() {
    this.historicForm = this.formBuilder.group({
      patId:['', Validators.required],
      patient:['', Validators.required],
      practitionnerNotesRecommandation: ['', Validators.required],
    })
  }

  onSubmitForm() {
    console.log('adding a new historic')
    const formValue = this.historicForm.value;
    this.historic.patId = this.patient.id;
    this.historic.patient = this.historic.patient;
    this.historic.practitionnerNotesRecommandation = formValue['practitionnerNotesRecommandation'];
    this.historicService.addHistoric(this.historic).subscribe(historic =>{
      console.log(historic);
      this.router.navigate(['/patient', 'historic', this.id]);
    });
  }

  returnToHistoric(){
    this.router.navigate(['/patient', 'historic', this.id]);
}
}
