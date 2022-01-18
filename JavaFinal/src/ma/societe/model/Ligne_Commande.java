package ma.societe.model;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ligne_Commande {
    private int qteCmd;
    private int idProd;
    private Statement myStmt;
    private long currentId;

    Ligne_Commande(int QteCmd,int idprod, Statement myStmt0){
        qteCmd=QteCmd;
        idProd=idprod;
        myStmt=myStmt0;
    }
    Ligne_Commande(Statement myStmt0){

        myStmt=myStmt0;
    }

    public boolean ajouterLigneCommande(int idCmd) {
        try {
            LocalDate localDate = LocalDate.now();
            String sql = String.format("INSERT INTO ligne_cmd  (qte_cmd,id_cmd,id_prod) VALUES ('"+qteCmd+"', '"+ idCmd+"', '"+idProd+"')");
            myStmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setCurrentId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean modifierLigneCommande(int idCmd) {
        String sql = String.format("UPDATE ligne_cmd SET qte_cmd='"+qteCmd+"',id_prod='"+idProd+"' WHERE id_cmd="+idCmd+" ");
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean supprimerLigneCommande(int idCmd){
        String sql = String.format("DELETE FROM ligne_cmd Where id_cmd=%s",idCmd);
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setCurrentId(long currentId) {
        this.currentId = currentId;
    }

    public long getCurrentId() {
        return currentId;
    }
}
