package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class addcliFrame extends JFrame {
    private JTextField nomC;
    private JTextField adrC;
    private JButton validerButton;
    private JPanel rootPanel;
    private JButton retourButton;

    addcliFrame(Statement myStmt){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        validerButton.setCursor(handcursor);
        retourButton.setCursor(handcursor);




        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cli= new Client(nomC.getText(),adrC.getText(),myStmt);
                if(cli.ajouterClient()){
                    JOptionPane.showMessageDialog(rootPanel,"Operation terminée");
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Erreur");
                }

            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliFrame cli=new cliFrame(myStmt);
                cli.setVisible(true);
                dispose();
            }
        });
    }

}
