package eventEmploye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

		/*
		============================================================
					RECHERCHER EMPLOYE PAR NOM
		============================================================
		 */

public class rechercherEmploye implements ActionListener {

	private DefaultTableModel model;
	private JTextField nom;
	PreparedStatement recherche;
	private String requete;
	String nomrecherche;
	afficherTousEmploye ate = new afficherTousEmploye();
	
	public rechercherEmploye(DefaultTableModel model, JTextField nom) {
		
		this.model = model;
		this.nom = nom;
			
	}
	
	public void actionPerformed (ActionEvent e) {
		
		boolean trouve = false; //Test trouvé
		model.setNumRows(0);
		
		/*
		 * Supprimer le placeholder
		 */
		if (nom.getText().equals("Recherche par nom...")) {
			JOptionPane.showMessageDialog(null, "Merci de renseigner un nom!");
			ate.afficheEmploye(model);
		}
		
		else {		
			nomrecherche = this.nom.getText().toUpperCase();//Récupérer nomrecherche dans le zone de recherche	
			try {			
				/*
				 * Connexion à la bdd
				 */
				Class.forName("com.mysql.cj.jdbc.Driver");
				String urlDB = "jdbc:mysql://root:@localhost:8889/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection cnx = DriverManager.getConnection(urlDB, "root", "root");	
				/*
				 * Requete select where et exécution
				 */	
				requete = "SELECT * FROM employe WHERE Nom like '%"+nomrecherche+"%'";
				recherche = cnx.prepareStatement(requete);
				
				ResultSet rs = recherche.executeQuery();
				
				while (rs.next()) {		
					int id = rs.getInt("IDRH");		
					String nom = rs.getString("Nom");
					String prenom = rs.getString("Prenom");
					Date dateNaissance = rs.getDate("Date_naissance");
					String adresse = rs.getString("Adresse");				
					String typeContrat = rs.getString("Type_contrat");
					Date dateDebut = rs.getDate("Date_debut_contrat");	
					model.addRow(new Object[] {id, nom, prenom, dateNaissance, adresse, typeContrat, dateDebut});
					trouve = true;			
				}
				
				if (!trouve) {
					JOptionPane.showMessageDialog(null, nomrecherche+" n'existe pas!!!");
					ate.afficheEmploye(model);		
				}	
				
			} catch (SQLException e1) {		
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {			
				e1.printStackTrace();
			}	
			
		}	
		
	}

}
