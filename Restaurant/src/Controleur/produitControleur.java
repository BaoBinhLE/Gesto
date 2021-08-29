package Controleur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modele.produitModele;
import Controleur.produitControleur;

public class produitControleur {
	
	 private static Connection conn = null;

	//Obtenir la liste de produits
	    public static List<produitModele> GetListeProduit(produitModele modele) {
	        List<produitModele> listeProduit = new ArrayList();

	        try {
	            conn = ConnexionBDD.getConnect() ;	
	            Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery("SELECT * FROM produit");
	
	            while (rs.next()) {
	                modele = new produitModele(
	                        rs.getString("ID_Produit"),
	                        rs.getString("ID_Categorie"),
	                        rs.getString("Libelle"),
	                        rs.getFloat("Prix_unitaire"),
	                        rs.getString("Photo")
	                );
	                listeProduit.add(modele);
	            }
	        } catch (SQLException ex) {
	            System.out.println("GetListProduct-SQLException: " + ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("GetListProduct-Exception: " + ex.getMessage());
	        } finally {
	        	ConnexionBDD.getClose();
	        }
	        return listeProduit;
	    }
}
