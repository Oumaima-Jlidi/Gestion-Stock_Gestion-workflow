import { Achat } from './achat.model'; // Import the Achat model
import { Produit } from './produit.model';

export interface Facture {
    id?: number;
    dateFacture: string; // Assuming the date is a string in the format "YYYY-MM-DD"
    achat: Achat;
    produit:Produit

}