import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AchatService } from '../service/achat.service';
import { Achat } from '../models/achat.model';
import { Produit } from '../models/produit.model';
import { FactureService } from '../service/facture.service';
import { Facture } from '../models/facture.model';
import { ProduitService } from '../service/produit.service';
import { FactureRequest } from '../models/FactureRequest.model';
@Component({
  selector: 'app-liste-achat',
  templateUrl: './liste-achat.component.html',
  styleUrls: ['./liste-achat.component.css']
})
export class ListeAchatComponent implements OnInit {
  achats: Achat[] = [];
  produitId: number = -1;
  achatId: number = -1;
  successMessage: string = '';

produit: Produit[]=[];
  constructor(
    private route: ActivatedRoute,
    private achatService: AchatService,
    private factureService:FactureService,
    private router:Router,
    private produitService:ProduitService
  ) {}

  ngOnInit() {
    
    this.route.queryParams.subscribe(params => {
      this.produitId = params['produitId'];
      this.achatId = +params['achatId'];

      this.fetchAchatsByProduit(this.produitId);
      this.achatService.getAchatsByProduit(this.produitId).subscribe((datas)=>{
        this.achats=datas;
        setTimeout(()=>{
          $('#datatables').DataTable({
            pagingType:'full_numbers',
            pageLength:5,
            processing:true,
            lengthMenu:[5,10,25]
          });
        });
        
      });
      
    });
  }

  fetchAchatsByProduit(produitId: number) {
    this.achatService.getAchatsByProduit(produitId).subscribe(
      (achats: Achat[]) => {
        this.achats = achats;
      },
      (error: any) => {
        console.error('Error fetching achats:', error);
      }
    );
  }

  
  deleteAchat(achatId: number) {
    if (confirm("Êtes-vous sûr de vouloir supprimer cet achat ?")) {
      this.achatService.deleteAchat(achatId).subscribe(
        () => {
          console.log(`Achat avec l'ID ${achatId} supprimé.`);
          this.fetchAchatsByProduit(this.produitId); // Mettre à jour la liste après la suppression
        },
        (error: any) => {
          console.error('Erreur lors de la suppression de l\'achat :', error);
        }
      );
    }
  }
  ajouterFacture(achatId: number, produitId: number) {
    // Utilisez le service d'achat pour récupérer les données de l'achat.
    this.achatService.getAchat(achatId).subscribe(
      (achat) => {
        // Vous avez maintenant les données de l'achat.
        console.log('Données de l\'achat :', achat);

        // Utilisez le service de produit pour récupérer les données du produit.
        this.produitService.getProduit(produitId).subscribe(
          (produit) => {
            // Vous avez maintenant les données du produit.
            console.log('Données du produit :', produit);

            // Créez un objet FactureRequest avec les données de l'achat et du produit.
            const factureData: FactureRequest = {
              achatId: achat.id,
              produitId: produit.id,

            };

            // Utilisez le service de facture pour ajouter la facture.
            this.factureService.ajouterFacture(factureData).subscribe(
              (facture) => {
                // Vous avez maintenant les données de la facture.
                console.log('Données de la facture :', facture);
alert('la facture est ajoutee avec success');
                // Gérer la réussite, peut-être rediriger l'utilisateur ou effectuer d'autres actions
              },
              (error) => {
                console.error('Erreur lors de l\'ajout de la facture :', error);
                // Gérer l'erreur pour l'ajout de la facture
              }
            );
          },
          (error: any) => {
            console.error('Erreur lors de la récupération des données du produit :', error);
            // Gérer l'erreur pour la récupération du produit
          }
        );
      },
      (error) => {
        console.error('Erreur lors de la récupération des données de l\'achat :', error);
        // Gérer l'erreur pour la récupération de l'achat
      }
    );
  }
}