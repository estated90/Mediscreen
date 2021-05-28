import { NotificationService } from './services/notification.service';
import { RisksService } from './services/risks.service';
import { HistoricService } from './services/historic-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule  } from '@angular/material/toolbar';
import {MatTableModule} from '@angular/material/table';
import { NgxIntlTelInputModule } from 'ngx-intl-tel-input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PatientService } from './services/patient-service';
import { PatientViewComponent } from './patient-view/patient-view.component';
import { AddPatientComponent } from './patient-add/patient-add.component';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { PatientEditComponent } from './patient-edit/patient-edit.component'
import { HistoricListComponent } from './historic-list/historic-list.component';
import { HistoricAddComponent } from './historic-add/historic-add.component';
import { HistoricEditComponent } from './historic-edit/historic-edit.component';
import { EvaluateRisksComponent } from './evaluate-risks/evaluate-risks.component';
import { MatSortModule } from '@angular/material/sort';


const appRoutes: Routes = [
  { path: 'patient', component: PatientViewComponent },
  { path: 'Patient', component: PatientViewComponent },
  { path: 'patient/add', component: AddPatientComponent },
  { path: 'patient/edit/:id', component: PatientEditComponent},
  { path: 'patient/historic/:id', component: HistoricListComponent},
  { path: 'patient/historic/add/:id', component: HistoricAddComponent},
  { path: 'patient/historic/edit/:id', component: HistoricEditComponent},
  { path: 'patient/evaluate/:id', component: EvaluateRisksComponent},
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
    HistoricAddComponent,
    HistoricEditComponent,
    EvaluateRisksComponent,
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
    BsDropdownModule.forRoot(),
    BrowserAnimationsModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatIconModule,
    MatTableModule,
    MatSortModule,
    MatSelectModule,
    MatToolbarModule,
    MatSnackBarModule,
    NgxIntlTelInputModule
  ],
  providers: [
    PatientService,
    HistoricService,
    RisksService,
    NotificationService,
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
