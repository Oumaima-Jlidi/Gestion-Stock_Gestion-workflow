import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'; // Importez l'opérateur tap depuis RxJS

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private loginUrl = 'http://localhost:8086/api/v1/user/login'; // URL de l'endpoint d'authentification
  public loggedInUserEmail: string | null = null; // Ajoutez cette propriété pour stocker l'email de l'utilisateur connecté

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post(this.loginUrl, body)
      .pipe(
        tap((response: any) => {
          // Assurez-vous que la réponse de l'API est correcte (utilisateur authentifié)
          if (response && response.user && response.user.email) {
            this.loggedInUserEmail = response.user.email;
          }
        })
      );
  }
}
