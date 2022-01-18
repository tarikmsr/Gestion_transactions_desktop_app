package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addtransFrame extends JFrame {
    private JPanel rootPanel;
    private JButton retourButton;
    private JTextField nomC;
    private JTextField adrC;
    private JTextField idP;
    private JTextField qte;
    private JTextField numCmd;
    private JButton validerButton;

    addtransFrame(Statement myStmt){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        validerButton.setCursor(handcursor);
        retourButton.setCursor(handcursor);



        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transFrame trans=new transFrame(myStmt);
                trans.setVisible(true);
                dispose();
            }

        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Select id_prod From produit  ";
                int idProd = Integer.parseInt(idP.getText());
                int k=0;
                try {
                    ResultSet myRs = myStmt.executeQuery(sql);
                    while (myRs.next()) {
                        if (myRs.getInt("id_prod") == idProd) {
                            k = 1;
                        }
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                ClientTransaction clientTransaction = new ClientTransaction(nomC.getText(), adrC.getText(), Integer.parseInt(numCmd.getText()), Integer.parseInt(idP.getText()), Integer.parseInt(qte.getText()), myStmt);
                if (k == 1) {
                    if(clientTransaction.ajouterTransaction()){
                        JOptionPane.showMessageDialog(rootPanel, "Operation terminée");

                    }else {
                        JOptionPane.showMessageDialog(rootPanel, "Erreur");

                    }
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Produit n'existe pas!");
                }
            }
        });

    }
}
