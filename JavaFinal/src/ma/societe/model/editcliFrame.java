package ma.societe.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class editcliFrame extends JFrame {
    private JTextField nomC;
    private JTextField adrC;
    private JButton validerButton;
    private JPanel rootPanel;
    private JButton retourButton;
    private  int idEdit;

    editcliFrame(Statement myStmt,int id){
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
                Client cli= new Client(nomC.getText(),adrC.getText(),myStmt);


                if(cli.modifierClient(idEdit)){
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
