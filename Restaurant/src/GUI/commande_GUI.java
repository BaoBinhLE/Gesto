package GUI;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BUS.table_BUS;
import Custom.monTableau;
import Custom.transparentPanel;
import DTO.table;

public class commande_GUI extends JPanel{
	
	public commande_GUI() {
		changeLNF("FlatLaf");
		addControls();
        addEvents();
	}
	
	private table_BUS tableBUS = new table_BUS();
	final Color colorPanel = new Color(247, 247, 247);
	JLabel titre;
	JButton btnAjoute, btnSupp, btnPaie;
    monTableau tabDetail;
    DefaultTableModel modelTabDetail;
	
	private void addControls() {
		Font font = new Font("Tahoma", Font.PLAIN, 20);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pnTitle = new transparentPanel();
        titre = new JLabel("<html><h1>ORDER</h1></html>");
        pnTitle.add(titre);
        this.add(pnTitle);
        
        JPanel panelRemplir= new transparentPanel();
        panelRemplir.setLayout(new BoxLayout(panelRemplir, BoxLayout.X_AXIS));
        
        //================PANEL TABLE=========
        ArrayList<table> tableList = tableBUS.getlisteTable();
        JPanel panelTable = new transparentPanel();
        panelTable.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton[] button = new JButton[tableList.size()];
        for(int i=0;i<tableList.size();i++)
        {
        	String status = null ;
            Color c;
            button[i] = new JButton() ;
            button[i].setPreferredSize(new Dimension(120,80));
            switch (tableList.get(i).getStatut())
            {
                case 1:
                	c =  new Color(51,204,255); //VERY_LIGHT_BLUE
                	status = " disponible";
                    break;
                default:
                	c = new Color(255,102,102); // LIGHT_PINK
                    break;
            }
            button[i].setText("<html>"+(String)tableList.get(i).getLibelle()+"<br>"+status+ "</html>");
            button[i].setBackground(c);
            panelTable.add(button[i]);
        }
        JScrollPane scrtabTable = new JScrollPane(panelTable);
        panelRemplir.add(scrtabTable);
        
        /*
		============================================================
           					PANEL INFO 
		============================================================
		 */
        
        JPanel panelDetail= new transparentPanel();
        panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS));
        
      //================PANEL TABLE=========
        JPanel pnAffiche = new transparentPanel();
        pnAffiche.setLayout(new BoxLayout(pnAffiche, BoxLayout.Y_AXIS));
        
        modelTabDetail = new DefaultTableModel();
        modelTabDetail.addColumn("ID ");
        modelTabDetail.addColumn("Libellé");
        modelTabDetail.addColumn("Quantité");
        modelTabDetail.addColumn("Prix");
        tabDetail = new monTableau();
        tabDetail.setModel(modelTabDetail);
        
        JScrollPane scrtabDetail = new JScrollPane(tabDetail);


      //================PANEL BUTTON=========
        JPanel pnBtn = new transparentPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.Y_AXIS));
        
        panelDetail.add(scrtabDetail);
        panelDetail.add(pnBtn);
        panelRemplir.add(panelDetail);
        this.add(panelRemplir);
	}
	
	private void addEvents() {
		
	}
	
	private void showCommande(int idCmd) {
		
	}
}
