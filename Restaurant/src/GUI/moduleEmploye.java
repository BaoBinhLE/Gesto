package GUI;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Custom.monButton;
import Custom.monTableau;
import eventEmploye.ajouterEmploye;
import eventEmploye.afficherTousEmploye;
import eventEmploye.Employe;
import eventEmploye.modifierEmploye;
import eventEmploye.rechercherEmploye;
import eventEmploye.reinitEmploye;
import eventEmploye.rowClicked;
import eventEmploye.supprimerEmploye;
import eventEmploye.textFieldRechercherEffect;
import eventEmploye.traiterDureeCDI;
		/*
		============================================================
            INITIATION L'INTERFACE GRAPHIQUE DU MODULE EMPLOYE
		============================================================
		 */
		
public class moduleEmploye extends JPanel {

	ArrayList<Employe> listeEmploye = new ArrayList<Employe>();

	JPanel panelLeft, panelTop, panelCenter, panelRemplirE;
	
	JLabel nomE, preNomE, dateNaissanceE, dateDebutE, dateFinE, typeContratE, dureeHebdoE, emploiE, adresseE, telE,titre;
	JTextField txtNomE, txtPreNomE, txtDureeHebdoE, txtAdresseE, txtTelE;
	JDateChooser txtDateNaissanceE, txtDateDebutE, txtDateFinE;
	JComboBox<String> choixTypeContrat , choixEmploi;
	
	monTableau table;
	DefaultTableModel model;
	JScrollPane pane;
	
	JTextField recherche;
	JButton btnRecherche;

	JPanel panelButtons;	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset; 	
	monButton btnAjoute, btnMod, btnSupp, btnReset;
	ArrayList<JButton> listButtons = new ArrayList<JButton>();
	
	afficherTousEmploye ate = new afficherTousEmploye();

	Font f1 = new Font("TimesRoman", Font.BOLD, 18);
	Font f2 = new Font("TimesRoman", Font.BOLD, 14);
	
	public moduleEmploye () {
		
		
		addComponents();
		
		addEvents();
		/*
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setLayout(new BorderLayout());
		JPanel panelLogo = new JPanel();
		JPanel panelMenu = new JPanel();
		panelLeft.add(panelLogo, BorderLayout.NORTH);
		panelLeft.add(panelMenu, BorderLayout.CENTER);
		
		panelLogo.setOpaque(false);
		panelMenu.setLayout(new BoxLayout(panelMenu,BoxLayout.Y_AXIS));
		panelMenu.setOpaque(false);	
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/logo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		panelLogo.add(new JLabel(imageIcon));
		JPanel m1 = new JPanel();
		JPanel m2 = new JPanel();
		JPanel m3 = new JPanel();	
		JPanel m4 = new JPanel();
				
		JLabel menu1 = new JLabel("EMPLOYEE");
		JLabel menu2 = new JLabel("MENU");
		JLabel menu3 = new JLabel("COMMANDE");
		JLabel menu4 = new JLabel("CHIFFRE D'AFFAIRE");
		
		ImageIcon iconPlats = new ImageIcon(new ImageIcon("images/Menu/plats.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon iconEmploye = new ImageIcon(new ImageIcon("images/Menu/employe.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon iconCommande = new ImageIcon(new ImageIcon("images/Menu/commande.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon iconCA = new ImageIcon(new ImageIcon("images/Menu/ca.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	
		menu1.setForeground(Color.WHITE);
		menu2.setForeground(Color.WHITE);				
		menu3.setForeground(Color.WHITE);				
		menu4.setForeground(Color.WHITE);
	
		panelMenu.add(m1);
		panelMenu.add(m2);
		panelMenu.add(m3);
		panelMenu.add(m4);
		
		m1.add(new JLabel(iconPlats));
		m2.add(new JLabel(iconEmploye));
		m3.add(new JLabel(iconCommande));
		m4.add(new JLabel(iconCA));
		
		m1.add(menu1);
		m2.add(menu2);
		m3.add(menu3);
		m4.add(menu4);
		
		m1.setMaximumSize(new Dimension(Integer.MAX_VALUE, m1.getMinimumSize().height));
		m2.setMaximumSize(new Dimension(Integer.MAX_VALUE, m2.getMinimumSize().height));
		m3.setMaximumSize(new Dimension(Integer.MAX_VALUE, m3.getMinimumSize().height));
		m4.setMaximumSize(new Dimension(Integer.MAX_VALUE, m4.getMinimumSize().height));
	     
		m1.setOpaque(true);
		m2.setOpaque(true);
		m3.setOpaque(true);
		m4.setOpaque(true);
		
		m1.setBackground(Color.ORANGE);
		m2.setBackground(Color.DARK_GRAY);
		m3.setBackground(Color.DARK_GRAY);
		m4.setBackground(Color.DARK_GRAY);
		
		m1.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		m2.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		m3.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		m4.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		
		
		
		
		
		
		
		
		
		GridBagConstraints gc2 = new GridBagConstraints();

		
		gc2.insets = new Insets(20,0,0,0);
	
		gc2.fill = GridBagConstraints.HORIZONTAL;
		
		gc2.gridx = 0;
		gc2.gridy = 0;
		panelMenu.add(m1, gc2);
		
		
		gc2.gridx = 1;
		gc2.gridy = 0;
		panelMenu.add(menu1, gc2);
		
		
		
		gc2.gridx = 0;
		gc2.gridy = 1;
		panelMenu.add(m2,gc2);
		
		
		gc2.gridx = 1;
		gc2.gridy = 1;
		panelMenu.add(menu2, gc2);
		
		
		
		gc2.gridx = 0;
		gc2.gridy = 2;
		panelMenu.add(m3, gc2);
		
		
		gc2.gridx = 1;
		gc2.gridy = 2;
		panelMenu.add(menu3, gc2);
		
		
		gc2.gridx = 0;
		gc2.gridy = 3;
		panelMenu.add(m4, gc2);
		
		
		
		gc2.gridx = 1;
		gc2.gridy = 3;
		panelMenu.add(menu4, gc2);
		
		
		
		
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.anchor = GridBagConstraints.LINE_START;	
		
		m1.add(new JLabel(iconPlats));
		
		m2.add(new JLabel(iconPlats));
		m3.add(new JLabel(iconPlats));
		m4.add(new JLabel(iconPlats));
		
		
		
	
		
		m1.add(menu1);
		m2.add(menu2);
		m3.add(menu3);
		m4.add(menu4);
		
	
		
		m1.setBackground(Color.ORANGE);
		
		*/		
	}
	
