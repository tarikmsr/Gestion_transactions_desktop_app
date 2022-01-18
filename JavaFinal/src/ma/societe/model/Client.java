package ma.societe.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Client   {
    private String nomCli;
    private String adrCli;
    private Statement myStmt;
    private long currentId;

    Client(String nom, String adr,Statement myStmt0) {
        nomCli = nom;
        adrCli = adr;
        myStmt=myStmt0;
    }

    Client(Statement myStmt0){
        myStmt=myStmt0;
    }

    public boolean ajouterClient() {
        try {
            String sql = String.format("insert into client(nom_client,adresse) values('%s','%s')",nomCli,adrCli);
            myStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          
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

    public boolean modifierClient(int id) {
        String sql = String.format("UPDATE client SET nom_client='%s',adresse='%s' Where id_cli=%s",this.nomCli,this.adrCli,id);
        try {
            myStmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerClient(int id){
        String sql = String.format("DELETE FROM client Where id_cli=%s",id);
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
