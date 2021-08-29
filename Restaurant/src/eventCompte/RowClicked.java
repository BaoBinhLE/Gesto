package eventCompte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class RowClicked implements ListSelectionListener {
	
	JComboBox<String> choixIDRH, choixHabilitation;

	JTextField nom, prenom, identifiant, emploi;
	
	JTable table;
	DefaultTableModel model;
	
	ArrayList<JButton> listButtons;
	
	public RowClicked (JComboBox<String> choixIDRH, JComboBox<String> choixHabilitation, 
			JTextField identifiant, JTextField nom, JTextField prenom, JTextField emploi, 
			JTable table, DefaultTableModel model, ArrayList<JButton> listButtons){
		
		this.choixIDRH = choixIDRH;
		this.choixHabilitation = choixHabilitation;
		this.nom = nom;
		this.prenom = prenom;
		this.emploi = emploi;
		this.identifiant = identifiant;
		this.table = table;
		this.model = model;
		this.listButtons = listButtons;
	}
	
	@Override
	public void valueChanged (ListSelectionEvent e) {
		PreparedStatement select;
		String requete;
		
		int indice = table.getSelectedRow();
		
		/*
		 * Désactiver les boutons Ajouter et Rechercher
		 */
		for (JButton btn : listButtons) {
			if (btn.getText().matches("Ajouter|Rechercher")){
				btn.setEnabled(false);		
			}
		}
	
		/*
		 * Vérifier si une ligne est sélectionnée
		 */
		
		if (indice >= 0) {
	
			try {
				int id = Integer.parseInt(model.getValueAt(indice, 0).toString());
				
				/*
				 * Connexion à la bdd
				 */
				Class.forName("com.mysql.cj.jdbc.Driver");
				String urlDB = "jdbc:mysql://root:@localhost:8889/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection cnx = DriverManager.getConnection(urlDB, "root", "root");			
				/*
				 * Requete select where et exécution
				 */
				requete = "SELECT compte_user.IDRH, compte_user.Identifiant, compte_user.Habilitation, employe.Nom, employe.Prenom, employe.Emploi FROM compte_user INNER JOIN employe ON compte_user.IDRH = employe.IDRH WHERE compte_user.IDRH ="+id;
				select = cnx.prepareStatement(requete);	
				ResultSet rs = select.executeQuery();
			
				while(rs.next()) {
					
					 nom.setText(rs.getString("Nom"));
					 prenom.setText(rs.getString("Prenom"));
					 emploi.setText(rs.getString("Emploi"));
					 choixIDRH.setSelectedItem(rs.getString("IDRH"));
					 choixHabilitation.setSelectedItem(rs.getString("Habilitation"));
					 identifiant.setText(rs.getString("Identifiant"));
				 }
									
				cnx.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}	
		}	
	}

}
