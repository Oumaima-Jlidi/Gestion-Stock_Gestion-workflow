import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Achat } from '../models/achat.model';

@Injectable({
  providedIn: 'root'
})
export class AchatService {
  readonly API_URL = "http://localhost:8086";
  readonly ENDPOINT_PRODS = "/api/acheter-produit";
  readonly ENDPOINT_ACHATS_BY_PRODUIT = "/api/achats-by-nom-produit";
  readonly ENDPOINT_ACHATS="/api/achats"
  constructor(private http: HttpClient) {}

  acheterProduit(achatData: any): Observable<any> {
    return this.http.post(this.API_URL + this.ENDPOINT_PRODS, achatData);
  }

  getAchatsByProduit(produitId: number): Observable<Achat[]> {
    const url = `${this.API_URL + this.ENDPOINT_ACHATS_BY_PRODUIT}/${produitId}`;
    return this.http.get<Achat[]>(url).pipe(
      map(achats => {
        return achats.map(achat => {
          // Convertir la date au format ISO 8601
          achat.dateAchat = new Date(achat.dateAchat).toISOString();
          return achat;
        });
      })
    );  }

getAchat(achatId :number):Observable<any>{
  const url=` ${this.API_URL}${this.ENDPOINT_ACHATS}/${achatId}`
  return this.http.get(url);
}
deleteAchat(achatId: number): Observable<any> {
  const url = `${this.API_URL}${this.ENDPOINT_ACHATS}/${achatId}`;
  return this.http.delete(url);
}
updateAchat(achat:any):Observable<any>{
  return this.http.put((this.API_URL+this.ENDPOINT_ACHATS),achat);
 }
}
