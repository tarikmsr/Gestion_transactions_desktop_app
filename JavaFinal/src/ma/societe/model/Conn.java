package ma.societe.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Conn {
    private String dbName;
    protected Statement myStmt;


    Conn()
    {
        Connection con=null;
        try{

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            myStmt = myConn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }}

    public Statement getMyStmt() {
        return myStmt;
    }








}
