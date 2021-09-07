package BUS;

import java.util.ArrayList;

import DAO.commande_DAO;
import DAO.detailCommande_DAO;
import DTO.commande;
import DTO.detailCommande;

public class commande_BUS {
	
	private ArrayList<commande> listeCommande;
    private commande_DAO cmdDAO = new commande_DAO();
    private detailCommande_DAO detailDAO = new detailCommande_DAO();

    public ArrayList<commande> getListeCommandeEnCour() {
        listeCommande = cmdDAO.getListeCommandeEnCour();
        return listeCommande;
    }

    /*public void creationCommande() {
        commande cmd = new commande();
        cmd.setIDRH(idRH);
        cmd.setMaKH(maKH);
        cmd.setGhiChu(ghiChu);
        cmd.setTongTien(tongTien);

        cmdDAO.addCommande(cmd);
    }*/

    

    public ArrayList<detailCommande> showCommandeDeTable(String idTable) {
        int id = Integer.parseInt(idTable);
        ArrayList<detailCommande> listeDetail = new ArrayList<detailCommande>();
        listeDetail = detailDAO.getListdetailCommandeSelonIdCmd(cmdDAO.getCommandeEnCourParTable(id));
        return listeDetail;
    }

}
