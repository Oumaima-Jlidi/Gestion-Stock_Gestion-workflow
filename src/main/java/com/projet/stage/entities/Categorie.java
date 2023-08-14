package com.projet.stage.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity

public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
 
    // One-to-Many relation with Produit
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Produit> produits;
 
 
public Categorie() {
		super();
	}

public Categorie(long id) {
	super();
	this.id = id;
}

public Categorie(long id, String nom, List<Produit> produits) {
	super();
	this.id = id;
	this.nom = nom;

	this.produits = produits;
}

public Categorie(String nom, String type, String description) {
	super();
	this.nom = nom;

}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

@Override
public String toString() {
	return "Categorie [id=" + id + ", nom=" + nom + ", produits=" + produits + "]";
}


 
 
 
}
