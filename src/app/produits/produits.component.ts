import { Component, OnInit ,AfterViewInit} from '@angular/core';
import { ProduitService } from '../service/produit.service';
import 'datatables.net';
import 'datatables.net-bs4';
import * as $ from 'jquery';
import { AuthService } from '../service/auth-service.service';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css'],
  providers: [ProduitService]
})
export class ProduitsComponent implements OnInit {
  prods: any[] = [];
  categories: any[] = [];
  totalPrixTTC: number = 0; // Ajout de la propriété pour stocker la somme
  loggedInUserEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur connecté

  constructor(private produitService: ProduitService, private authService: AuthService) {}

  ngOnInit() {
    console.log('on init ...');
    this.loggedInUserEmail = this.authService.loggedInUserEmail; // Récupérer l'email de l'utilisateur connecté

    this.fetchProduits();
    this.calculateTotalPrixTTC(); // Appel de la fonction de calcul

    this.produitService.getProduits().subscribe((datas) => {
      this.prods = datas;
      setTimeout(() => {
        $('#datatables').DataTable({
          pagingType: 'full_numbers',
          pageLength: 5,
          processing: true,
          lengthMenu: [5, 10, 25]
        });
      });
    });
  }

  // Méthode pour calculer la somme totale des prix TTC
  calculateTotalPrixTTC(): number {
    let total = 0;
    for (const produit of this.prods) {
      total += produit.prix_ttc;
    }
    return total;
  }

  fetchProduits() {
    this.produitService.getProduits().subscribe((data) => {
      this.prods = data;
    });
  }

  deleteProduit(produitId: number) {
    this.produitService.deleteProduit(produitId).subscribe(
      () => {
        console.log(`Produit avec l'ID ${produitId} supprimé.`);
        this.fetchProduits(); // Mettre à jour la liste après la suppression
        this.calculateTotalPrixTTC(); // Mettre à jour la somme après la suppression
      },
      (error: any) => {
        console.error('Erreur lors de la suppression du produit :', error);
      }
    );
  }

  // Méthode pour calculer et retourner la somme totale des prix TTC
  getTotalPrixTTC(): number {
    return this.calculateTotalPrixTTC();
  }
}
