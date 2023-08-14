package com.projet.stage;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.stage.entities.Categorie;
import com.projet.stage.entities.Produit;
import com.projet.stage.repos.CategorieRepository;
import com.projet.stage.repos.ProduitRepository;

@SpringBootTest
class GestionStockApplicationTests {
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void testCreateProduit() {
	Produit prod = new Produit("co23de89" ,"ensemble traditionnelle",10,64.500,"c'est un ensemble traditionnelle   pour une fille adulte ");
	  double prixTTC = prod.calculerMontantTTC();
	    prod.setPrix_ttc(prixTTC);
	    double TVA = prod.calculerMontantTVA();
	    prod.setTVA(TVA);
	produitRepository.save(prod);
	
	}
	
	@Test
	public void testFindProduit(){
	Produit p=produitRepository.findById(1L).get();
	System.out.println(p);
	}
    @Test
    public void testUpdateProduit() {
    	Produit p =produitRepository.findById(1L).get();
    	p.setPrix(20.600);
    	 double prixTTC = p.calculerMontantTTC();
 	    p.setPrix_ttc(prixTTC);
 	    double TVA = p.calculerMontantTVA();
 	    p.setTVA(TVA);
    	produitRepository.save(p);
    }
    @Test
    public void testDeleteProduit() {
    	produitRepository.deleteById(1L);
    }

    @Test
    public void testFindALLProduit() {
       List<Produit> prods=produitRepository.findAll();
           for (Produit p:prods) {
        	   System.out.println(p);
           }
}
}
