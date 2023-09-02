import { Component, OnInit } from '@angular/core';
import { CategorieService } from '../service/categorie.service';
import 'datatables.net';
import 'datatables.net-bs4';
import * as $ from 'jquery';
import * as jQuery from 'jquery';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css'],
  providers: [CategorieService]

})
export class CategoriesComponent  implements OnInit{
  cats: any[]=[];
constructor(private categorieservice:CategorieService){
}
ngOnInit() {
  console.log('on init ...');
  this.fetchCategories();
  this.categorieservice.getCategories().subscribe((datas)=>{
    this.cats=datas;
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

fetchCategories() {
  this.categorieservice.getCategories().subscribe((data) => {
    this.cats = data;
  });
}


deleteCategorie(categorieId: number) {
  this.categorieservice.deleteCategorie(categorieId).subscribe(
    () => {
      console.log(`Produit avec l'ID ${categorieId} supprimé.`);
      this.fetchCategories(); // Mettre à jour la liste après la suppression
    },
    (error:any) => {
      console.error('Erreur lors de la suppression du produit :', error);
    }
  );
}
}