import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FactureService } from '../service/facture.service';
import { Facture } from '../models/facture.model';
import { FactureRequest } from '../models/FactureRequest.model';
import { PdfService } from '../service/pdf.service';

@Component({
  selector: 'app-facture',
  templateUrl: './facture.component.html',
  styleUrls: ['./facture.component.css']
})
export class FactureComponent implements OnInit {
  achatId: number = -1;
  produitId: number = -1;
  facture: any; // Variable pour stocker les données de la facture
  
  dateFacture: Date = new Date();
  errorMessage: string = '';
  successMessage: string = '';
  montantTotal: number=-1; // Variable pour stocker le montant total

  constructor(
    private route: ActivatedRoute,
    private factureService: FactureService,
    private pdfService:PdfService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const achatIdParam = params.get('achatId');
      const produitIdParam = params.get('produitId');
      
      if (achatIdParam !== null && produitIdParam !== null) {
        this.achatId = +achatIdParam; // Convertit en nombre
        this.produitId = +produitIdParam; // Convertit en nombre
      }
   
    });
    this.calculerMontantTotal();

    const factureId = this.route.snapshot.params['id'];
    const achatId = this.achatId; // Récupérez l'ID de l'achat ici
console.log(this.produitId)
    // Appeler le service pour obtenir les détails de la facture par son ID
    this.factureService.getFactureById(factureId).subscribe((facture) => {
      this.facture = facture;
      
       // Affectez la facture récupérée à 'facture'
       console.log('Données de l\'achat reçues :', facture.achat);

    },
      (error: any) => {
        console.error('Erreur lors de la récupération des détails de la facture :', error);
      }
   );
    
  }
  calculerMontantTotal() {
    // Implémentez la logique pour calculer le montant total à partir des données de la facture
    if (this.facture) {
      const quantite = this.facture.achat.quantite;
      const prixUnitaire = this.facture.produit.prix;
      const TVA = this.facture.produit.tva;

      this.montantTotal = (prixUnitaire * quantite) + TVA;
    }
  }
  generatePdf() {
    if (this.facture) {
      this.pdfService.generateFacturePdf(this.facture);
    }
  }
}
