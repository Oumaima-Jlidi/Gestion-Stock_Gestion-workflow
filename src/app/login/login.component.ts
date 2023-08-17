import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth-service.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        // Stockez le token dans le localStorage ou utilisez un gestionnaire de state (ex. NgRx)
        localStorage.setItem('token', response.token);
       console.log(this.email);
        // Redirigez l'utilisateur vers une autre page (par exemple, la liste des produits)
        this.router.navigate(['/produits']);
      },
      error => {
        console.error('Erreur:', error);
        alert("l'email et mot de passe incorrect");

      }
    );
  }
}
