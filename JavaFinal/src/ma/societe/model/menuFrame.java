package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuFrame extends JFrame {
    public JPanel layoutP ;
    public JButton consulterClientsButton;
    public JButton consulterProduitsButton;
    public JButton enregistrerTransactionButton;


    Conn conn =new Conn();


    menuFrame(){


        add(layoutP);
        setSize(600,500);
        setLocationRelativeTo(null);

        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        consulterClientsButton.setCursor(handcursor);
        consulterProduitsButton.setCursor(handcursor);
        enregistrerTransactionButton.setCursor(handcursor);



        consulterClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliFrame cliP=new cliFrame(conn.getMyStmt());
                cliP.setVisible(true);
                dispose();
            }
        });
        enregistrerTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transFrame transP=new transFrame(conn.getMyStmt());
                transP.setVisible(true);
                dispose();
            }
        });
        consulterProduitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proFrame pro=new proFrame(conn.getMyStmt());
                pro.setVisible(true);
                dispose();
            }
        });
    }



}
