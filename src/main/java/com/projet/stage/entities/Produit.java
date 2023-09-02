package com.projet.stage.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Produit {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String reference;
private String nom;
private int quantite;
private double prix;
private double TVA;
private double timbre;
private double prix_ttc;
private String description;

//Many-to-One relation with Categorie
@ManyToOne
@JoinColumn(name = "categorie_id")
private Categorie categorie;
@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Facture> factures = new ArrayList<>();

public Produit() {
	super();
	// TODO Auto-generated constructor stub
}
public Produit(String id) {
    this.id = Long.parseLong(id);
}

public Produit(String reference, String nom, int quantite, double prix, String description) {
	super();
	this.reference = reference;
	this.nom = nom;
	this.quantite = quantite;
	this.prix = prix;
	TVA =calculerMontantTVA();
	this.timbre=0.600;
	this.description = description;
    this.prix_ttc = calculerMontantTTC();
    // Appel à la méthode dans le constructeur

}




public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getReference() {
	return reference;
}

public void setReference(String reference) {
	this.reference = reference;
}
public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public int getQuantite() {
	return quantite;
}

public void setQuantite(int quantite) {
	this.quantite = quantite;
}

public double getPrix() {
	return prix;
}

public void setPrix(double prix) {
	this.prix = prix;
}

public double getTVA() {
	return TVA;
}

public void setTVA(double tVA) {
	TVA = tVA;
}



public double getTimbre() {
	return timbre;
}

public void setTimbre(double timbre) {
	this.timbre = timbre;
}

public double getPrix_ttc() {
	return prix_ttc;
}

public void setPrix_ttc(double prix_ttc) {
	this.prix_ttc = prix_ttc;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Categorie getCategorie() {
	return categorie;
}

public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

@Override
public String toString() {
	return "Produit [id=" + id + ", reference=" + reference + ", nom=" + nom + ", quantite=" + quantite + ", prix="
			+ prix + ", TVA=" + TVA + ", prix_ttc=" + prix_ttc + ", description=" + description + "]";
}

public double calculerMontantTTC() {
    double TVA = 0.19; 
    double prix_TTC = prix + (prix * TVA)+timbre;
    return prix_TTC;
}
public double calculerMontantTVA() {
    double tauxTVA = 0.19; // Taux de TVA de 19%
    double TVA = prix * tauxTVA;
    return TVA;
}
}