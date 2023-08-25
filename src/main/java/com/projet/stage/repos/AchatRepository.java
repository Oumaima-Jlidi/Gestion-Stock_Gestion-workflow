package com.projet.stage.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.stage.entities.Achat;

public interface AchatRepository extends JpaRepository<Achat,Long>{
	@Query("SELECT a FROM Achat a WHERE a.produit.id = :produitId")
    List<Achat> findAchatsByNomProduit(@Param("produitId") long produitId);
}
