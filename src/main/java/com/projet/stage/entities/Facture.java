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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity

public class Facture implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP) // Utilisez TemporalType.TIMESTAMP pour stocker la date et l'heure

	private Date dateFacture;
    
    @ManyToOne
    @JoinColumn(name = "achat_id")
    @JsonManagedReference // Côté "propriétaire" de la relation

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
    
	 public double calculerMontantTotal() {
	        if (achat != null && produit != null) {
	            int quantite = achat.getQuantite();
	            double prixUnitaire = produit.getPrix();
	            double TVA = produit.calculerMontantTVA(); // Assurez-vous d'avoir une méthode pour calculer la TVA dans votre classe Produit

	            // Calculez le montant total de la facture
	            double montantTotal = (prixUnitaire * quantite) + TVA;
	            return montantTotal;
	        } else {
	            return 0.0; // Gérez le cas où l'achat ou le produit est nul
	        }
	    }
}
