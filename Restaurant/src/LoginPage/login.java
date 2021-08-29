package LoginPage;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

		/*
		============================================================
							INTERFACE LOGIN
		============================================================
		 */

public class login {
	
	public login() {
		
		JFrame frame = new JFrame("LOGIN FORM");		
		Container cprincipal = frame.getContentPane();	
		JPanel panelCenter = new JPanel();
		cprincipal.add(panelCenter);
		frame.setBackground(Color.ORANGE);
		
		/*
		============================================================
							COMPOSANTS
		============================================================
		 */
		
		JLabel id = new JLabel("Identifiant");		
		JLabel mdp = new JLabel("Mot de passe");		
		JTextField txtID = new JTextField("", 15);	
		JPasswordField txtMdp = new JPasswordField("", 15);	
		JButton btnConnect = new JButton("Se connecter");
		JButton btnReset = new JButton("Reset");
		
		/*
		============================================================
						ARRANGEMENT DES COMPOSANTS
		============================================================
		 */
			
		panelCenter.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 2;		
		gc.gridy = 0;		
		panelCenter.add(id, gc);
		
		gc.gridx = 2;	
		gc.gridy = 1;	
		panelCenter.add(mdp, gc);
		
		gc.gridx = 3;	
		gc.gridy = 0;	
		panelCenter.add(txtID, gc);
		
		gc.gridx = 3;	
		gc.gridy = 1;
		panelCenter.add(txtMdp, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;	
		gc.gridx = 3;	
		gc.gridy = 2;
		panelCenter.add(btnConnect, gc);
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 3;
		gc.gridy = 2;
		
		panelCenter.add(btnReset, gc);
		
		/*
		============================================================
						BRANCHEMENT DES EVENTS
		============================================================
		 */
		
					/*
					 * SE CONNECTER
					 */
		
		authentifier check = new authentifier(txtID, txtMdp, frame);
		
		btnConnect.addActionListener(check);
		
					/*
					 * RESET
					 */
		
		reinit reset = new reinit(txtID, txtMdp);
		
		btnReset.addActionListener(reset);
		
		/*
		============================================================
						DIVERS
		============================================================
		 */

		frame.setVisible(true);	
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

}
