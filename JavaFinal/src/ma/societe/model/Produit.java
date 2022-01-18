package ma.societe.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Produit {
    private String nomProd;
    private float prixProd;
    private Statement myStmt;

    Produit(String NomProd,float PrixProd,Statement myStmt0){
        nomProd=NomProd;
        prixProd=PrixProd;
        myStmt=myStmt0;
    }
    Produit(Statement myStmt0){
        myStmt=myStmt0;
    }

    public boolean ajouterProduit(){
        try{
            String sql = String.format("INSERT INTO produit (nom_prod,prix_prod) VALUES ('"+nomProd+"', '"+prixProd+"')");
            myStmt.executeUpdate(sql);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean modifierProduit(int id){
        String sql = String.format("UPDATE produit SET nom_prod='"+nomProd+"', prix_prod='"+prixProd+"'  Where id_prod='"+id+"' ");
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean supprimerProduit(int id){

        String sql = String.format("DELETE FROM produit Where id_prod='"+id+"'");
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
