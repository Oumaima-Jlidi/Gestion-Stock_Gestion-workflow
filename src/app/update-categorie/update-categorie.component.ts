import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '../service/categorie.service';

@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css']
})
export class UpdateCategorieComponent implements OnInit {
  categorieId: number;
  categorie: any = {}; // Modifiez le type selon votre modèle de produit
  constructor(private route: ActivatedRoute,private categorieservice:CategorieService , private router: Router) {
    this.categorieId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.loadCategorie();
  }

  loadCategorie(): void {
    this.categorieservice.getCategorie(this.categorieId).subscribe(
      (data:any) => {
        this.categorie = data; // Remplacez avec le modèle de votre produit
      },
      (error:any) => {
        console.error('Erreur lors du chargement du categorie :', error);
      }
    );
  }

  updateCategorie(): void {
    this.categorieservice.updateCategorie(this.categorie).subscribe(
      (response:any) => {
        console.log('categorie mis à jour avec succès :', response);
        this.router.navigate(['/categories']); // Redirige vers la liste des produits après la mise à jour
      },
      (error:any) => {
        console.error('Erreur lors de la mise à jour du categorie :', error);
      }
    );
  }
}
