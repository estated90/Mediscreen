import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Patient } from '../models/patient.model';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.scss']
})
export class PatientEditComponent implements OnInit, OnDestroy {

  id = this.route.snapshot.params['id'];
  patientForm!: FormGroup;
  patient!: Patient;
  doAsyncObservableThing!: Observable<unknown>;
  destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private patientService: PatientService,
              private router: Router) { }

  ngOnDestroy(){
    this.destroy$.next(true);
    // Unsubscribe from the subject
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    this.patientService.getPatientById(+this.id).subscribe(patient => {
      this.patient= patient;
      this.updateForm();
    });
    this.initForm();

  }

  initForm() {
    this.patientForm = this.formBuilder.group({
      family:['', Validators.required],
      given:['', Validators.required],
      dob: ['', Validators.required],
      sex:['', Validators.required],
      address:['', Validators.required],
      phone: ['', Validators.required]
    })
  }

  onSubmitForm() {
    console.log('Updating a patient ' + this.id )
    const formValue = this.patientForm.value;
    this.patient.family = formValue['family'];
    this.patient.given = formValue['given'];
    this.patient.dob = formValue['dob'];
    this.patient.sex = formValue['sex'];
    this.patient.address = formValue['address'];
    this.patient.phone = formValue['phone'];
    this.patientService.editPatient(this.patient).subscribe(returned =>{
      console.log(returned);
      this.router.navigate(['/patient']);
    });
  }

  updateForm(){
    this.patientForm.patchValue({'family': this.patient.family})
    this.patientForm.patchValue({'given': this.patient.given})
    this.patientForm.patchValue({'dob': this.patient.dob})
    this.patientForm.patchValue({'sex': this.patient.sex})
    this.patientForm.patchValue({'address': this.patient.address})
    this.patientForm.patchValue({'phone': this.patient.phone})
  }

  returnToPatient(){
    this.patientService.returnToPatient();
  }

}


