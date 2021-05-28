import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Historic } from '../models/historic.model';
import { Patient } from '../models/patient.model';
import { HistoricService } from '../services/historic-service';
import { NotificationService } from '../services/notification.service';
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
  loading: boolean = false;
  errorMessage: any;
  

  constructor(private formBuilder: FormBuilder, 
              private patientService: PatientService,
              private historicService: HistoricService,
              private route: ActivatedRoute,
              private router: Router,
              private notification: NotificationService
              ) { }

  ngOnInit(): void {
    this.patientService.getPatientById(+this.id)
    .subscribe(
      (patient) => {
      console.log(patient);
      this.patient= patient;
      },
      (error) => {
        console.error('Request failed with error')
        this.errorMessage = error;
        this.loading = false;
      }
    );
    this.initForm();
  }

  initForm() {
    this.historicForm = this.formBuilder.group({
      patId:[''],
      patient:[''],
      practitionnerNotesRecommandation: ['', Validators.required],
    })
  }

  onSubmitForm() {
    console.log('adding a new historic')
    const formValue = this.historicForm.value;
    this.historic.patId = this.patient.id;
    this.historic.patient = this.patient.family + ' ' + this.patient.given;
    this.historic.practitionnerNotesRecommandation = formValue['practitionnerNotesRecommandation'];
    this.historicService.addHistoric(this.historic)
      .subscribe(
        (historic) =>{
          console.log(historic);
          this.notification.openSnackBar('Historic saved successfully', 'Done', 'custom-style-success')
          this.router.navigate(['/patient', 'historic', this.id]);
        },
        (error) => {
          console.error('Request failed with error')
          this.errorMessage = error;
          this.notification.openSnackBar('Error while saving data', 'Done', 'custom-style-error')
        }
      )
    ;
    
  }

  returnToHistoric(){
    this.router.navigate(['/patient', 'historic', this.id]);
  }

}
