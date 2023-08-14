package com.projet.stage.services;

import com.projet.stage.entities.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie saveCategorie(Categorie cat);

    Categorie updateCategorie(Categorie cat);


    void deleteCategorieById(Long id);

    Categorie getCategorie(Long id);

    List<Categorie> getAllCategories();
}
