package DTO;


import java.util.Date;

public class commande {
	private int idCommande;
    private int IDRH;
    private int idClient;
    private int idTable;
    private Date date;
    private int statut;
    private int typeCommande;
    private float total;
    
    public commande() {
    	
    }
    
	public commande(int idCommande, int iDRH, int idClient, int idTable, Date date, int statut, int typeCommande,
			float total) {
		super();
		this.idCommande = idCommande;
		IDRH = iDRH;
		this.idClient = idClient;
		this.idTable = idTable;
		this.date = date;
		this.statut = statut;
		this.typeCommande = typeCommande;
		this.total = total;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public int getIDRH() {
		return IDRH;
	}

	public void setIDRH(int iDRH) {
		IDRH = iDRH;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public int getTypeCommande() {
		return typeCommande;
	}

	public void setTypeCommande(int typeCommande) {
		this.typeCommande = typeCommande;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
    
    
}
