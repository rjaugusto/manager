import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Transaction } from '../../model/transaction/transaction.model';

@Injectable({ providedIn: 'root' })
export class AssetAllocationAPIService {

    apiUrl = 'http://localhost:8080/transaction';

    httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      };

    constructor(private httpClient: HttpClient) {

    }

    public getTransactions(): Observable<Transaction[]> {
      return this.httpClient.get<Transaction[]>(this.apiUrl)
        .pipe(
          catchError(this.handleError)
        );
    }

    public saveTransaction(transaction: Transaction): Observable<any> {
      return this.httpClient.post<any>(this.apiUrl, transaction, this.httpOptions)
        .pipe(
          catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
      console.error('Erro na requisição:', error);
      return throwError('Erro na requisição. Tente novamente mais tarde.');
    }
}
