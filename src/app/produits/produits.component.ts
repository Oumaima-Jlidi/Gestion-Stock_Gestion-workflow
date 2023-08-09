import { Component, OnInit ,AfterViewInit} from '@angular/core';
import { ProduitService } from './produit.service';
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

    
  
  }
  
  
