import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { BrazilianStock } from '../../model/brazilianStock/brazilianStock.model';

@Injectable({ providedIn: 'root' })
export class AssetAllocationAPIService {

    apiUrl = 'http://localhost:8080/brazilianStock';

    httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      };

    constructor(private httpClient: HttpClient) {

    }

    public getTransactions(): Observable<BrazilianStock[]> {
      return this.httpClient.get<BrazilianStock[]>(this.apiUrl)
        .pipe(
          catchError(this.handleError)
        );
    }

    public saveTransaction(transaction: BrazilianStock): Observable<any> {
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
