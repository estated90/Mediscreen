import { HistoricService } from './services/historic-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { PatientService } from './services/patient-service';
import { PatientViewComponent } from './patient-view/patient-view.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddPatientComponent } from './add-patient/add-patient.component';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { PatientEditComponent } from './patient-edit/patient-edit.component'
import { CommonModule } from '@angular/common';
import { HistoricListComponent } from './historic-list/historic-list.component';
import { HistoricAddComponent } from './historic-add/historic-add.component';

const appRoutes: Routes = [
  { path: 'patient', component: PatientViewComponent },
  { path: 'patient/add', component: AddPatientComponent },
  { path: 'patient/edit/:id', component: PatientEditComponent},
  { path: 'patient/historic/:id', component: HistoricListComponent},
  { path: 'patient/historic/add/:id', component: HistoricAddComponent},
  { path: '', component: PatientViewComponent },
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  declarations: [
    AppComponent,
    PatientViewComponent,
    AddPatientComponent,
    FourOhFourComponent,
    PatientEditComponent,
    HistoricListComponent,
    HistoricAddComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatIconModule
  ],
  providers: [
    PatientService,
    HistoricService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
