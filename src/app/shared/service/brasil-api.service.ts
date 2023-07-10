import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Taxa } from '../model/taxa.model';

@Injectable({ providedIn: 'root' })
export class BrasilAPIService {

    apiUrl = 'https://brasilapi.com.br/api/taxas/v1';
    httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      };

    constructor(
        private httpClient: HttpClient
    ) {

    }

    public getTaxas(): Observable<Taxa[]> {
      return this.httpClient.get<Taxa[]>(this.apiUrl)
        .pipe(
          catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
      console.error('Erro na requisição:', error);
      return throwError('Erro na requisição. Tente novamente mais tarde.');
    }
}
