package eventEmploye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;



/*
 * Traiter date fin de contrat si CDI était sélectionné
 * Dans le cas de modification :
 * Si l'on met CDI mais le champs de dateFin n'est pas nul => mets dateFin à null, désactiver le calendrier pour dateFin
 * 
 */


public class traiterDureeCDI implements ActionListener {
	
	private JComboBox<String> choixTypeContrat;
	private JDateChooser dateFin;
	private String typeContrat;
	
	public traiterDureeCDI(JComboBox<String> choixTypeContrat, JDateChooser dateFin) {
		this.choixTypeContrat = choixTypeContrat;
		this.dateFin = dateFin;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		typeContrat = this.choixTypeContrat.getSelectedItem().toString();
				
		if (typeContrat.equals("CDI") && dateFin.getDateFormatString().length()!=0) {			
			dateFin.setCalendar(null);
			dateFin.setEnabled(false);			
		}
		else {
			dateFin.setEnabled(true);
		}
			
	}

}
