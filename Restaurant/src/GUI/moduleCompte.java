package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Custom.monButton;
import Custom.monTableau;
import eventCompte.AjouterCompte;
import eventCompte.ReinitCompte;
import eventCompte.RowClicked;
import eventCompte.afficherTousComptes;
import eventEmploye.ajouterEmploye;
import eventEmploye.modifierEmploye;
import eventEmploye.rechercherEmploye;
import eventEmploye.reinitEmploye;
import eventEmploye.rowClicked;
import eventEmploye.supprimerEmploye;
import eventEmploye.textFieldRechercherEffect;
import eventEmploye.traiterDureeCDI;

public class moduleCompte extends JPanel {	
	
	Font f = new Font("TimesRoman", Font.BOLD, 18);
	
	ArrayList<JButton> listButtons = new ArrayList<JButton>();
	
	JPanel panelTop, panelCenter;
	
	JPanel panelRemplir, panelButtons, panelRecherche;
	
	JLabel idrh, identifiant, habilitation, titre, nom, prenom, emploi;
	JTextField txtIdentifiant, txtNom, txtPrenom, txtEmploi;
	JComboBox<String> choixIDRH = new JComboBox<String>();	
	JComboBox<String> choixHabilitation = new JComboBox<String>();
	
	monButton btnAjoute, btnMod, btnSupp, btnReset;	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset;
	
	JButton btnRecherche;
	JTextField recherche;
	
	monTableau table;
	DefaultTableModel model;
	JScrollPane pane;

	afficherTousComptes atc = new afficherTousComptes();
	
	public moduleCompte() {
		
		addComponents();
		addEvents();
	}
	
	public void addComponents() {
	
		panelTop = new JPanel();
		panelCenter = new JPanel();
		this.add(panelTop);
		this.add(panelCenter);
		
		panelRemplir = new JPanel();
		panelButtons = new JPanel();
		panelRecherche = new JPanel();
		
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelRemplir);
		panelCenter.add(panelButtons);
		panelCenter.add(panelRecherche);
		
		panelRemplir.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		
		
		/*
		============================================================
           		ARRANGEMENT DES COMPOSANTS DANS LE PANEL REMPLIR
		============================================================
		 */
		
		titre = new JLabel("Gestion de compte");
		titre.setFont(f);
		idrh = new JLabel("IDRH");
		identifiant = new JLabel("Identifiant");
		habilitation = new JLabel("Habilitation");
		nom = new JLabel("Nom");
		prenom = new JLabel("Prenom");
		emploi = new JLabel("Emploi");
		
		txtIdentifiant = new JTextField("", 15);
		txtNom = new JTextField("", 15);
		txtPrenom = new JTextField("", 15);
		txtEmploi = new JTextField("", 15);
		
		gc.gridx = 2;
		gc.gridy = 0;
		panelRemplir.add(titre, gc);
	
		gc.gridx = 1;
		gc.gridy = 1;
		panelRemplir.add(idrh, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		panelRemplir.add(choixIDRH, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		panelRemplir.add(habilitation, gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		panelRemplir.add(choixHabilitation, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		panelRemplir.add(nom, gc);
		
		gc.gridx = 2;
		gc.gridy = 3;
		panelRemplir.add(txtNom, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		panelRemplir.add(prenom, gc);
		
		gc.gridx = 2;
		gc.gridy = 4;
		panelRemplir.add(txtPrenom, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		panelRemplir.add(identifiant, gc);
		
		gc.gridx = 2;
		gc.gridy = 5;
		panelRemplir.add(txtIdentifiant, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		panelRemplir.add(emploi, gc);
		
		gc.gridx = 2;
		gc.gridy = 6;
		panelRemplir.add(txtEmploi, gc);	
		/*
		============================================================
            				CREATION DES BUTTONS
		============================================================
		 */
		
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconRechercher = new ImageIcon(new ImageIcon("images/Buttons/recherche.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnMod = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnReset = new monButton("Réinitialiser", iconReset);
		
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnAjoute);
		panelButtons.add(btnMod);
		panelButtons.add(btnSupp);
		panelButtons.add(btnReset);
		
		listButtons.add(btnAjoute);
		listButtons.add(btnMod);
		listButtons.add(btnSupp);
		listButtons.add(btnReset);
		
		btnRecherche = new JButton(iconRechercher);
		recherche = new JTextField("Recherche par identifiant...", 20);
		recherche.setForeground(Color.GRAY);
		panelRecherche.add(recherche);
		panelRecherche.add(btnRecherche);
		recherche.addFocusListener(null);		
		/*
		============================================================
           					CREATION DE TABLE
		============================================================
		 */
		
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"IDRH","Identifiant","Nom","Prenom", "Habilitation"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		atc.afficheComptes(model);
	
		pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		
		/*
		============================================================
            		INITIATION DES COMBOBOX
		============================================================
		 */
		choixIDRH.addItem("");
		listIDRH();
		choixHabilitation.addItem("");
		choixHabilitation.addItem("1");
		choixHabilitation.addItem("2");	
	}	
		/*
		============================================================
        			INITIATION LES CHOIX DE IDRH
		============================================================
		 */			
	public void listIDRH() {		

		PreparedStatement select;
		String requete;		
	
		try {
			/*
			 * Connexion à la bdd
			 */		
			Class.forName("com.mysql.cj.jdbc.Driver");		
			String urlDB = "jdbc:mysql://root:@localhost:3306/gestion_restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection cnx = DriverManager.getConnection(urlDB);			
			/*
			 * Requete select * et exécution
			 */		
			requete = "SELECT * FROM employe";
			select = cnx.prepareStatement(requete);	
			ResultSet rs = select.executeQuery();			
			/*
			 * Boucle d'affichage
			 */
			while (rs.next()) {
				String idrh = rs.getString("IDRH");
				choixIDRH.addItem(idrh);							
			}		
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}			
	}
	
	private void addEvents() {
		
		/*
								REINITIATION

		 */
		ReinitCompte reinit = new ReinitCompte(choixIDRH, txtIdentifiant, choixHabilitation, listButtons, txtNom, txtPrenom, txtEmploi, model);

		btnReset.addActionListener(reinit);
		/*
								AJOUTER UN EMPLOYE

		 */	
		AjouterCompte ajouteCompte = new AjouterCompte (choixIDRH, choixHabilitation, model, reinit);

		btnAjoute.addActionListener(ajouteCompte);
		
		/*
		SELECTION UNE LIGNE DANS LE TABLEAU EMPLOYE

		 */	
		RowClicked rowClicked = new RowClicked (choixIDRH, choixHabilitation, txtIdentifiant, txtNom, txtPrenom, txtEmploi, table, model, listButtons);

		table.getSelectionModel().addListSelectionListener(rowClicked);
		
	}
}
