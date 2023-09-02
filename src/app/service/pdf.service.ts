import { Injectable } from '@angular/core';
import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

@Injectable({
  providedIn: 'root',
})
export class PdfService {
  generateFacturePdf(factureData: any) {
    // Configurez pdfMake avec les polices de caractères
    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    // Définissez le contenu du PDF
    const documentDefinition = {
      content: [
        { text: 'Facture', style: 'header' },
        { text: factureData, style: 'body' },
      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true,
          margin: [0, 10, 0, 10], // Ajustez la marge en conséquence
        },
        body: {
          fontSize: 12,
          margin: [0, 0, 0, 10], // Ajustez la marge en conséquence
        },
      },
    };

    // Générez le PDF
    pdfMake.createPdf(documentDefinition).download('facture.pdf');
  }
}
