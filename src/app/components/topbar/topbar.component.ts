import { Component, OnInit } from '@angular/core';
import { BrasilAPIService } from '../../shared/service/brasil-api.service';
import { Taxa } from './../../shared/model/taxa.model';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

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

  formatNumber(number: number): string {
    if (isNaN(number)) {
      return number.toString();
    }

    const formattedNumber = number.toString().replace('.', ',');
    return formattedNumber;
  }
}
