import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from '../service/produit.service';
import { CategorieService } from '../service/categorie.service';

@Component({
  selector: 'app-update-produit',
  templateUrl: './update-produit.component.html',
  styleUrls: ['./update-produit.component.css']
})
export class UpdateProduitComponent implements OnInit {
  produitId: number;
  produit: any = {}; // Modifiez le type selon votre modèle de produit
  categories: any[] = [];

  constructor(private route: ActivatedRoute, private produitService: ProduitService, private router: Router,private categorieservice:CategorieService) {
    this.produitId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.loadProduit();
    this.loadCategories(); // Chargez la liste des catégories disponibles

  }

  loadProduit(): void {
    this.produitService.getProduit(this.produitId).subscribe(
      (data:any) => {
        this.produit = data; // Remplacez avec le modèle de votre produit
      },
      (error:any) => {
        console.error('Erreur lors du chargement du produit :', error);
      }
    );
  }
  loadCategories(): void {
    this.categorieservice.getCategories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error: any) => {
        console.error('Erreur lors du chargement des catégories :', error);
      }
    );
  }

  updateProduit(): void {
    this.produitService.updateProduit(this.produit).subscribe(
      (response) => {
        console.log('Produit mis à jour avec succès :', response);
        this.router.navigate(['/produits']); // Redirige vers la liste des produits après la mise à jour
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du produit :', error);
      }
    );
  }
}
