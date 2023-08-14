package com.projet.stage.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.stage.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}

