import { NotificationService } from './../services/notification.service';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchCountryField, CountryISO } from 'ngx-intl-tel-input';
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
  sexes: any[] = [
    {value: 'F', viewValue: 'Female'},
    {value: 'M', viewValue: 'Male'}
  ];
  separateDialCode = true;
	SearchCountryField = SearchCountryField;
	CountryISO = CountryISO;
	preferredCountries: CountryISO[] = [CountryISO.UnitedStates, CountryISO.UnitedKingdom];
  errorMessage: any;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private patientService: PatientService,
              private router: Router,
              private notification: NotificationService) { }

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
    this.patient.phone = formValue['phone'].internationalNumber;
    this.patient.countryCode = formValue['phone'].countryCode;
    this.patientService.editPatient(this.patient)
      .subscribe(
        (returned) =>{
          console.log(returned);
          this.notification.openSnackBar('Patient saved successfully', 'Done', 'custom-style-success')
          this.router.navigate(['/patient']);
        },
        (error) => {
          console.error('Request failed with error')
          this.errorMessage = error;
          this.notification.openSnackBar('Error while saving data', 'Done', 'custom-style-error')
        }
      );
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


