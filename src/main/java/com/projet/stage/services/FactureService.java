package com.projet.stage.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Facture;
import com.projet.stage.entities.Produit;
import com.projet.stage.repos.AchatRepository;
import com.projet.stage.repos.FactureRepository;
import com.projet.stage.repos.ProduitRepository;

@Service
public class FactureService {
	@Autowired
private AchatRepository achatRepository;
	@Autowired
private ProduitRepository produitRepository;
	@Autowired
	private FactureRepository factureRepository;

	   public Facture getFactureByAchatAndProduitId(Long achatId, Long produitId) {
	        return factureRepository.findByAchatIdAndProduitId(achatId, produitId);
	    }
	   
	   public ResponseEntity<?> ajouterFactureAutomatique(Long achatId, Long produitId) {
		    // Créez une instance de Facture avec les informations nécessaires
		    Facture facture = new Facture();
		    
		    // Récupérez l'achat et le produit en utilisant leurs IDs
		    Achat achat = achatRepository.findById(achatId).orElse(null);
		    Produit produit = produitRepository.findById(produitId).orElse(null);
		    
		    if (achat != null && produit != null) {
		        facture.setAchat(achat);
		        facture.setProduit(produit);
		        facture.setDateFacture(new Date()); // Utilisez la date actuelle
		        
		        // Enregistrez la facture dans la base de données
		        Facture factureEnregistree = factureRepository.save(facture);
		        
		        // Renvoyez la facture enregistrée avec un code d'état 201 (Created)
		        return ResponseEntity.status(HttpStatus.CREATED).body(factureEnregistree);
		    } else {
		        // Gérez le cas où l'achat ou le produit n'a pas été trouvé
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Achat ou produit non trouvé.");
		    }
		}

	   
	   public Facture getFactureById(Long id) {
	        Optional<Facture> optionalFacture = factureRepository.findById(id);
	        return optionalFacture.orElse(null);
	    }


}
