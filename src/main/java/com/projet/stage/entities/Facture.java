package com.projet.stage.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Facture implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP) // Utilisez TemporalType.TIMESTAMP pour stocker la date et l'heure

	private Date dateFacture;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "achat_id")
    @JsonBackReference

    private Achat achat;

    
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

	public Facture(Long id, Date dateFacture, Achat achat, Produit produit) {
		super();
		this.id = id;
		this.dateFacture = dateFacture;
		this.achat = achat;
		this.produit = produit;
	}

	public Facture() {
		super();
        this.dateFacture = new Date(); // Initialisez la date d'achat avec la date système lors de la création de l'objet
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}


	

	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
    
    
}
