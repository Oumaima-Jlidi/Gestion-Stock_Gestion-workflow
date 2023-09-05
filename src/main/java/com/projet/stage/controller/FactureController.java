package com.projet.stage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Facture;
import com.projet.stage.entities.FactureRequest;
import com.projet.stage.entities.Produit;
import com.projet.stage.repos.AchatRepository;
import com.projet.stage.repos.FactureRepository;
import com.projet.stage.repos.ProduitRepository;
import com.projet.stage.services.AchatService;
import com.projet.stage.services.FactureService;
import com.projet.stage.services.PdfService;
import com.itextpdf.text.DocumentException;


import java.io.IOException;
@RestController
@RequestMapping("/api/factures")
@CrossOrigin("*")
public class FactureController {
	 @Autowired
	    private AchatService achatService;
	 @Autowired
	    private FactureService factureService;
	 
	 private final FactureRepository factureRepository;
	    private final AchatRepository achatRepository;
	    private final ProduitRepository produitRepository;

	    @Autowired
	    public FactureController(FactureRepository factureRepository, AchatRepository achatRepository, ProduitRepository produitRepository) {
	        this.factureRepository = factureRepository;
	        this.achatRepository = achatRepository;
	        this.produitRepository = produitRepository;
	    }
	
	    @Autowired
	    private PdfService pdfService;

	    @GetMapping("/{factureId}/export-pdf")
	    public ResponseEntity<byte[]> exportFactureToPdf(@PathVariable Long factureId) {
	        try {
	            // Récupérez la facture à partir de la base de données ou de tout autre service
	            Facture facture = factureRepository.findById(factureId).orElse(null);

	            if (facture == null) {
	                return ResponseEntity.notFound().build();
	            }

	            // Générez le contenu de la facture au format texte
	            String factureText = "ID de la facture : " + facture.getId() + "\n";
	            factureText += "Date de la facture : " + facture.getDateFacture() + "\n";
	            factureText += "Nom du Produit : " + facture.getProduit().getNom() + "\n";
	            factureText += " Reference du Produit : " + facture.getProduit().getReference() + "\n";
	            factureText += " Nom  du Client : " + facture.getAchat().getNom_client() + "\n";
	            factureText += " Adresse  du Client : " + facture.getAchat().getAdresse_client() + "\n";
	            factureText += " Quantite Acheter : " + facture.getAchat().getQuantite() + "\n";
	            factureText += " Prix unitaire : " + facture.getProduit().getPrix() + "\n";
	            factureText += " TVA : " + facture.getProduit().calculerMontantTVA() + "\n";

	            // Calcul du total du prix
	            double montantTotal = facture.calculerMontantTotal();
	            factureText += "Total du prix : " + montantTotal + "\n";

	            // Ajoutez d'autres informations de la facture ici

	            // Supposons que vous avez un service PDF nommé pdfService
	            byte[] pdfBytes = pdfService.generatePdf(factureText);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDispositionFormData("filename", "facture.pdf");
	            headers.setContentLength(pdfBytes.length);

	            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	        } catch (DocumentException | IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }


	   

	    @PostMapping("/ajouter")
	    public Facture ajouterFacture(@RequestBody FactureRequest factureRequest) {
	        // Vous devez implémenter FactureService pour gérer l'ajout de la facture en utilisant factureData
	        return factureService.ajouterFacture(factureRequest);
	    }

	 
	  @GetMapping("/{id}")
	    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
	        Facture facture = factureService.getFactureById(id);
	        
	        if (facture != null) {
	            return ResponseEntity.ok(facture);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
