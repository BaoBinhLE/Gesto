package eventEmploye;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import DAO.ConnexionBDD;

import java.awt.event.ActionEvent;


		/*
		============================================================
    					AJOUTER UN NOUVEAU EMPLOYE
		============================================================
		 */

public class ajouterEmploye implements ActionListener{
	
	private JTextField nom, prenom, dureeHebdo, adresse, tel;
	private JDateChooser dateNaissance, dateDebut, dateFin;
	private DefaultTableModel model;
	private JComboBox<String> typeContrat, typeEmploi;
	private String contrat, emploi;
	private reinitEmploye reinit;
	private String requete;
	boolean ajoute = false;
	
	afficherTousEmploye ate = new afficherTousEmploye();
	
	PreparedStatement insert;
	
	public ajouterEmploye (JTextField nom,JTextField prenom ,JDateChooser txtDateNaissanceE,JComboBox<String> choixTypeContrat,
								JDateChooser txtDateDebutE, JDateChooser txtDateFinE, JTextField dureeHebdo, JComboBox<String> choixEmploi,
								JTextField adresse, JTextField tel, DefaultTableModel model, reinitEmploye reinit
								){
		
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
		this.model = model;
		this.reinit = reinit;
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		
		String dateFin = null; //Dans le cas CDI, insérer la date fin : null
		contrat = this.typeContrat.getSelectedItem().toString();
		emploi = this.typeEmploi.getSelectedItem().toString();	
		boolean isNumeric = this.dureeHebdo.getText().chars().allMatch( Character::isDigit);	
		/*
		 * Vérifier si tous les champs sont bien remplis
		 */
		
		if (this.dateNaissance.getDate() ==null || this.dateDebut.getDate() ==null
			|| this.nom.getText().length()==0 || this.prenom.getText().length()==0 || emploi.length()==0
			|| this.adresse.getText().length()==0 || this.tel.getText().length()==0 || this.dureeHebdo.getText().length()==0 
				) {	
				JOptionPane.showMessageDialog(null, "Merci de renseigner tous les champs nécessaires!!!");	
			}			
		/*
		 * Vérifier si l'on remplit un chiffre dans dureeHebdomadaire
		 */
		
		else if(!isNumeric){
			JOptionPane.showMessageDialog(null, "Durée habdomadaire doit être un chiffre!");				
		}
		
		else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String nom = this.nom.getText().toUpperCase();
				String prenom = this.prenom.getText().toUpperCase();
				String dateNaissance = sdf.format(this.dateNaissance.getDate());
				String dateDebut = sdf.format(this.dateDebut.getDate());
				int dureeHebdo = Integer.parseInt(this.dureeHebdo.getText());
				String adresse = this.adresse.getText().toUpperCase();
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
							Connection cnx = ConnexionBDD.getConnect() ;					
							/*
							 * Requete insert et exécution
							 */
							requete = "INSERT into employe(Nom,Prenom,Date_naissance,Adresse,Num_tel,Type_contrat,Date_debut_contrat,Date_fin_contrat,Duree_hebdomadaire,Emploi) values (?,?,?,?,?,?,?,?,?,?)";
							insert = cnx.prepareStatement(requete);
								
							insert.setString(1, nom);
							insert.setString(2, prenom);
							insert.setString(3, dateNaissance);
							insert.setString(4, adresse);
							insert.setString(5, tel);
							insert.setString(6, contrat);
							insert.setString(7, dateDebut);					
							insert.setInt(9, dureeHebdo);
							insert.setString(10, emploi);		
							insert.setString(8, dateFin);		
							
							insert.executeUpdate();
								
							JOptionPane.showMessageDialog(null, nom+" a été ajouté");			
							/*
							 * Actualisation le tableau des employés après avoir ajouté un nouveau
							 */
							ate.afficheEmploye(model);						
							/*
							 * Reinitialisation tous les champs à remplir
							 */
							reinit.actionPerformed(e);
							/*
							 * Incrémenter le nombre des employés
							 */
															
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						}finally {
							ConnexionBDD.getClose();
				        }	
					
					}						
				}						
			}		
	}
}
