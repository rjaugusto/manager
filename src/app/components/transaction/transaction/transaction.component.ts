import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AssetType } from 'src/app/shared/model/transaction/AssetType.enum';
import { TransactionType } from 'src/app/shared/model/transaction/TransactionType.enum';
import { AssetAllocationAPIService } from 'src/app/shared/service/assetAllocation/transaction.service';
import { Transaction } from '../../../shared/model/transaction/transaction.model';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  transactions$: Observable<Transaction[]> | undefined;

  transaction: Transaction = {
    id: 0,
    price:0,
    quantity: 0,
    transactionType: TransactionType.BUY,
    assetType: AssetType.BRAZILIAN_STOCK,
    transactionDate: new Date()
  };

  constructor(private assetAllocationService: AssetAllocationAPIService) { }

  ngOnInit(): void {
    this.loadTransactions();
  }

  salvaTransaction(): void {
    this.assetAllocationService.saveTransaction(this.transaction)
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
