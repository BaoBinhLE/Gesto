package eventEmploye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

import Controleur.ConnexionBDD;


		/*
		============================================================
    		FONCTION AFFICHER TOUS LES EMPLOYES
		============================================================
		 */

public class afficherTousEmploye {
		private String requete;
		PreparedStatement afficher;
	
public void afficheEmploye(DefaultTableModel model) {
		
		model.setNumRows(0); //Reinit le tableau des employés
		
		try {
			/*
			 * Connexion à la bdd
			 */		
			Connection cnx = ConnexionBDD.getConnect() ;		
			
			/*
			 * Requete select * et exécution
			 */
			
			requete = "SELECT * FROM employe";
			afficher = cnx.prepareStatement(requete);	
			ResultSet rs = afficher.executeQuery();
			
			/*
			 * Boucle d'affichage
			 */
			while (rs.next()) {			
				int id = rs.getInt("IDRH");		
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				Date dateNaissance = rs.getDate("Date_naissance");
				String adresse = rs.getString("Adresse");
				String tel = rs.getString("Num_tel");
				String typeContrat = rs.getString("Type_contrat");
				Date dateDebut = rs.getDate("Date_debut_contrat");
				Date dateFin = rs.getDate("Date_fin_contrat");
				int dureeHebdo = rs.getInt("Duree_hebdomadaire");		
				String Emploi = rs.getString("Emploi");		
				model.addRow(new Object[] {id, nom, prenom, dateNaissance, adresse, typeContrat, dateDebut});						
			}		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			ConnexionBDD.getClose();
        }		
	}
}
