import { Component } from '@angular/core';
import { ProduitService } from '../service/produit.service';
import { Router } from '@angular/router';
import { Produit } from '../models/produit.model';



@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent  {
  nouveauProduit: Produit = {
    reference: '',
    nom: '',
    quantite: 0,
    prix: 0,
    tva: 0,
    timbre: 0.600,
    prix_ttc: 0,
    description: '',
  };
  constructor(private produitService: ProduitService , private router: Router ) {
    this.nouveauProduit.tva=this.calculerMontantTVA();

  }

 

  calculerMontantTVA(): number {
     const prixTVA = this.nouveauProduit.prix * 0.19;
    return prixTVA;

  }
  calculerMontantTTC(): number {
    const prixTTC = (this.nouveauProduit.prix +(this.nouveauProduit.prix*0.19) + this.nouveauProduit.timbre)*this.nouveauProduit.quantite;
    return prixTTC;
  }

  ajouterProduit(): void {
    this.nouveauProduit.tva = this.calculerMontantTVA();
    this.nouveauProduit.prix_ttc = this.calculerMontantTTC();

    this.produitService.addProduit(this.nouveauProduit).subscribe(
      (response) => {
        console.log('Produit ajouté avec succès :', response);
        this.nouveauProduit = {
          reference: '',
          nom: '',
          quantite: 0,
          prix: 0,
          tva:0,
          timbre:0.600,

          prix_ttc: 0,
          description: '',
        };
        this.router.navigate(['/produits']);

      },
      (error) => {
        console.error('Erreur lors de l\'ajout du produit :', error);
      }
    );
  }
}
