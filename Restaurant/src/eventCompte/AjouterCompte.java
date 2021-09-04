package eventCompte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.ConnexionBDD;

public class AjouterCompte implements ActionListener {
	
	PreparedStatement insert, select;
	private String requete;
	JComboBox<String> choixIDRH, choixHabilitation;
	afficherTousComptes atc = new afficherTousComptes();
	ReinitCompte rc;
	DefaultTableModel model;
	
	
	public AjouterCompte(JComboBox<String> choixIDRH,JComboBox<String> choixHabilitation, DefaultTableModel model, ReinitCompte rc) {
		this.choixIDRH = choixIDRH;
		this.model = model;
		this.rc = rc;
		this.choixHabilitation = choixHabilitation;
	}
	
	public boolean verifierIDRH() {
		
		boolean existe = false;
		
		int idrh1 = Integer.parseInt(choixIDRH.getSelectedItem().toString());
		
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
			requete = "SELECT * FROM compte_user";
			select = cnx.prepareStatement(requete);	
			ResultSet rs = select.executeQuery();
			/*
			 * Boucle d'affichage
			 */
			while (rs.next() && !existe) {			
				int idrh2 = rs.getInt("IDRH");			
				if  (idrh1 == idrh2) {
					existe = true;
				}	
			}	
			cnx.close();									
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}	
		return existe;	
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		boolean existe = verifierIDRH();		
		if(existe){
			JOptionPane.showMessageDialog(null,"Cette personne a déjà un compte !");			
		}
		else if (choixIDRH.getSelectedItem().toString().length() == 0 || choixHabilitation.getSelectedItem().toString().length() == 0) {
			JOptionPane.showMessageDialog(null,"Merci de renseigner les champs nécessaires!");
		}
		else {
			try {			
				/*
				 * Connexion à la bdd
				 */
				Connection cnx = ConnexionBDD.getConnect() ;			
				/*
				 * Requete insert et exécution
				 */
				requete = "INSERT into compte_user(IDRH, Identifiant, Mot_de_passe, Habilitation) values (?,?,?,?)";
				insert = cnx.prepareStatement(requete);
				
				insert.setInt(1, Integer.parseInt(choixIDRH.getSelectedItem().toString()));
				insert.setString(2, "NB"+this.choixIDRH.getSelectedItem().toString());
				insert.setString(3, "123456");
				insert.setInt(4, 1);
				insert.executeUpdate();
					
				JOptionPane.showMessageDialog(null,"Compte ajouté !");			
				/*
				 * Actualisation le tableau des employés après avoir ajouté un nouveau
				 */
				atc.afficheComptes(model);
								
				/*
				 * Reinitialisation tous les champs à remplir
				 */
				rc.actionPerformed(e);
				/*
				 * Incrémenter le nombre des employés
				 */
											
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} finally {
	        	ConnexionBDD.getClose();
	        }		
		}		
	}
}
