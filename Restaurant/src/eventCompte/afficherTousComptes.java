package eventCompte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.table.DefaultTableModel;

import Controleur.ConnexionBDD;

public class afficherTousComptes {
	
	private String requete;
	PreparedStatement afficher;
	
	public void afficheComptes(DefaultTableModel model) {
		
		model.setNumRows(0); //Reinit le tableau des comptes
		
		try {
			/*
			 * Connexion à la bdd
			 */		
			Connection cnx = ConnexionBDD.getConnect() ;		
			
			/*
			 * Requete select * et exécution
			 */
			
			requete = "SELECT compte_user.IDRH, compte_user.Identifiant, compte_user.Habilitation, employe.Nom, employe.Prenom FROM compte_user INNER JOIN employe ON compte_user.IDRH = employe.IDRH";
			afficher = cnx.prepareStatement(requete);	
			ResultSet rs = afficher.executeQuery();
			
			/*
			 * Boucle d'affichage
			 */
			while (rs.next()) {			
				int idrh = rs.getInt("IDRH");
				String identifiant = rs.getString("Identifiant");
				int habilitation = rs.getInt("Habilitation");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				model.addRow(new Object[] {idrh, identifiant,nom,prenom, habilitation});						
			}		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
        	ConnexionBDD.getClose();
        }
	}
	
	
	

}
