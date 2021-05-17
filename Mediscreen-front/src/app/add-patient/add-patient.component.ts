import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Router } from '@angular/router';
import PhoneNumber from 'awesome-phonenumber';
import { ConfigurationOptions, ContentOptionsEnum, OutputOptionsEnum } from "intl-input-phone";
import { Patient } from '../models/patient.model';
import { ISO_3166_1_CODES } from '../phone/country-codes';
import { PatientService } from '../services/patient-service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.scss']
})
export class AddPatientComponent implements OnInit {

  patientForm!: FormGroup;

  configOption1: ConfigurationOptions;

  countyCodes = ISO_3166_1_CODES;

  constructor(private formBuilder: FormBuilder, 
              private patientService: PatientService, 
              private router: Router, private fb: FormBuilder) {    
    this.configOption1 = new ConfigurationOptions();
    this.configOption1.OutputFormat = OutputOptionsEnum.Number
    this.configOption1.SelectorClass = "OptionType3";
      this.configOption1.OptionTextTypes = [];
    this.configOption1.OptionTextTypes.push(ContentOptionsEnum.Flag);
    this.configOption1.OptionTextTypes.push(ContentOptionsEnum.CountryName);
    this.configOption1.OptionTextTypes.push(ContentOptionsEnum.CountryPhoneCode);
               };

  submitted = false;

  patient: Patient = <Patient>{};

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
      phone: ['', Validators.required]
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
    this.patient.phone = formValue['phone'];
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
