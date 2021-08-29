package eventEmploye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

		/*
		============================================================
				REINITIATION TOUS LES CHAMPS A REMPLIR
		============================================================
		 */

		/*
		 * Réactiver tous les boutons
		 */

public class reinitEmploye implements ActionListener {
	
	private JTextField nom, prenom, dureeHebdo, adresse, tel;
	private JDateChooser dateNaissance, dateDebut, dateFin;
	private JComboBox<String> choixContrat, choixEmploi;
	private JTable table;
	private ArrayList<JButton> listButtons;
	afficherTousEmploye ate = new afficherTousEmploye();
	DefaultTableModel model;
	
	public reinitEmploye (JTextField nom,JTextField prenom ,JDateChooser txtDateNaissanceE, JComboBox<String> typeContrat,
			JDateChooser txtDateDebutE, JDateChooser txtDateFinE, JTextField dureeHebdo, JComboBox<String> emploi,
			JTextField adresse, JTextField tel, JTable table, ArrayList<JButton> listButtons, DefaultTableModel model){
			
			this.nom = nom;
			this.prenom = prenom;
			this.dateNaissance = txtDateNaissanceE;
			this.choixContrat = typeContrat;
			this.dateDebut = txtDateDebutE;
			this.dateFin = txtDateFinE;
			this.dureeHebdo = dureeHebdo;
			this.choixEmploi = emploi;
			this.adresse = adresse;
			this.tel = tel;
			this.table = table;
			this.listButtons = listButtons;
			this.model = model;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		this.nom.setText("");
		this.prenom.setText("");
		this.dateNaissance.setCalendar(null);
		this.adresse.setText("");
		this.tel.setText("");
		this.choixContrat.setSelectedIndex(0);
		this.dureeHebdo.setText("");
		this.choixEmploi.setSelectedIndex(0);
		this.dateDebut.setCalendar(null);
		this.dateFin.setCalendar(null);
		table.clearSelection();
		ate.afficheEmploye(model);
			
		for (JButton btn : listButtons) {
			btn.setEnabled(true);
		}
	}
}
