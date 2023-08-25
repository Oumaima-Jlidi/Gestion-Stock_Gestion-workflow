export interface Achat {
    id: number;
    nom_client: string;
    adresse_client: string;
    quantite: number;
    dateAchat: string; // Assuming the date is a string in the format "YYYY-MM-DD"
    produit: string; // Assuming the name of the associated product
  }