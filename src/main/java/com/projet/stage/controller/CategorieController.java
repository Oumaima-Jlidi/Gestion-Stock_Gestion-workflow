package com.projet.stage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.stage.entities.Categorie;
import com.projet.stage.services.CategorieService;

@CrossOrigin(origins="*")
@RestController
public class CategorieController {
	@Autowired
	private CategorieService categorieservice;
	
	 @RequestMapping(method=RequestMethod.GET,value="categories")
	 List<Categorie> getAllCategories(){
			return categorieservice.getAllCategories();
		}
	  
	   @RequestMapping("categories/{id}")
	   public Categorie getCategorie(@PathVariable long id) {
		return categorieservice.getCategorie(id);   
	   }
	   
	   @RequestMapping(method= RequestMethod.POST,value="categories")
	   public Categorie addCategorie(@RequestBody Categorie cat) {
		   return categorieservice.saveCategorie(cat);
	   }
	   
	   @RequestMapping(method=RequestMethod.DELETE,value="categories/{id}")
	   public void deleteCategorie(@PathVariable 
			   long id) {
		   categorieservice.deleteCategorieById(id);
	   }
	   @RequestMapping(method=RequestMethod.PUT,value="categories")
	   public Categorie  updateCategorie(@RequestBody Categorie cat) {
		   return categorieservice.updateCategorie(cat);
		   }
}
