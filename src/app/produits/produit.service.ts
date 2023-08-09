import { Injectable } from '@angular/core';
import {HttpClient}   from  '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
readonly API_URL="http://localhost:8089"
readonly ENDPOINT_PRODS="/produits"
  constructor(private httpClient:HttpClient) {

   }
   getProduits():Observable<any>{
    return this.httpClient.get(this.API_URL+this.ENDPOINT_PRODS)
   }
}
