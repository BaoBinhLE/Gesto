package LoginPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class reinit implements ActionListener {
	
	private JTextField id;
	private JTextField mdp;

	public reinit(JTextField id, JTextField mdp) {
		this.id = id;
		this.mdp = mdp;	
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		this.id.setText("");
		this.mdp.setText("");	
	}


}
