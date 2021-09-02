package GUI;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

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
import Modele.produitModele;
import static Main.main.changeLNF;

public class produit_GUI extends JPanel{
	
	public produit_GUI() {
		changeLNF("Windows");
		addComponents();
		
	}
	
	produitModele modele;
	
	Font f = new Font("TimesRoman", Font.BOLD, 18);
	
	ArrayList<JButton> listButtons = new ArrayList<JButton>();
	
	JPanel panelTop, panelCenter;
	
	JPanel panelRemplir, panelButtons, panelRecherche;
	
	JLabel idProduit,libelleProduit,categorie, prixUnitaire, titre, photo;
	JTextField txtIdProduit, txtLibelleProduit, txtPrixUnitaire,txtPhoto;
	JComboBox<String> choixCategorie = new JComboBox<String>();	

	
	monButton btnAjoute, btnMod, btnSupp, btnReset;	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset;
	
	JButton btnRecherche;
	JTextField recherche;
	
	monTableau table;
	DefaultTableModel model;
	JScrollPane pane;

	
	
	public void AfficheTableauProduit() {
        produitControleur product = new produitControleur();
        List<produitModele> listeProduit = produitControleur.GetListeProduit(getProduitModele());
        
        table= new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"ID produit","Libellé","Catégorie","Prix unitaire", "Photo"};
		model.setColumnIdentifiers(columns);

	
        
        //String[] colHeader = new String[]{"MÃ£ sáº£n pháº©m", "TÃªn sáº£n pháº©m", "NhÃ  cung cáº¥p", "Loáº¡i", "Ä�Æ¡n vá»‹", "NhÃ  sáº£n xuáº¥t", "Háº¡n sá»­ dá»¥ng", "GiÃ¡", "Sá»‘ lÆ°á»£ng"};
        //modelTable = new DefaultTableModel(colHeader, 0);
        Object[] rowData = new Object[5];
        for (int i = 0; i < listeProduit.size(); i++) {
            rowData[0] = listeProduit.get(i).getIdProduit();
            rowData[1] = listeProduit.get(i).getLibelleProduit();
            rowData[2] = listeProduit.get(i).getCategorie();
            rowData[3] = listeProduit.get(i).getPrixUnitaire();
            rowData[4] = listeProduit.get(i).getPhoto();
            model.addRow(rowData);
        }
        table.setModel(model);
    }
	
	public produitModele getProduitModele() {
        modele = new produitModele(
                txtIdProduit.getText(),//id produit
                txtLibelleProduit.getText(),//libellé de produit
                choixCategorie.getSelectedItem().toString(),//catégorie   
                Float.parseFloat(txtPrixUnitaire.getText()),//prix unitaire
                txtPhoto.getText());//image
        return modele;
    }

	
	public void addComponents() {
		produitModele modele;
		
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
		
		titre = new JLabel("Gestion de produit");
		titre.setFont(f);
		idProduit = new JLabel("ID produit");
		libelleProduit = new JLabel("Libellé");
		categorie = new JLabel("Catégorie");
		prixUnitaire = new JLabel("Prix unitaire");
		photo = new JLabel("Photo");
		
		txtIdProduit = new JTextField("", 15);
		txtLibelleProduit = new JTextField("", 15);
		txtPrixUnitaire = new JTextField("", 15);

		
		gc.gridx = 2;
		gc.gridy = 0;
		panelRemplir.add(titre, gc);
	
		gc.gridx = 1;
		gc.gridy = 1;
		panelRemplir.add(idProduit,gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		panelRemplir.add(txtIdProduit, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		panelRemplir.add(libelleProduit, gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		panelRemplir.add(txtLibelleProduit, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		panelRemplir.add(categorie, gc);
		
		gc.gridx = 2;
		gc.gridy = 3;
		panelRemplir.add(choixCategorie, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		panelRemplir.add(prixUnitaire, gc);
		
		gc.gridx = 2;
		gc.gridy = 4;
		panelRemplir.add(txtPrixUnitaire, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		panelRemplir.add(photo, gc);
		
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
		btnReset = new monButton("RÃ©initialiser", iconReset);
		
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
		Object[] columns = {"ID Produit","Libellé","Catégorie","Prix Unitaire"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
	
	
		pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		
	}
}

