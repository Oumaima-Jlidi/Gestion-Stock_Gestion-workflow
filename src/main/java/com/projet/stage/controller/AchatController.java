package com.projet.stage.controller;

import com.projet.stage.entities.Achat;
import com.projet.stage.entities.Produit;
import com.projet.stage.services.AchatService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AchatController {

    @Autowired
    private AchatService achatService;

    @PostMapping("/acheter-produit")
    public ResponseEntity<?> acheterProduit(@RequestBody Achat request) {
        try {
            Achat achat = achatService.acheterProduit(
                request.getId(),
                request.getNom_client(),
                request.getAdresse_client(),
                request.getQuantite()
            );
            // Formater la date d'achat avant de la renvoyer dans la réponse
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(achat.getDateAchat());
            return ResponseEntity.ok(achat); // Renvoyer l'objet achat dans la réponse
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/achats-by-nom-produit/{produitId}")
    public List<Achat> getAchatsByNomProduit(@PathVariable long produitId) {
        return achatService.getAchatsByNomProduit(produitId);
    }

    @RequestMapping("achats/{id}")
    public Achat getAchat(@PathVariable long id) {
 	return achatService.getAchatById(id);   
    }
    
    @DeleteMapping("/achats/{achatId}")
    public ResponseEntity<?> deleteAchat(@PathVariable Long achatId) {
        try {
            achatService.deleteAchat(achatId);
            return ResponseEntity.ok().build(); // Réponse 200 OK si la suppression réussit
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @RequestMapping(method=RequestMethod.PUT,value="achats")

    public ResponseEntity<?> updateAchat(@RequestBody Achat a){
    	try {
    		achatService.updateAchat(a);
    		return ResponseEntity.ok().build();
    	}catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

    	}
    	}
}
