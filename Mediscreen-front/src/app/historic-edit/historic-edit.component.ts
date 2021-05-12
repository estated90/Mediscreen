import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Historic } from '../models/historic.model';
import { Patient } from '../models/patient.model';
import { HistoricService } from '../services/historic-service';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-historic-edit',
  templateUrl: './historic-edit.component.html',
  styleUrls: ['./historic-edit.component.scss']
})
export class HistoricEditComponent implements OnInit {

  id = this.route.snapshot.params['id'];
  historicForm!: FormGroup;
  patient: Patient = <Patient>{};
  historic: Historic = <Historic>{};
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(private formBuilder: FormBuilder,
    private historicService: HistoricService,
    private route: ActivatedRoute,
    private patientService: PatientService,
    private router: Router) { }

  ngOnInit(): void {
    this.historicService.getHistoricById(this.id).subscribe(historic => {
      this.historic= historic;
      this.updateForm();
    });
    this.initForm();
  }

  ngOnDestroy(){
    this.destroy$.next(true);
    // Unsubscribe from the subject
    this.destroy$.unsubscribe();
  }

  initForm() {
    this.historicForm = this.formBuilder.group({
      id:[''],
      patId:[''],
      patient:[''],
      createdAt:[''],
      practitionnerNotesRecommandation: ['', Validators.required],
    })
  }

  onSubmitForm() {
    console.log('adding a new historic')
    const formValue = this.historicForm.value;
    this.historic.practitionnerNotesRecommandation = formValue['practitionnerNotesRecommandation'];
    this.historicService.editHistoric(this.historic).subscribe(historic =>{
      console.log(historic);
      this.router.navigate(['/patient', 'historic', this.historic.patId]);
    });
  }

  updateForm(){
    this.historicForm.patchValue({'patId': this.historic.patId})
    this.historicForm.patchValue({'patient': this.historic.patient})
    this.historicForm.patchValue({'id': this.historic.id})
    this.historicForm.patchValue({'createdAt': this.historic.createdAt})
    this.historicForm.patchValue({'practitionnerNotesRecommandation': this.historic.practitionnerNotesRecommandation})
  }

  returnToHistoric(){
    this.router.navigate(['/patient', 'historic', this.historic.patId]);
  }

}
