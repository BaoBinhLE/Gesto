package eventCompte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ReinitCompte implements ActionListener {
	
	JComboBox<String>choixIDRH, choixHabilitation;
	JTextField txtIdentifiant;
	private ArrayList<JButton> listButtons;
	JTextField txtNom, txtPrenom, txtEmploi;
	DefaultTableModel model;
	afficherTousComptes atc = new afficherTousComptes();
	

	public ReinitCompte (JComboBox<String>choixIDRH, JTextField txtIdentifiant, JComboBox<String> choixHabilitation, 
			ArrayList<JButton> listButtons, JTextField txtNom, JTextField txtPrenom, JTextField txtEmploi, DefaultTableModel model){
		this.choixIDRH = choixIDRH;
		this.choixHabilitation = choixHabilitation;
		this.txtIdentifiant = txtIdentifiant;
		this.listButtons = listButtons;
		this.txtNom = txtNom;
		this.txtPrenom = txtPrenom;
		this.txtEmploi = txtEmploi;
		this.model = model;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		choixIDRH.setSelectedIndex(0);
		choixHabilitation.setSelectedIndex(0);
		txtIdentifiant.setText("");
		txtNom.setText("");
		txtPrenom.setText("");
		txtNom.setText("");
		txtEmploi.setText("");
		
		for (JButton btn : listButtons) {
			btn.setEnabled(true);
		}
		atc.afficheComptes(model);
		
	}

}
