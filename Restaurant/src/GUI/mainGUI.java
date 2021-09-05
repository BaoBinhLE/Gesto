package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class mainGUI {
	
	moduleEmploye employe = new moduleEmploye();
	moduleCompte compte = new moduleCompte();
	moduleMenu menu = new moduleMenu();
	moduleCommande commande = new moduleCommande();
	client_GUI clientGUI = new client_GUI();
	moduleCA ca = new moduleCA();
	produit_GUI produit = new produit_GUI();
	JFrame fenetre;
	JTabbedPane tabs;
	JPanel tabEmploye, tabMenu, tabCommande, tabClient, tabCompte, tabCA;
	
	
	public mainGUI(int habilitation) {
		
		fenetre = new JFrame("Mon application");
		Container cprincipal = fenetre.getContentPane();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabs = new JTabbedPane(JTabbedPane.LEFT);
		tabs.setBackground(Color.DARK_GRAY);
	
	
		tabs.addTab("Employe",employe);
		tabs.addTab("Menu",produit);
		tabs.addTab("Commande",commande);
		tabs.addTab("Client",clientGUI);
		tabs.addTab("Chiffre d'affaire",ca);
		tabs.addTab("Compte",compte);
		
		
		cprincipal.add(tabs);

		fenetre.pack(); // calcul de la taille adéquate		
		fenetre.setVisible(true); // permet de faire apparaître l'interface graphique à l'écran		
	}

}
