package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class editproFrame extends JFrame {
    private JPanel rootPanel;
    private JTextField nomP;
    private JTextField prixP;
    private JButton validerButton;
    private JButton retourButton;
    private  int idEdit;


    editproFrame(Statement myStmt,int id){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);
        idEdit=id;
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        validerButton.setCursor(handcursor);
        retourButton.setCursor(handcursor);

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float prix=Float.parseFloat(prixP.getText());
                Produit pro= new Produit(nomP.getText(),prix,myStmt);


                if(pro.modifierProduit(idEdit)){
                    JOptionPane.showMessageDialog(rootPanel,"Operation terminée");
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Erreur");
                }
            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proFrame prod=new proFrame(myStmt);
                prod.setVisible(true);
                dispose();
            }
        });
    }


}
