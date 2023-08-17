import { Component } from '@angular/core';
import { Categorie } from '../models/categorie.model';
import { CategorieService } from '../service/categorie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-categorie',
  templateUrl: './add-categorie.component.html',
  styleUrls: ['./add-categorie.component.css']
})
export class AddCategorieComponent {
  nouveauCat: Categorie = {
    nom: ''};
    constructor(private categorieservice: CategorieService , private router: Router ) {
  
    }

    ajouterCategorie(): void {
     
      this.categorieservice.addCategorie(this.nouveauCat).subscribe(
        (response:any) => {
          console.log('Categorie ajouté avec succès :', response);
          this.nouveauCat = {
           
            nom: '',
           
          };
          this.router.navigate(['/categories']);
  
        },
        (error:any) => {
          console.error('Erreur lors de l\'ajout du Categorie :', error);
        }
      );
    }
}
