package ma.societe.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class delFrame extends JFrame {
    private JTextField idC;
    private JButton validerButton;
    private JPanel rootPanel;
    private JButton retourButton;

    delFrame(Statement myStmt){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliFrame cli=new cliFrame(myStmt);
                cli.setVisible(true);
                dispose();
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cli= new Client(myStmt);
                int idInt=Integer.parseInt(idC.getText());
                if(cli.supprimerClient(idInt)){
                    JOptionPane.showMessageDialog(rootPanel,"Operation terminée");
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Erreur");
                }
            }
        });
    }
}
