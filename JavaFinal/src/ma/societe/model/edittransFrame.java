package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class edittransFrame extends JFrame {
    private JPanel rootPanel;
    private JTextField idP;
    private JTextField qteC;
    private JButton validerButton;
    private JButton retourButton;
    private  int idEdit;


    edittransFrame(Statement myStmt,int id){
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

                int idProd=Integer.parseInt(idP.getText());
                int qteCmd=Integer.parseInt(qteC.getText());
                String sql = "Select id_prod From produit  ";
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

                ClientTransaction clientTransaction= new ClientTransaction(qteCmd,idProd,myStmt);

                if (k == 1) {
                    if (clientTransaction.modifierTransaction(idEdit)) {
                        JOptionPane.showMessageDialog(rootPanel, "Operation terminée");
                    } else {
                        JOptionPane.showMessageDialog(rootPanel, "Erreur");
                    }

                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Produit n'existe pas!");

                }


            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transFrame tran=new transFrame(myStmt);
                tran.setVisible(true);
                dispose();
            }
        });
    }


}
