import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Transaction } from 'src/app/shared/model/transaction/transaction.model';
import { AssetAllocationAPIService } from 'src/app/shared/service/assetAllocation/assetAllocation-api.service';

@Component({
  selector: 'app-acoes',
  templateUrl: './acoes.component.html',
  styleUrls: ['./acoes.component.css']
})
export class AcoesComponent implements OnInit {

  transactions$: Observable<Transaction[]> | undefined;

  newTransaction: Transaction = {
    quantidade: 10,
    id: 0
  };

  constructor(private assetAllocationService: AssetAllocationAPIService) { }

  ngOnInit(): void {
    this.loadTransactions();
  }

  salvaTransaction(): void {
    this.assetAllocationService.saveTransaction(this.newTransaction)
      .pipe(
        tap(response => console.log('Transação salva com sucesso:', response)),
        catchError(error => {
          console.error('Erro ao salvar transação:', error);
          return [];
        })
      )
      .subscribe();
  }

  loadTransactions(): void {
    this.transactions$ = this.assetAllocationService.getTransactions();
  }
}
