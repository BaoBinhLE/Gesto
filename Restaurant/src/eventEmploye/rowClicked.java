package eventEmploye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;


		/*
		==================================================================
		AFFICHER TOUS LES COORDONNEES UN EMPLOYE EN CLIQUANT SUR UNE LIGNE
		==================================================================
		 */

public class rowClicked implements ListSelectionListener {
	
	private JTextField nom, prenom, dureeHebdo, adresse, tel;
	private JDateChooser dateNaissance, dateDebut, dateFin;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> choixContrat, choixEmploi;
	PreparedStatement select;
	private ArrayList<JButton> listButtons;
	
	public rowClicked (JTextField nom,JTextField prenom ,JDateChooser txtDateNaissanceE, JComboBox<String> typeContrat,
			JDateChooser txtDateDebutE, JDateChooser txtDateFinE, JTextField dureeHebdo, JComboBox<String> typeEmploi,
			JTextField adresse, JTextField tel, JTable table, DefaultTableModel model, ArrayList<JButton> listButtons
			){
				this.nom = nom;
				this.prenom = prenom;
				this.dateNaissance = txtDateNaissanceE;
				this.choixContrat = typeContrat;
				this.dateDebut = txtDateDebutE;
				this.dateFin = txtDateFinE;
				this.dureeHebdo = dureeHebdo;
				this.choixEmploi = typeEmploi;
				this.adresse = adresse;
				this.tel = tel;
				this.table = table;
				this.model = model;
				this.listButtons = listButtons;
	}
	
	@Override
	public void valueChanged (ListSelectionEvent e) {
		
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
				String urlDB = "jdbc:mysql://root:@localhost:3306/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection cnx = DriverManager.getConnection(urlDB);
				
				/*
				 * Requete select where et exécution
				 */
				select = cnx.prepareStatement("SELECT * from employe WHERE IDRH="+id);
				ResultSet rs = select.executeQuery();
				
				while(rs.next()) {
					 nom.setText(rs.getString("Nom"));
					 prenom.setText(rs.getString("Prenom"));
					 dateNaissance.setDate(rs.getDate("Date_naissance"));
					 dateDebut.setDate(rs.getDate("Date_debut_contrat"));
					 dateFin.setDate(rs.getDate("Date_fin_contrat"));
					 dureeHebdo.setText(rs.getString("Duree_hebdomadaire"));
					 adresse.setText(rs.getString("Adresse"));
					 tel.setText(rs.getString("Num_tel"));
					 choixContrat.setSelectedItem(rs.getString("Type_contrat"));
					 choixEmploi.setSelectedItem(rs.getString("Emploi"));
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
