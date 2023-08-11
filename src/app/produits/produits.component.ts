import { Component, OnInit ,AfterViewInit} from '@angular/core';
import { ProduitService } from '../service/produit.service';
import 'datatables.net';
import 'datatables.net-bs4';
import * as $ from 'jquery';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css'],
  providers: [ProduitService]

})
export class ProduitsComponent implements OnInit {
  prods: any[]=[];
  constructor(private produitService: ProduitService){
  }
  ngOnInit() {
    console.log('on init ...');
    this.fetchProduits();
    this.produitService.getProduits().subscribe((datas)=>{
      this.prods=datas;
      setTimeout(()=>{
        $('#datatables').DataTable({
          pagingType:'full_numbers',
          pageLength:5,
          processing:true,
          lengthMenu:[5,10,25]
        });
      })
    })    
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
      },
      (error:any) => {
        console.error('Erreur lors de la suppression du produit :', error);
      }
    );
  }
  
  }
  
  
