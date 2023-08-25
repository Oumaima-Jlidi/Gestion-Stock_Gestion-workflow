package com.projet.stage.entities;
import javax.persistence.*;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Achat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nom_client;
	private String adresse_client;
    private int quantite;
    @Temporal(TemporalType.TIMESTAMP) // Utilisez TemporalType.TIMESTAMP pour stocker la date et l'heure

    private Date dateAchat;

    @ManyToOne
    private Produit produit;
	public Achat() {
		super();
        this.dateAchat = new Date(); // Initialisez la date d'achat avec la date système lors de la création de l'objet

	}
	public Achat(String nom_client, String adresse_client, int quantite, Produit produit) {
		super();
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.quantite = quantite;
		this.produit = produit;
        this.dateAchat = new Date(); // Initialisez la date d'achat avec la date système lors de la création de l'objet

	}
	
	public Achat(Long id, String nom_client, String adresse_client, int quantite, Produit produit) {
		super();
		this.id = id;
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.quantite = quantite;
		this.produit = produit;
        this.dateAchat = new Date(); // Initialisez la date d'achat avec la date système lors de la création de l'objet

	}
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getAdresse_client() {
		return adresse_client;
	}
	public void setAdresse_client(String adresse_client) {
		this.adresse_client = adresse_client;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	 public Date getDateAchat() {
	        return dateAchat;
	    }

	    public void setDateAchat(Date dateAchat) {
	        this.dateAchat = dateAchat;
	    }

}
