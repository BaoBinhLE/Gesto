package DTO;

public class table {
	private int id ;
	private String libelle;
	private int statut;
	
	public table() {
		
	}
	
	public table(int id, String libelle, int statut) {
		this.id = id;
		this.libelle = libelle;
		this.statut = statut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}
	
}
