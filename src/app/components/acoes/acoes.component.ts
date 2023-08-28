import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { BrazilianStock } from 'src/app/shared/model/brazilianStock/brazilianStock.model';
import { TransactionType } from 'src/app/shared/model/transaction/TransactionType.enum';
import { AssetAllocationAPIService } from 'src/app/shared/service/assetAllocation/brazilianStock.service';

@Component({
  selector: 'app-acoes',
  templateUrl: './acoes.component.html',
  styleUrls: ['./acoes.component.css']
})
export class AcoesComponent implements OnInit {

  constructor(private assetAllocationService: AssetAllocationAPIService) { }

  ngOnInit(): void {
    this.loadTransactions();
  }

  transactions$: Observable<BrazilianStock[]> | undefined;

  brazilianStock: BrazilianStock = {
    price:0,
    quantity: 0,
    transactionType: TransactionType.BUY,
    transactionDate: new Date(),
    tax:0,
    ticker:""
  };

  total:number = 0;

  somaTotal(price:number,quantity:number,tax:number){
    this.total = (price*quantity)+tax;
  }

  loadTransactions(): void {
      this.transactions$ = this.assetAllocationService.getTransactions();
    }

    salvaTransaction(): void {
      this.assetAllocationService.saveTransaction(this.brazilianStock)
        .pipe(
          tap(response => console.log('Brazilian Stock salva com sucesso:', response)),
          catchError(error => {
            console.error('Erro ao salvar transação:', error);
            return [];
          })
        )
        .subscribe();
        this.loadTransactions();
    }


}
