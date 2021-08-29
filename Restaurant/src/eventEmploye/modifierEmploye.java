package eventEmploye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

		/*
		============================================================
				MISE A JOUR LES COORDONNES UN EMPLOYE
		============================================================
		 */

public class modifierEmploye implements ActionListener {
		
	private JTable table;
	private DefaultTableModel model;
	private JTextField nom, prenom, dureeHebdo, adresse, tel;
	private JDateChooser dateNaissance, dateDebut, dateFin;
	private JComboBox<String> typeContrat, typeEmploi;
	private String contrat, emploi;
	PreparedStatement update;
	afficherTousEmploye ate = new afficherTousEmploye();
	private reinitEmploye reinit;
	
	public modifierEmploye (JTable table,DefaultTableModel model, JTextField nom,JTextField prenom ,JDateChooser txtDateNaissanceE,JComboBox<String> choixTypeContrat,
							JDateChooser txtDateDebutE, JDateChooser txtDateFinE, JTextField dureeHebdo, JComboBox<String> choixEmploi, JTextField adresse, JTextField tel, reinitEmploye reinit ){
		
		this.table = table;
		this.model = model;
		this.typeContrat = choixTypeContrat;	
		this.typeEmploi = choixEmploi;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = txtDateNaissanceE;
		this.dateDebut = txtDateDebutE;
		this.dateFin = txtDateFinE;
		this.dureeHebdo = dureeHebdo;
		this.adresse = adresse;
		this.tel = tel;
		this.reinit = reinit;
			
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		/*
		 * Récupérer l'id de l'employé sur la ligne séléctionné
		 */
		int indice = table.getSelectedRow();
		int id = Integer.parseInt(model.getValueAt(indice, 0).toString());
		
		boolean isNumeric = this.dureeHebdo.getText().chars().allMatch( Character::isDigit);
	
		String dateFin = null;//Dans le cas CDI, insérer dateFin =  null
		
		contrat = this.typeContrat.getSelectedItem().toString();
		emploi = this.typeEmploi.getSelectedItem().toString();
	
		/*
		 * Vérifier si tous les champs sont bien remplis
		 */
		if (this.dateNaissance.getDate() ==null || this.dateDebut.getDate() ==null
			|| this.nom.getText().length()==0 || this.prenom.getText().length()==0 || emploi.length()==0 || contrat.length()==0
			|| this.adresse.getText().length()==0 || this.tel.getText().length()==0 || this.dureeHebdo.getText().length()==0 
				) {	
				JOptionPane.showMessageDialog(null, "Merci de renseigner tous les champs nécessaires!!!");	
			}
		
		else if(!isNumeric){
			JOptionPane.showMessageDialog(null, "Durée hebdomadaire doit être un chiffre!");				
		}
		
		else {			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nom = this.nom.getText();
			String prenom = this.prenom.getText();
			String dateNaissance = sdf.format(this.dateNaissance.getDate());
			String dateDebut = sdf.format(this.dateDebut.getDate());
			int dureeHebdo = Integer.parseInt(this.dureeHebdo.getText());
			String adresse = this.adresse.getText();
			String tel = this.tel.getText();
			
			/*
			 * Dans tous les cas sauf CDI :
			 * Vérifier si dateFin >= dateDebut
			 * Si oui, convertir dateFin en String puis insérer dans la bdd
			 * Sinon dateFin = null
			 */
			if (!contrat.equals("CDI")) {
				if (this.dateFin.getDate().before(this.dateDebut.getDate())) {
					JOptionPane.showMessageDialog(null, "Dates invalides!");
				}
				else {
					dateFin = sdf.format(this.dateFin.getDate());
					try {			
						/*
						 * Connexion à la bdd
						 */
						Class.forName("com.mysql.cj.jdbc.Driver");
						String urlDB = "jdbc:mysql://root:@localhost:8889/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						Connection cnx = DriverManager.getConnection(urlDB, "root", "root");	
						/*
						 * Requete update et exécution
						 */			
						String requete = "UPDATE employe set Nom = ? , Prenom = ? , Date_naissance = ? , Adresse = ? , Num_tel = ? , Type_contrat = ? , Date_debut_contrat  = ? , Date_fin_contrat = ?, Duree_hebdomadaire = ?, Emploi  = ? WHERE IDRH ="+id;						
						update = cnx.prepareStatement(requete);

						update.setString(1, nom);
						update.setString(2, prenom);
						update.setString(3, dateNaissance);
						update.setString(4, adresse);
						update.setString(5, tel);
						update.setString(6, contrat);
						update.setString(7, dateDebut);
						update.setString(8, dateFin);
						update.setInt(9, dureeHebdo);
						update.setString(10, emploi);
						
						update.execute();
				
						JOptionPane.showMessageDialog(null, nom+" a été modifié!");	
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
	}	
}
