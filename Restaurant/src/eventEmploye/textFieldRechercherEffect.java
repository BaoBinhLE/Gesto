package eventEmploye;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

		/*
		============================================================
			GERER EFFECT DU CHAMPS DE RECHERCHE EN CLIQUANT
		============================================================
		 */


public class textFieldRechercherEffect implements FocusListener {
	
	JTextField nom = new JTextField  ();
	
	public textFieldRechercherEffect(JTextField nom) {
		this.nom = nom;	
	}
	
	/*
	 * En cliquant :
	 * Supprimer le placeholder et mettre la police en gris
	 */
	public void focusGained(FocusEvent e) {		
		nom.setText("");
		nom.setForeground(Color.BLACK);		
	}
	
	/*
	 * En sortant la zone de recherche :
	 * Remettre le placeholder si l'utilisateur ne remplit pas
	 */
	
	public void focusLost(FocusEvent e) {	
		if (nom.getText().toString().length() == 0) {			
			nom.setText("Recherche par nom...");
			nom.setForeground(Color.GRAY);		
		}	
	}
}
