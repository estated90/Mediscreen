import { HistoricService } from './../services/historic-service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../models/patient.model';

@Component({
  selector: 'app-historic-list',
  templateUrl: './historic-list.component.html',
  styleUrls: ['./historic-list.component.scss']
})
export class HistoricListComponent implements OnInit {

  patient: Patient = <Patient>{};
  id = this.route.snapshot.params['id'];

  constructor(private historicService: HistoricService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  goToNewHistoric(id: number){
    this.router.navigate(['/historic', 'add', id]);
  }

}