	public void addComponents() {
			
		/*
        ============================================================
                         CREATION 3 ZONES PRINCIPALES
        ============================================================
         */
		
		panelLeft = new JPanel();
		panelTop = new JPanel();
		panelCenter = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelLeft, BorderLayout.WEST);	
		
		
		/*
        ============================================================
                         COMPOSANTS DE PANEL CENTER
        ============================================================
         */
		
		panelRemplirE = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelRemplirE);
		panelRemplirE.setOpaque(true);
		panelRemplirE.setBackground(new Color(250, 240, 230));
		
		nomE = new JLabel("Nom");
		preNomE = new JLabel("Prenom");
		dateNaissanceE = new JLabel("Date de naissance");
		dateDebutE = new JLabel("Date début");
		dateFinE = new JLabel("Date fin");
		typeContratE = new JLabel("Type contrat");
		dureeHebdoE = new JLabel("Durée hebdomadaire");
		emploiE = new JLabel("Emploi");
		adresseE = new JLabel("Adresse");
		telE = new JLabel("Telephone");
		titre = new JLabel("Gestion de la liste des employés");
		
		txtNomE = new JTextField(15);
		txtPreNomE = new JTextField(15);
		txtDateNaissanceE = new JDateChooser();
		txtDateNaissanceE.setDateFormatString("dd-MM-yyyy");
		
		txtDateDebutE = new JDateChooser();
		txtDateDebutE.setDateFormatString("dd-MM-yyyy");
		
		txtDateFinE = new JDateChooser();
		txtDateFinE.setDateFormatString("dd-MM-yyyy");
		txtDureeHebdoE = new JTextField(15);
	
		txtAdresseE = new JTextField(15);
		txtTelE = new JTextField(15);
		
		choixTypeContrat = new JComboBox<String>();
		choixTypeContrat.addItem("");
		choixTypeContrat.addItem("CDI");
		choixTypeContrat.addItem("CDD");
		choixTypeContrat.addItem("Interim");
		choixTypeContrat.addItem("Stage");
		choixTypeContrat.addItem("Alternance");
		
		choixEmploi = new JComboBox<String>();
		choixEmploi.addItem("");
		choixEmploi.addItem("Serveur(se)");
		choixEmploi.addItem("Caissier(e)");
		choixEmploi.addItem("Plongeur(se)");
		choixEmploi.addItem("Cuisinier(e)");
		/*
		 		ARRANGEMENT DES COMPONENTS DANS LE PANEL CHAMPS A REMPLIR
		
		 */		
		panelRemplirE.setLayout(new GridBagLayout());
		
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.insets = new Insets(10,0,0,0);
		gc1.anchor = GridBagConstraints.LINE_START;
		
		gc1.gridx = 1;
		gc1.gridy = 0;
		panelRemplirE.add(titre, gc1);
		titre.setFont(f1);
		
		gc1.gridx = 0;
		gc1.gridy = 1;
		panelRemplirE.add(nomE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 1;
		panelRemplirE.add(txtNomE, gc1);
	
		gc1.gridx = 2;
		gc1.gridy = 1;
		panelRemplirE.add(preNomE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 1;
		panelRemplirE.add(txtPreNomE,gc1);
				
		gc1.gridx = 0;
		gc1.gridy = 2;
		panelRemplirE.add(adresseE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 2;
		panelRemplirE.add(txtAdresseE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 2;
		panelRemplirE.add(telE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 2;
		panelRemplirE.add(txtTelE,gc1);
			
		gc1.gridx = 0;
		gc1.gridy = 3;
		panelRemplirE.add(dateNaissanceE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 3;
		panelRemplirE.add(txtDateNaissanceE,gc1);	
		
		gc1.gridx = 2;
		gc1.gridy = 3;
		panelRemplirE.add(typeContratE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 3;
		panelRemplirE.add(choixTypeContrat, gc1);
		
		gc1.gridx = 0;
		gc1.gridy = 4;
		panelRemplirE.add(dateDebutE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 4;
		panelRemplirE.add(txtDateDebutE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 4;
		panelRemplirE.add(dateFinE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 4;
		panelRemplirE.add(txtDateFinE,gc1);
		
		gc1.gridx = 0;
		gc1.gridy = 5;
		panelRemplirE.add(dureeHebdoE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 5;
		panelRemplirE.add(txtDureeHebdoE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 5;
		panelRemplirE.add(emploiE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 5;
		panelRemplirE.add(choixEmploi,gc1);		
		/*
						CREATION DES BUTTONS

		 */			
		panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout());
		panelCenter.add(panelButtons);
		panelButtons.setOpaque(true);
		panelButtons.setBackground(new Color(250, 240, 230));
		
		
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconRechercher = new ImageIcon(new ImageIcon("images/Buttons/recherche.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnMod = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnReset = new monButton("Réinitialiser", iconReset);
			
		panelButtons.add(btnAjoute);
		panelButtons.add(btnMod);
		panelButtons.add(btnSupp);
		panelButtons.add(btnReset);
		
		listButtons.add(btnAjoute);
		listButtons.add(btnMod);
		listButtons.add(btnSupp);
		listButtons.add(btnReset);		
		/*
							CREATION BARRE DE RECHERCHE

		 */	
		JPanel panelRecherche = new JPanel();
		panelRecherche.setOpaque(true);
		panelRecherche.setBackground(new Color(250, 240, 230));
		
		btnRecherche = new JButton(iconRechercher);
		btnRecherche.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		recherche = new JTextField("Recherche par nom...", 20);
		recherche.setForeground(Color.GRAY);
		panelRecherche.add(recherche);
		panelRecherche.add(btnRecherche);
		panelCenter.add(panelRecherche);
		recherche.addFocusListener(null);		
		/*
							CREATION TABLEAU EMPLOYE

		 */	
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"ID","Nom", "Prénom","Date Naissance","Adresse","Type Contrat", "Date début"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		ate.afficheEmploye(model); //Afficher tous les employes
	
		pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		pane.getViewport().setBackground(new Color(250, 240, 230));
		
			/*
			============================================================
              						DIVERS
			============================================================
			 */
		Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
		panelCenter.setBorder(blackline);
	}
	
	public void addEvents() {
		
		/*
								RECHERCHER
		 */
		rechercherEmploye rechercheEmploye = new rechercherEmploye(model,recherche);
		btnRecherche.addActionListener(rechercheEmploye);

		textFieldRechercherEffect tre = new textFieldRechercherEffect(recherche);
		recherche.addFocusListener(tre);	
		/*
								REINITIATION

		 */
		reinitEmploye reinit = new reinitEmploye(txtNomE, txtPreNomE, txtDateNaissanceE,
					choixTypeContrat, txtDateDebutE, txtDateFinE,
					txtDureeHebdoE, choixEmploi, txtAdresseE, txtTelE, table, listButtons,model);

		btnReset.addActionListener(reinit);
		/*
								AJOUTER UN EMPLOYE

		 */	
		ajouterEmploye ajouteEmploye = new ajouterEmploye (txtNomE, txtPreNomE, txtDateNaissanceE,
												choixTypeContrat, txtDateDebutE, txtDateFinE,
												txtDureeHebdoE, choixEmploi, txtAdresseE, txtTelE, model, reinit);

		btnAjoute.addActionListener(ajouteEmploye);
		/*
					SELECTION UNE LIGNE DANS LE TABLEAU EMPLOYE

		 */	
		rowClicked rowClicked = new rowClicked (txtNomE, txtPreNomE, txtDateNaissanceE,
												choixTypeContrat, txtDateDebutE, txtDateFinE,
												txtDureeHebdoE, choixEmploi, txtAdresseE, txtTelE, table,model, listButtons);

		table.getSelectionModel().addListSelectionListener(rowClicked);
		/*
						TRAITEMENT LA DATE FIN CONTRAT DANS LE CAS CDI

		 */
		traiterDureeCDI traitDureeCDI = new traiterDureeCDI(choixTypeContrat,txtDateFinE);
		choixTypeContrat.addActionListener(traitDureeCDI);
		/*
									SUPPRESION UN EMPLOYE
		 */
		supprimerEmploye supprimerEmploye = new supprimerEmploye(table, model, reinit);
		btnSupp.addActionListener(supprimerEmploye);
		/*
							MISE A JOUR LES COORDONNEES UN EMPLOYE
		 */
		modifierEmploye modifierEmploye = new modifierEmploye(table, model, txtNomE, txtPreNomE, txtDateNaissanceE,
																choixTypeContrat, txtDateDebutE, txtDateFinE,
																txtDureeHebdoE, choixEmploi, txtAdresseE, txtTelE, reinit);

		btnMod.addActionListener(modifierEmploye);
	}
}
