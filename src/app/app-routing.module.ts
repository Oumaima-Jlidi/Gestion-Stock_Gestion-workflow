import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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

const routes: Routes = [
{path: "produits", component : ProduitsComponent},
{path: "add-produit", component : AddProduitComponent},
{path: "update-produit/:id",  component: UpdateProduitComponent},
{path: "categories", component : CategoriesComponent},
{path: "add-categorie", component :AddCategorieComponent},
{path: "update-categorie/:id",  component: UpdateCategorieComponent},
{path: "dashborad",  component: DashboradComponent},
{path: "register",  component: RegisterComponent},
{path: "login",  component: LoginComponent},
{path: "achat",  component: AchatComponent},
{path: "liste-achat",  component: ListeAchatComponent},
{path:"update-achat/:achatId/:produitId",  component: UpdateAchatComponent},


{ path: "", redirectTo: "produits", pathMatch: "full" }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
