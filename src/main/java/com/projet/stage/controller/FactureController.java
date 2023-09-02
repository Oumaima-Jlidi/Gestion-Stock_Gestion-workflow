package com.projet.stage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	

	    @PostMapping("/ajouter-automatique")
	    public ResponseEntity<?> ajouterFactureAutomatique(@RequestParam Long achatId, @RequestParam Long produitId) {
	        ResponseEntity<?> response = factureService.ajouterFactureAutomatique(achatId, produitId);

	        if (response != null && response.getBody() != null) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            boolean achatExiste = achatRepository.existsById(achatId);
	            boolean produitExiste = produitRepository.existsById(produitId);
	            
	            if (!achatExiste) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Achat non trouvé.");
	            } else if (!produitExiste) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé.");
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur inattendue lors de l'ajout de la facture.");
	            }
	        }
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
