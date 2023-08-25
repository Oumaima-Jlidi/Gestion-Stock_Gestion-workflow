import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AchatService } from '../service/achat.service';
import { HttpErrorResponse } from '@angular/common/http'; // Importez HttpErrorResponse

@Component({
  selector: 'app-achat',
  templateUrl: './achat.component.html',
  styleUrls: ['./achat.component.css']
})
export class AchatComponent implements OnInit {
  produitId: number = -1;
  nom_client: string = '';
  adresse_client: string = '';
  quantite: number = 0;
  dateAchat: Date = new Date();
  errorMessage: string = '';
  successMessage: string = '';
 // Initialisez la date d'achat avec la date système

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private achatService: AchatService
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.produitId = params['produitId'];
    });
  }

  acheterProduit():void {
    if (this.produitId !== -1) {
      const achatData = {
        id: this.produitId,
        nom_client: this.nom_client,
        adresse_client: this.adresse_client,
        quantite: this.quantite,
        dateAchat: this.dateAchat
      };

      this.achatService.acheterProduit(achatData).subscribe(
        (response: any) => {
          if (response.id) {
            this.successMessage = 'Achat réussi !';
            this.errorMessage = ''; // Réinitialiser le message d'erreur
            // Rediriger vers la liste des achats
            this.router.navigate(['/liste-achat'], { queryParams: { produitId: this.produitId } });
          } else {
            this.errorMessage = 'Erreur lors de l\'achat';
            this.successMessage = ''; // Réinitialiser le message de réussite
          }
        },
        (error) => {
          this.errorMessage = 'Erreur lors de l\'achat';
          this.successMessage = ''; // Réinitialiser le message de réussite
        }
      );
    }
  
  }}