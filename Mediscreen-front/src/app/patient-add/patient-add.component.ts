import { CountryCodes } from './../phone/country-codes';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { SearchCountryField, CountryISO } from 'ngx-intl-tel-input';
import { Patient } from '../models/patient.model';
import { ISO_3166_1_CODES } from '../phone/country-codes';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.scss']
})
export class AddPatientComponent implements OnInit {

  patientForm!: FormGroup;
  countyCodes = ISO_3166_1_CODES;
  submitted = false;
  patient: Patient = <Patient>{};
  sexes: any[] = [
    {value: 'F', viewValue: 'Female'},
    {value: 'M', viewValue: 'Male'}
  ];
  separateDialCode = true;
	SearchCountryField = SearchCountryField;
	CountryISO = CountryISO;
	preferredCountries: CountryISO[] = [CountryISO.UnitedStates, CountryISO.UnitedKingdom];

  constructor(private formBuilder: FormBuilder, 
              private patientService: PatientService, 
              private router: Router, private fb: FormBuilder) {};

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.patientForm = this.formBuilder.group({
      family:['', [Validators.required, Validators.minLength(2)]],
      given:['', [Validators.required, Validators.minLength(2)]],
      dob: ['', Validators.required],
      sex:['', Validators.required],
      address:['', [Validators.required, Validators.minLength(10), Validators.maxLength(150)]],
      phone: new FormControl('', [Validators.required])
    })
  }

  onSubmitForm() {
    console.log('adding a new patient')
    this.submitted = true;
    const formValue = this.patientForm.value;
    this.patient.family = formValue['family'];
    this.patient.given = formValue['given'];
    this.patient.dob = formValue['dob'];
    this.patient.sex = formValue['sex'];
    this.patient.address = formValue['address'];
    this.patient.phone = formValue['phone'].internationalNumber;
    this.patient.countryCode = formValue['phone'].countryCode;
    this.patientService.addPatient(this.patient).subscribe(patient =>{
      console.log(patient);
      this.router.navigate(['/patient']);  
    });

  }
  returnToPatient(){
    this.patientService.returnToPatient();
  }

  //only number will be add
  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  get f() { 
    return this.patientForm.controls; 
  }
}

