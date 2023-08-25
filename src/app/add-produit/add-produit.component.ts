import { Component, OnInit } from '@angular/core';
import { ProduitService } from '../service/produit.service';
import { CategorieService } from '../service/categorie.service';
import { Router } from '@angular/router';
import { Produit } from '../models/produit.model';
import { Categorie } from '../models/categorie.model'; // Importez le modèle Categorie correctement

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent implements OnInit {
  categories: any[] = [];
  nouveauProduit: Produit = {
    reference: '',
    nom: '',
    quantite: 0,
    prix: 0,
    tva: 0,
    timbre: 0.600,
    prix_ttc: 0,
    description: '',
    categorie: {} as Categorie   
  };

  constructor(
    private produitService: ProduitService,
    private router: Router,
    private categorieservice: CategorieService
  ) {}

  ngOnInit() {
    this.loadCategories();
  }

  loadCategories() {
    this.categorieservice.getCategories().subscribe(
      (data) => {
        this.categories = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des catégories :', error);
      }
    );
  }

  calculerMontantTVA(): number {
    return this.nouveauProduit.prix * 0.19;
  }

  calculerMontantTTC(): number {
    const prixTTC = (this.nouveauProduit.prix + (this.nouveauProduit.prix * 0.19) + this.nouveauProduit.timbre) * this.nouveauProduit.quantite;
    return prixTTC;
  }

  ajouterProduit(): void {
    this.nouveauProduit.tva = this.calculerMontantTVA();
    this.nouveauProduit.prix_ttc = this.calculerMontantTTC();

    this.produitService.addProduit(this.nouveauProduit).subscribe(
      (response) => {
        console.log('Produit ajouté avec succès :', response);
        this.clearNouveauProduit();
        this.router.navigate(['/produits']);
      },
      (error) => {
        console.error('Erreur lors de l\'ajout du produit :', error);
      }
    );
  }

  clearNouveauProduit() {
    this.nouveauProduit = {
      reference: '',
      nom: '',
      quantite: 0,
      prix: 0,
      tva: 0,
      timbre: 0.600,
      prix_ttc: 0,
      description: '',
      categorie: {} as Categorie    };
  }
}