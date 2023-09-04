import { Injectable } from '@angular/core';
import { jsPDF } from 'jspdf';

@Injectable({
  providedIn: 'root',
})
export class PdfService {
  generateFacturePdf(factureData: any) {
    // Créez une nouvelle instance de jsPDF
    const doc = new jsPDF();

    // Définissez la police et la taille par défaut pour le contenu de la facture
    doc.setFont('helvetica');
    doc.setFontSize(12);

    // Définissez le titre de la facture
    doc.setFontSize(16);
    doc.text('Facture', 10, 15);

    // Position de départ pour le contenu de la facture
    let y = 30;

    // Ajoutez les données de la facture
    doc.text(`ID de la facture : ${factureData.id}`, 10, y);
    y += 10;

    doc.text('Informations sur la facture', 10, y);
    y += 10;

    doc.text(`Date de la facture : ${factureData.dateFacture}`, 10, y);
    y += 10;
    doc.text(`Nom du client : ${factureData.clientNom}`, 10, y);
    y += 10;
    doc.text(`Adresse du client : ${factureData.clientAdresse}`, 10, y);
    y += 10;

    doc.text('Détails des produits', 10, y);
    y += 10;
    doc.text(`Adresse du client : ${factureData.produit.prix * factureData.produit.quantite}`, 10, y);
    y+=10
    // Créez un tableau pour afficher les détails des produits
   
    // Sauvegardez le PDF avec un nom de fichier
    doc.save('facture.pdf');
  }
}
