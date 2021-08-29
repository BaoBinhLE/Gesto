package eventEmploye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class Employe {
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresse;
	private String tel;
	private String typeContrat;
	private Date dateDebut;
	private Date dateFin;
	private int dureeHebdo;
	private String emploi;
	private ArrayList<Employe> listeEmploye;
	private static int nbEmploye = 0;
	private static int IDRH;
	private String requete;
	PreparedStatement count;

	
	public Employe(String nom, String prenom, Date dateNaissance, String adresse, String tel, String typeContrat, Date dateDebut, Date dateFin, int dureeHebdo, String emploi) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.tel = tel;
		this.typeContrat = typeContrat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dureeHebdo = dureeHebdo;
		this.emploi = emploi;
	}
	
	@Override
	
	public String toString() {
		return this.nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPreom() {
		return prenom;
	}
	
	public Date getDateNaissance() {
		return dateNaissance;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getTypeContrat() {
		return typeContrat;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public int getDureeHebdo() {
		return dureeHebdo;
	}
	
	public String getEmploi() {
		return emploi;
	}
	
	public int getNbEmploye() {
		
		try {			
			/*
			 * Connexion à la bdd
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");		
			String urlDB = "jdbc:mysql://root:@localhost:8889/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection cnx = DriverManager.getConnection(urlDB, "root", "root");			
			/*
			 * Requete insert et exécution
			 */
			requete = "SELECT * COUNT(*) FROM employe";
			count = cnx.prepareStatement(requete);
			count.executeUpdate();
					
			cnx.close();									
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
		
		return nbEmploye;
	}
	
	public int getIDRH() {
		return IDRH;
	}
	
}
