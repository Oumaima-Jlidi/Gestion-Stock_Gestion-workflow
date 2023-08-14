package com.projet.stage.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stage.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
