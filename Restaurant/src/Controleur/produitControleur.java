package Controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	    /*public static List<produitModele> GetListeProduit(produitModele modele) {
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
	    }*/
	 
	    /*
	     * Obtenir la liste de produits
	     */
	    public ArrayList<produitModele> GetListeProduit() {
	        try {
	            String sql = "SELECT * FROM produit";
	            conn = ConnexionBDD.getConnect() ;	
	            Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            ArrayList<produitModele> listeProduit = new ArrayList<>();
	            while (rs.next()) {
	            	produitModele produit = new produitModele();
	            	//setter des propri�t�s de produit
	            	produit.setIdProduit(rs.getString(1));
	            	produit.setLibelleProduit(rs.getString(2));
	            	produit.setCategorie(rs.getString(3));
	            	produit.setPrixUnitaire(rs.getFloat(4));
	            	produit.setPhoto(rs.getString(5));
	            	//ajouter le produit dans la liste de produits
	            	listeProduit.add(produit);
	            }
	            return listeProduit;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("GetListeProduit-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("GetListeProduit-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }

	        return null;
	    }


	    /*
	     * obtenir les informations d'un produit
	     */
	    public produitModele getProduit(int idRecherche) {
	        try {
	        	conn = ConnexionBDD.getConnect() ;	
	        	Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery("SELECT * from produit WHERE ID_Produit = '"+idRecherche+"'");
	            if (rs.next()) {
	            	produitModele produit = new produitModele();
	            	//setter des propri�t�s de produit
	            	produit.setIdProduit(rs.getString(1));
	            	produit.setLibelleProduit(rs.getString(2));
	            	produit.setCategorie(rs.getString(3));
	            	produit.setPrixUnitaire(rs.getFloat(4));
	            	produit.setPhoto(rs.getString(5));
	                return produit;
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("GetProduit-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("GetProduit-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }

	        return null;
	    }
	    
	    /*
	     * Obtenir la liste de produits d'une categorie
	     */
	    public ArrayList<produitModele> getProduitSelonCategorie(String categorieRecherche) {
	        try {
	        	conn = ConnexionBDD.getConnect() ;	
	        	Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery("SELECT * from produit WHERE Libelle_categorie = '"+categorieRecherche+"'");
	            ArrayList<produitModele> listeProduit = new ArrayList<>();
	            while (rs.next()) {
	            	produitModele produit = new produitModele();
	            	//setter des propri�t�s de produit
	            	produit.setIdProduit(rs.getString(1));
	            	produit.setLibelleProduit(rs.getString(2));
	            	produit.setCategorie(rs.getString(3));
	            	produit.setPrixUnitaire(rs.getFloat(4));
	            	produit.setPhoto(rs.getString(5));
	            	//ajouter le produit dans la liste de produits
	            	listeProduit.add(produit);
	            }
	            return listeProduit;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("getProduitSelonCategorie-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("getProduitSelonCategorie-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }

	        return null;
	    }

	    /*
	     * obtenir le lien d'image de produit 
	     */
	    public String getImage(int produitRecherche) {
	        try {
	        	conn = ConnexionBDD.getConnect() ;	
	        	Statement s = conn.createStatement();
	            ResultSet rs = s.executeQuery("SELECT Photo from produit WHERE ID_Produit= '"+produitRecherche+"'");
	            if (rs.next()) {
	                return rs.getString("Photo");
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("GetImage-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("GetImage-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return "";
	    }

	   /*
	    * Ajoute d'un produit � la BDD
	    */
	    public boolean ajouteProduit(produitModele produitAjoute) {
	        try {
	        	conn = ConnexionBDD.getConnect() ;	
	            String sql = "INSERT INTO produit(Libelle, Libelle_categorie, Prix_nitaire,Photo) "
	                    + "VALUES (?, ?, ?, ?)";
	            PreparedStatement pre = conn.prepareStatement(sql);
	            pre.setString(1, produitAjoute.getLibelleProduit());
	            pre.setString(2, produitAjoute.getCategorie());
	            pre.setFloat(3, produitAjoute.getPrixUnitaire());
	            pre.setString(4, produitAjoute.getPhoto());

	            pre.execute();
	            return true;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("ajouteProduit-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("ajouteProduit-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return false;
	    }

	   /*
	    * Suppression d'un produit dans la BDD
	    */
	    public boolean suppressionProduit(int produitSupprime) {
	        try {
	        	conn = ConnexionBDD.getConnect() ;	
	        	Statement s = conn.createStatement();
	            s.execute("DELETE FROM produit WHERE ID_Produit= '"+produitSupprime+"'");
	            return true;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("suppressionProduit-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("suppressionProduit-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return false;
	    }

	    /*
	     * Mise � jour d'un produit
	     */
	    public boolean majProduit(produitModele produitMaj) {
	        try {
	            String sql = "UPDATE produit SET Libelle = ? , Libelle_categorie = ? , Prix_unitaire = ? , Photo = ? WHERE ID_Produit ="+produitMaj;
	            PreparedStatement pre = conn.prepareStatement(sql);
	            pre.setString(1, produitMaj.getLibelleProduit());
	            pre.setString(2, produitMaj.getCategorie());
	            pre.setFloat(3, produitMaj.getPrixUnitaire());
	            pre.setString(4, produitMaj.getPhoto());

	            pre.execute();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("majProduit-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("majProduit-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return false;
	    }
}
