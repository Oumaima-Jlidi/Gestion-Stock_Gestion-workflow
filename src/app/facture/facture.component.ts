import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AchatService } from '../service/achat.service';
import { Achat } from '../models/achat.model';

@Component({
  selector: 'app-facture',
  templateUrl: './facture.component.html',
  styleUrls: ['./facture.component.css']
})
export class FactureComponent implements OnInit {
  achatId: number = -1;
  achat: Achat | null = null; // Variable pour stocker les données de l'achat
  montantTotal: number=0; // Variable pour stocker le montant total
  montantHTVA:number=0;
  montantTTC:number=0;
  constructor(
    private route: ActivatedRoute,
    private achatService: AchatService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const achatIdParam = params.get('id');
      if (achatIdParam !== null) {
        this.achatId = +achatIdParam; // Convertit en nombre
        // Appeler le service pour obtenir les détails de l'achat par son ID
        this.achatService.getAchat(this.achatId).subscribe((achat) => {
          this.achat = achat;
          this.calculerMontantTVA();
          this.calculerMontantHTVA();
          this.calculerMontantTTC();

        },
        (error: any) => {
          console.error('Erreur lors de la récupération des détails de l\'achat :', error);
        });
      }
    });
  }
  calculerMontantTVA() {
    // Implémentez la logique pour calculer le montant total à partir des données de la facture
    if (this.achat && this.achat.produit) {
      const prixUnitaire = this.achat.produit.prix;
      const Qunatite = this.achat.quantite;

      const TVA = 0.19;

      // Calculer le montant total en ajoutant la TVA au prix unitaire
      this.montantTotal = (prixUnitaire * TVA )*Qunatite;
    }
  }

  calculerMontantHTVA() {
    // Implémentez la logique pour calculer le montant total à partir des données de la facture
    if (this.achat && this.achat.produit) {
      const prixUnitaire = this.achat.produit.prix;
      const Qunatite = this.achat.quantite;


      // Calculer le montant total en ajoutant la TVA au prix unitaire
      this.montantHTVA = (prixUnitaire  *Qunatite);
    }
  }

  calculerMontantTTC() {
    // Implémentez la logique pour calculer le montant total à partir des données de la facture
    if (this.achat && this.achat.produit) {
      const prixUnitaire = this.achat.produit.prix;
      const Qunatite = this.achat.quantite;
      const TVA = this.achat.produit.tva;

      // Calculer le montant total en ajoutant la TVA au prix unitaire
      this.montantTTC = (prixUnitaire  *Qunatite)+TVA;
    }
  }
}