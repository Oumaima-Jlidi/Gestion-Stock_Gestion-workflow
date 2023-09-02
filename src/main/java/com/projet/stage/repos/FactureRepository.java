package com.projet.stage.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stage.entities.Facture;

public interface FactureRepository  extends JpaRepository<Facture,Long>{
    Facture findByAchatIdAndProduitId(Long achatId, Long produitId);

}
