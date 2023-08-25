package com.projet.stage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Produit;
import com.projet.stage.repos.AchatRepository;
import com.projet.stage.repos.ProduitRepository;
@Service
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    ProduitRepository produitRepository;
    
    @Autowired
    AchatRepository achatRepository;
	@Override
	public Produit saveProduit(Produit p) {
		// TODO Auto-generated method stub
		return produitRepository.save(p);
	}

	@Override
	public Produit updateProduit(Produit p) {
		// TODO Auto-generated method stub
		return produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Produit p) {
		// TODO Auto-generated method stub
		produitRepository.delete(p);
	}

	@Override
	public void deleteProduitById(Long id) {
		// TODO Auto-generated method stub
		produitRepository.deleteById(id);
	}

	@Override
	public Produit getProduit(Long id) {
		// TODO Auto-generated method stub
		return produitRepository.findById(id).get();
		}

	@Override
	public List<Produit> getAllProduits() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	@Override
	public void processPurchase(Achat achat) {
		Produit product = achat.getProduit();
        int purchasedQuantity = achat.getQuantite();

        if (product.getQuantite() >= purchasedQuantity) {
            product.setQuantite(product.getQuantite() - purchasedQuantity);
            produitRepository.save(product);
            achatRepository.save(achat);
        } else {
            throw new RuntimeException("Insufficient stock");
        }
    }
}
