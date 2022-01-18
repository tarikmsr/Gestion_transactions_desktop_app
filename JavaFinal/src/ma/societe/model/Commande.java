package ma.societe.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Commande {
    private int numCmd;
    private Statement myStmt;
    private long currentId;

    Commande(int Numcmd,Statement myStmt0){
        numCmd=Numcmd;
        myStmt=myStmt0;
    }
    Commande(Statement myStmt0){
        myStmt=myStmt0;
    }

    public boolean ajouterCommande(int idC) {
        try {
            LocalDate localDate = LocalDate.now();
            String sql = String.format("INSERT INTO commande (num_cmd,date_cmd,id_cli) VALUES ('"+numCmd+"', '"+ localDate.toString()+"', '"+idC+"')");
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
    public boolean modifierCommande(int id_db,int idC) {
        String sql = String.format("UPDATE commande SET num_cmd='"+numCmd+"',id_cli='"+idC+"' WHERE id_cmd="+id_db+" ");
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean supprimerCommande(int id_cmd){
        String sql = String.format("DELETE FROM commande Where id_cmd=%s",id_cmd);
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
