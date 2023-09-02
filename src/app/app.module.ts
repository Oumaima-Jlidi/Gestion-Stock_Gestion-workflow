import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // Importez FormsModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProduitsComponent } from './produits/produits.component';
import { AddProduitComponent } from './add-produit/add-produit.component';
import { UpdateProduitComponent } from './update-produit/update-produit.component';
import { CategoriesComponent } from './categories/categories.component';
import { AddCategorieComponent } from './add-categorie/add-categorie.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { DashboradComponent } from './dashborad/dashborad.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AchatComponent } from './achat/achat.component';
import { ListeAchatComponent } from './liste-achat/liste-achat.component';
import { UpdateAchatComponent } from './update-achat/update-achat.component';
import { FactureComponent } from './facture/facture.component';

@NgModule({
  declarations: [
    AppComponent,
    ProduitsComponent,
    AddProduitComponent,
    UpdateProduitComponent,
    CategoriesComponent,
    AddCategorieComponent,
    UpdateCategorieComponent,
    DashboradComponent,
    RegisterComponent,
    LoginComponent,
    AchatComponent,
    ListeAchatComponent,
    UpdateAchatComponent,
    FactureComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
