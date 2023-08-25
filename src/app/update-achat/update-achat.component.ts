import { Component, OnInit } from '@angular/core';
import { AchatService } from '../service/achat.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-achat',
  templateUrl: './update-achat.component.html',
  styleUrls: ['./update-achat.component.css']
})
export class UpdateAchatComponent implements OnInit {
  achatId: number=-1;
  Achat: any = {};
  produitId: number = -1;

  dateAchat: Date = new Date();

  constructor(
    private route: ActivatedRoute,
    private Achatservice: AchatService,
    private router: Router
  ) {
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const achatIdParam = params.get('achatId');
      const produitIdParam = params.get('produitId');
      
      if (achatIdParam !== null && produitIdParam !== null) {
        this.achatId = +achatIdParam; // Convertit en nombre
        this.produitId = +produitIdParam; // Convertit en nombre
  
        this.loadAchat();
      } else {
        console.error('Paramètres manquants dans l\'URL');
      }
    });
  
    this.loadAchat();
    
  }

  loadAchat(): void {
    this.Achatservice.getAchat(this.achatId).subscribe(
      (data: any) => {
        this.Achat = data; // Remplacez avec le modèle de votre produit
      },
      (error: any) => {
        console.error('Erreur lors du chargement du achat :', error);
      }
    );
  }

  updateAchat(): void {
    this.Achatservice.updateAchat(this.Achat).subscribe(
      (response: any) => {
        console.log('Achat mis à jour avec succès :', response);
        
        // Naviguer vers la route de la liste des achats avec le produitId
        this.router.navigate(['/liste-achat'], {
          queryParams: { produitId: this.produitId }
        });

      },
      (error: any) => {
        console.error('Erreur lors de la mise à jour de l\'achat :', error);
      }
    );
  }
}
