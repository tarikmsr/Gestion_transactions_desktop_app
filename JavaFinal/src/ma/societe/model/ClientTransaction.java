package ma.societe.model;

import java.sql.Statement;

public class ClientTransaction {
    Client client;
    Commande commande;
    Ligne_Commande ligne_commande;




    ClientTransaction(String nomC ,String adrC,int numCmd,int idP,int Qte,Statement myStmt ){
        client=new Client(nomC,adrC,myStmt);
        System.out.println((int)client.getCurrentId());
        commande=new Commande(numCmd,myStmt);
        ligne_commande=new Ligne_Commande(Qte,idP,myStmt);

    }
    ClientTransaction(int qte,int idP,Statement myStmt){
        ligne_commande=new Ligne_Commande(qte,idP,myStmt);
    }
    ClientTransaction(Statement myStmt){
        commande=new Commande(myStmt);
        ligne_commande=new Ligne_Commande(myStmt);

    }

    public boolean ajouterTransaction(){
        if(client.ajouterClient() && commande.ajouterCommande((int)client.getCurrentId()) &&ligne_commande.ajouterLigneCommande((int)commande.getCurrentId())) {
            return true;
        }else{
            return false;
        }




    }
    public boolean modifierTransaction(int id_cmd){
        if(ligne_commande.modifierLigneCommande( id_cmd)){
            return true;
        }else{
            return  false;
        }

    }
    public boolean supprimerTransaction(int id_cmd){
        if(ligne_commande.supprimerLigneCommande(id_cmd) &&commande.supprimerCommande(id_cmd)){
            return true;
        }else{
            return false;
        }



    }




}
