package Modele;

public class produitModele {
	private String idProduit;
	private String libelleProduit;
	private String categorie;
	private float prixUnitaire;
	private String photo;
	
	// création nouveau produit
	public produitModele( String libelleProduit,
					String categorie,
					float prixUnitaire,
					String photo) {
		this.libelleProduit = libelleProduit;
		this.categorie = categorie;
		this.prixUnitaire = prixUnitaire;
		this.photo = photo;
	}
	
	// modification ou supprimer du produit
	public produitModele( String idProduit,
					String libelleProduit,
					String categorie,
					float prixUnitaire,
					String photo) {
		this.idProduit = idProduit;
		this.libelleProduit = libelleProduit;
		this.categorie = categorie;
		this.prixUnitaire = prixUnitaire;
		this.photo = photo;
	}

	public String getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(String idProduit) {
		this.idProduit = idProduit;
	}

	public String getLibelleProduit() {
		return libelleProduit;
	}

	public void setLibelleProduit(String libelleProduit) {
		this.libelleProduit = libelleProduit;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
