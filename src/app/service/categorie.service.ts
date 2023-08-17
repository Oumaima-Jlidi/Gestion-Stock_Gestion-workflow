import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  readonly API_URL="http://localhost:8086"
  readonly ENDPOINT_PRODS="/categories"
  constructor(private httpClient:HttpClient) { }

  getCategories():Observable<any>{
    return this.httpClient.get(this.API_URL+this.ENDPOINT_PRODS)
   }

   addCategorie(categorie: any): Observable<any> {
    
    return this.httpClient.post((this.API_URL + this.ENDPOINT_PRODS), categorie);
  }

  updateCategorie(categorie:any):Observable<any>{
    return this.httpClient.put((this.API_URL+this.ENDPOINT_PRODS),categorie);
   }
   getCategorie(categorieId: number): Observable<any> {
     const url = `${this.API_URL}${this.ENDPOINT_PRODS}/${categorieId}`;
     return this.httpClient.get(url);
   }

   deleteCategorie(categorieId: number): Observable<any> {
    const url = `${this.API_URL}${this.ENDPOINT_PRODS}/${categorieId}`;
    return this.httpClient.delete(url);
  }
}
