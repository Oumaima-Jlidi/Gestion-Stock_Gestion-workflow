package com.projet.stage.entities;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Achat implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nom_client;
	private String adresse_client;
    private int quantite;
    @Temporal(TemporalType.TIMESTAMP) // Utilisez TemporalType.TIMESTAMP pour stocker la date et l'heure
    private Date dateAchat;

    @JsonManagedReference // Côté "propriétaire" de la relation
    @OneToMany(mappedBy = "achat")
    private List<Facture> factures;
    @ManyToOne
    private Produit produit;
    
	public Achat() {
		super();
        this.dateAchat = new Date(); // Initialisez la date d'achat avec la date système lors de la création de l'objet

	}

	@JsonIgnore
	public Achat(String id) {
	    this.id = Long.parseLong(id);
	}

	 public Achat(String nom_client, String adresse_client, int quantite, Date dateAchat) {
	        this.nom_client = nom_client;
	        this.adresse_client = adresse_client;
	        this.quantite = quantite;
	        this.dateAchat = dateAchat;
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
	 
	public Achat(String nom_client, String adresse_client, int quantite, Produit produit) {
		super();
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.quantite = quantite;
		this.produit = produit;
	}

	public Achat(String nom_client, String adresse_client, int quantite, Date dateAchat, Produit produit) {
		super();
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
		this.produit = produit;
	}

	public Achat(Long id, String nom_client, String adresse_client, int quantite, Date dateAchat,
			List<Facture> factures, Produit produit) {
		super();
		this.id = id;
		this.nom_client = nom_client;
		this.adresse_client = adresse_client;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
		this.factures = factures;
		this.produit = produit;
	}

	public Achat(String nom_client, String adresse_client, int quantite, Produit produit, Date dateAchat) {
        this.nom_client = nom_client;
        this.adresse_client = adresse_client;
        this.quantite = quantite;
        this.produit = produit;
        this.dateAchat = dateAchat;
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

		public List<Facture> getFactures() {
			return factures;
		}

		public void setFactures(List<Facture> factures) {
			this.factures = factures;
		}
		public double calculerMontantTVA() {
		    if (factures != null && produit != null) {
		        double TVA = produit.calculerMontantTVA(); 
		        // Assurez-vous d'avoir une méthode pour calculer la TVA dans votre classe Produit
		        return TVA;
		    }
		    return 0.0; // Gérez le cas où la liste de factures ou le produit est nul
		}
}
