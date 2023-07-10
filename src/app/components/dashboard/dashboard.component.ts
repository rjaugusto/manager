import { Component, OnInit } from '@angular/core';
import { Taxa } from 'src/app/shared/model/taxa.model';
import { BrasilAPIService } from '../../shared/service/brasil-api.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  taxas: Taxa[] = [];
  erro: string | null = null;

  constructor(private brasilAPIService: BrasilAPIService) { }

  ngOnInit(): void {
    this.brasilAPIService.getTaxas()
      .subscribe(
        response => {
          this.taxas = response;
        },
        error => {
          this.erro = error;
        }
      );
  }

}
