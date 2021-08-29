package eventEmploye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


		/*
		============================================================
				SELECTIONNER UN EMPLOYER ET LE SUPPRIMER
		============================================================
		 */
public class supprimerEmploye implements ActionListener {
	
	private JTable table;
	private DefaultTableModel model;
	PreparedStatement delete;
	afficherTousEmploye ate = new afficherTousEmploye();
	private reinitEmploye reinit;
	private String requete;
	
	public supprimerEmploye(JTable table, DefaultTableModel model, reinitEmploye reinit) {	
		this.table = table;
		this.model = model;
		this.reinit = reinit;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		int indice = table.getSelectedRow();
		
		/*
		 * Récupérer le nom de l'employé sur la ligne sélectionné
		 */
		
		String nom = model.getValueAt(indice, 1).toString();
		
		int reply = JOptionPane.showInternalConfirmDialog(null,"Voulez-vous supprimer "+nom+" ?", "SUPPRESION",
				JOptionPane.YES_NO_OPTION);
			
		if  (reply == JOptionPane.YES_OPTION) {
			
			try {
				/*
				 * Récupérer l'id de l'employé sur la ligne sélectionné
				 */
				int id = Integer.parseInt(model.getValueAt(indice, 0).toString());
				
				/*
				 * Connexion à la bdd
				 */
				Class.forName("com.mysql.cj.jdbc.Driver");
				String urlDB = "jdbc:mysql://root:@localhost:8889/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Connection cnx = DriverManager.getConnection(urlDB, "root", "root");	
				/*
				 * Requete delete where et exécution
				 */
				requete = "DELETE FROM employe WHERE IDRH="+id;
				delete = cnx.prepareStatement(requete);
				delete.execute();
				
				JOptionPane.showMessageDialog(null, nom+" a été supprimé(e)!");			
				/*
				 * Actualisation le tableau des employés après avoir ajouté un nouveau
				 */
				ate.afficheEmploye(model);
				
				/*
				 * Reinitialisation tous les champs à remplir
				 */
				reinit.actionPerformed(e);
			
				cnx.close();			
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
					
		}			
	}
}
