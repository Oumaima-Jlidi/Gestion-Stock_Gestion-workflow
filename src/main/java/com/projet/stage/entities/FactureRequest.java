package com.projet.stage.entities;

public class FactureRequest {
	   private Long achatId;
	    private Long produitId;
		public Long getAchatId() {
			return achatId;
		}
		public void setAchatId(Long achatId) {
			this.achatId = achatId;
		}
		public Long getProduitId() {
			return produitId;
		}
		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}
	    
}
