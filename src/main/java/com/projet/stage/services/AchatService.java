package com.projet.stage.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Produit;
import com.projet.stage.repos.AchatRepository;
import com.projet.stage.repos.ProduitRepository;

@Service
public class AchatService {

    @Autowired
    private ProduitRepository produitRepository;
    
    @Autowired
    private AchatRepository achatRepository;

    public Achat acheterProduit(Long produitId, String nomClient, String adresseClient, int quantiteAchat) {
        // Rechercher le produit à acheter
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        if (!optionalProduit.isPresent()) {
            throw new NoSuchElementException("Produit non trouvé");
        }

        Produit produit = optionalProduit.get();

        // Vérifier si la quantité demandée est disponible
        if (quantiteAchat > produit.getQuantite()) {
            throw new IllegalArgumentException("Quantité insuffisante");
        }

        // Mettre à jour la quantité restante du produit
        int nouvelleQuantite = produit.getQuantite() - quantiteAchat;
        produit.setQuantite(nouvelleQuantite);
        produitRepository.save(produit);

        // Créer l'objet Achat
        Achat achat = new Achat(nomClient, adresseClient, quantiteAchat, produit);
        achatRepository.save(achat);

        return achat;
    }
    public List<Achat> getAchatsByNomProduit(long produitId) {
        return achatRepository.findAchatsByNomProduit(produitId);
    }
    
    public Achat updateAchat(Achat a) {
    	return achatRepository.save(a);
    }
    public void deleteAchat(Long achatId) {
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new IllegalArgumentException("Achat avec l'ID spécifié n'existe pas"));
        
        achatRepository.delete(achat);
    }
    public Achat getAchatById(long id) {
    	return achatRepository.findById(id).get();
    }
    
    
}
