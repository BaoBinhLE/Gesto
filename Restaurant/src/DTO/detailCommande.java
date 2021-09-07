package DTO;

public class detailCommande {
	private int idCommande;
	private int idProduit;
	private int quantite;
	private float prixUnitaire;
	
	public detailCommande() {
		
	}

	public detailCommande(int idCommande, int id_produit, int quantite) {
		super();
		this.idCommande = idCommande;
		this.idProduit = id_produit;
		this.quantite = quantite;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaite) {
		this.prixUnitaire = prixUnitaite;
	}
}

