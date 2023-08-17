import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = "";
  email: string = "";
  password: string = "";

  constructor(private http: HttpClient,private router:Router) { }

  save() {
    // Préparez les données du corps à envoyer dans la requête POST
    const bodyData = {
      "username": this.username,
      "email": this.email,
      "password": this.password
    };

    // Effectuez la requête POST vers l'API
    this.http.post("http://localhost:8086/api/v1/user/save", bodyData, { responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User Registered Successfully");
        this.router.navigate(['/login']);

      },
      (error: any) => {
        console.error("An error occurred:", error);
        alert("An error occurred while registering user");
      }
    );
  }
}
