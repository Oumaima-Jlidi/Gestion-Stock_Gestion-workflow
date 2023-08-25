package com.projet.stage.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.stage.entities.Produit;
import com.projet.stage.services.ProduitService;
@CrossOrigin(origins="*")
@RequestMapping("/api")
@RestController
public class ProduitController {
	@Autowired
	private ProduitService produitservice;

   @RequestMapping(method=RequestMethod.GET,value="produits")
   List<Produit> getAllProduits(){
		return produitservice.getAllProduits();
	}
   
   @RequestMapping("produits/{id}")
   public Produit getProduit(@PathVariable long id) {
	return produitservice.getProduit(id);   
   }
   
   @RequestMapping(method= RequestMethod.DELETE,value="produits/{id}")
   public  void deleteProduit(@PathVariable long id) {
	    produitservice.deleteProduitById(id);
   }
   @RequestMapping(method=RequestMethod.POST,value="produits")
   public void addProduit(@RequestBody Produit prod) {
	   produitservice.saveProduit(prod);
   }
   @RequestMapping(method=RequestMethod.PUT,value="produits")
   public void updateProduit(@RequestBody Produit prod) {
	   produitservice.updateProduit(prod);
   }
}