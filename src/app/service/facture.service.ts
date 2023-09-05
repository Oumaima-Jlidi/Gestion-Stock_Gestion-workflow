import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facture } from '../models/facture.model';
import { FactureRequest } from '../models/FactureRequest.model';

@Injectable({
  providedIn: 'root'
})
export class FactureService {
  private baseUrl = 'http://localhost:8086/api/factures'; // Update the URL to your API endpoint

  constructor(private http: HttpClient) {}

  createFactureForAchat(achatId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/create-facture-for-achat/${achatId}`, {});
  }
  getFactureByAchatId(achatId: number,produitId:number): Observable<Facture> {
    const url = `${this.baseUrl}/achat/${achatId}/${produitId}`;
    return this.http.get<Facture>(url);
  }
  ajouterFacture(facture: FactureRequest): Observable<Facture> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };

    return this.http.post<Facture>(
      `${this.baseUrl}/ajouter`,
      facture,
      httpOptions
    );
  }
  getFactureById(id: number): Observable<Facture> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Facture>(url);
  }
}
