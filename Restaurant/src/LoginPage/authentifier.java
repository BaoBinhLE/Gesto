package LoginPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.ConnexionBDD;
import GUI.mainGUI;
import GUI.moduleEmploye;

		/*
		============================================================
    							AUTHENTIFICATION
		============================================================
		 */

public class authentifier implements ActionListener{
	
	PreparedStatement select;	
	private String requete;	
	private JTextField id;	
	private JPasswordField mdp;	
	private JFrame frame;	
	private int habilitation;
		
	public authentifier(JTextField id, JPasswordField mdp, JFrame frame) {
		
		this.id = id;
		this.mdp = mdp;
		this.frame = frame;
	}
		
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed (ActionEvent e) {		
		/*
		 * Vérifier si les deux champs id et mdp sont bien remplis
		 */		
		boolean trouve = false;
		
		if (id.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Merci de renseigner votre identifiant");
		}
		
		else if (mdp.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Merci de renseigner votre mot de passe");		
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
				requete = "SELECT * FROM compte_user";
				
				select = cnx.prepareStatement(requete);
				
				ResultSet rs = select.executeQuery();
				
				while (rs.next() && !trouve) {				
					String id = rs.getString("Identifiant");
					String mdp = rs.getString("Mot_de_passe");
					habilitation = rs.getInt("Habilitation");
					if (id.equals(this.id.getText()) && mdp.equals(this.mdp.getText())) {
					trouve = true;
					}
				}				
				/*
				 * Si id et mdp sont corrects, lancer l'application
				 */
				if (trouve) {
					mainGUI mainGUI = new mainGUI(habilitation);
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect!!");
				}
				cnx.close();									
			} catch (SQLException e1) {
				e1.printStackTrace();
			}						
		}	
	}	
}
