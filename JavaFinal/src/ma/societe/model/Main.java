package ma.societe.model;


import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) throws Exception {

        menuFrame myWindow= new menuFrame();

        myWindow.setVisible(true);

        createTables();
    }


    public static void createTables() throws Exception{
        try{
            Conn conn =new Conn();

            String sql = String.format("CREATE TABLE IF NOT EXISTS `client` (`id_cli` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,`nom_client` varchar(30) NOT NULL,`adresse` varchar(100) NOT NULL) ");
            conn.myStmt.executeUpdate(sql);

            String sql2 = String.format("CREATE TABLE IF NOT EXISTS `commande` ( `id_cmd` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, `date_cmd` date NOT NULL,`id_cli` int(11) NOT NULL, `num_cmd` int(11) NOT NULL) ");
            conn.myStmt.executeUpdate(sql2);
            String sql3 = String.format("CREATE TABLE IF NOT EXISTS `produit` ( `id_prod` int(6) NOT NULL PRIMARY KEY AUTO_INCREMENT , `nom_prod` varchar(30) NOT NULL, `prix_prod` float NOT NULL)");
            conn.myStmt.executeUpdate(sql3);

            String sql4 = String.format("CREATE TABLE IF NOT EXISTS `ligne_cmd` (`id_ligne` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,`qte_cmd` int(11) NOT NULL, `id_cmd` int(11) NOT NULL, `id_prod` int(11) NOT NULL)");
            conn.myStmt.executeUpdate(sql4);

            String sql5 = String.format("ALTER TABLE `commande` ADD CONSTRAINT `FK3` FOREIGN KEY (`id_cli`) REFERENCES `client` (`id_cli`)");
            conn.myStmt.executeUpdate(sql5);

            String sql6 = String.format("ALTER TABLE `commande` ADD KEY `FK3` (`id_cli`)");
            conn.myStmt.executeUpdate(sql6);
            
            String sql7 = String.format("ALTER TABLE `ligne_cmd`ADD KEY `FK5` (`id_cmd`),ADD KEY `FK7` (`id_prod`)");
            conn.myStmt.executeUpdate(sql7);

            String sql8 = String.format("ALTER TABLE `ligne_cmd` ADD CONSTRAINT `FK4` FOREIGN KEY (`id_prod`) REFERENCES `produit` (`id_prod`),ADD CONSTRAINT `FK5` FOREIGN KEY (`id_cmd`) REFERENCES `commande` (`id_cmd`)");
            conn.myStmt.executeUpdate(sql8);

        }catch(Exception e){System.out.println(e);}
    }








}
