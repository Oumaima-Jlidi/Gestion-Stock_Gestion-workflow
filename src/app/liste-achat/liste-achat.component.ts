import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AchatService } from '../service/achat.service';
import { Achat } from '../models/achat.model';
import { Produit } from '../models/produit.model';
@Component({
  selector: 'app-liste-achat',
  templateUrl: './liste-achat.component.html',
  styleUrls: ['./liste-achat.component.css']
})
export class ListeAchatComponent implements OnInit {
  achats: Achat[] = [];
  produitId: number = -1;
produit: Produit[]=[];
  constructor(
    private route: ActivatedRoute,
    private achatService: AchatService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.produitId = params['produitId'];
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
  
}
