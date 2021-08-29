package Modele;

public class moduleCategorie {
	private String libelle_categorie;
	

	public moduleCategorie(String libelle_categorie) {
		this.libelle_categorie = libelle_categorie;
	}
	
	public String getLibelle_categorie() {
		return libelle_categorie;
	}

	public void setLibelle_categorie(String libelle_categorie) {
		this.libelle_categorie = libelle_categorie;
	}
	
	@Override
	public String toString() {//manipuler agréablement le contenu
		return this.libelle_categorie;
	}
	
}
