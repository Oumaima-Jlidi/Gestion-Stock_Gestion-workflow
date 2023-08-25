package com.projet.stage.services;

import java.util.List;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Produit;

public interface ProduitService {
	Produit saveProduit(Produit p);
	Produit updateProduit(Produit p);
	void deleteProduit(Produit p);
	void deleteProduitById(Long id);
    Produit getProduit(Long id);
	List<Produit> getAllProduits();
	void processPurchase(Achat achat);
}
