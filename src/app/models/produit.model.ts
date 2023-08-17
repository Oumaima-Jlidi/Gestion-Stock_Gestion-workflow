
import { Categorie } from './categorie.model';

export interface Produit {
  id?: number;
  reference: string;
  nom: string;
  quantite: number;
  prix: number;
  tva: number;
  timbre: number;
  prix_ttc: number;
  description: string;
  categorie: Categorie; // Utilisez "categorie" au lieu de "categorie_id"
}