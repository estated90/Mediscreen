import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Patient } from '../models/patient.model';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.scss']
})
export class AddPatientComponent implements OnInit {

  patientForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private patientService: PatientService, private router: Router) { };

  patient: Patient = <Patient>{};

  ngOnInit() {
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
    console.log('adding a new patient')
    const formValue = this.patientForm.value;
    this.patient.family = formValue['family'];
    this.patient.given = formValue['given'];
    this.patient.dob = formValue['dob'];
    this.patient.sex = formValue['sex'];
    this.patient.address = formValue['address'];
    this.patient.phone = formValue['phone'];
    this.patientService.addPatient(this.patient).subscribe(patient =>{
      console.log(patient);
      this.router.navigate(['/patient']);
    });

  }
  returnToPatient(){
    this.patientService.returnToPatient();
}
}
