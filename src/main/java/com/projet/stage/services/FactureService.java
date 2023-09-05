package com.projet.stage.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Facture;
import com.projet.stage.entities.FactureRequest;
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
	   
	 
	   public Facture ajouterFacture(FactureRequest factureRequest) {
		    // Récupérez l'achat et le produit en fonction de leurs identifiants
		    Long achatId = factureRequest.getAchatId();
		    Long produitId = factureRequest.getProduitId();

		    // Vérifiez que les identifiants ne sont pas null
		    if (achatId == null || produitId == null) {
		        throw new IllegalArgumentException("Les identifiants d'achat et de produit ne peuvent pas être null.");
		    }

		    // Récupérez l'achat et le produit depuis les référentiels
		    Achat achat = achatRepository.findById(achatId).orElseThrow(() -> new IllegalArgumentException("Achat introuvable"));
		    Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new IllegalArgumentException("Produit introuvable"));

		    // Créez une nouvelle instance de Facture
		    Facture facture = new Facture();
		    facture.setDateFacture(new Date()); // Initialisez la date de la facture

		    // Associez l'achat et le produit à la facture
		    facture.setAchat(achat);
		    facture.setProduit(produit);

		    // Calculez le montant total ici (par exemple, en utilisant le prix du produit et la quantité achetée)

		    // Enregistrez la facture dans la base de données
		    return factureRepository.save(facture);
		}



	   
	   public Facture getFactureById(Long id) {
	        Optional<Facture> optionalFacture = factureRepository.findById(id);
	        return optionalFacture.orElse(null);
	    }

	  
}
