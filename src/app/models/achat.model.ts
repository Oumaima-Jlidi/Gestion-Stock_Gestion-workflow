import { Produit } from './produit.model';

export interface Achat {
    id: number;
    nom_client: string;
    adresse_client: string;
    quantite: number;
    dateAchat: string; // Assuming the date is a string in the format "YYYY-MM-DD"
    produit: Produit; // Assurez-vous que Produit est bien défini
  }