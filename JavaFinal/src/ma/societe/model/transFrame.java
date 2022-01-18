package ma.societe.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;

public class transFrame extends JFrame {
    private JPanel rootPanel;
    private JButton retourButton;
    private JTable listeClients;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private Statement myStmt1;



    transFrame(Statement myStmt){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);
        myStmt1=myStmt;
        String sql = "SELECT c.nom_client,d.id_cmd,prix_prod*qte_cmd FROM client as c,commande as d,ligne_cmd as ld,produit as p WHERE c.id_cli=d.id_cli AND ld.id_cmd=d.id_cmd AND ld.id_prod=p.id_prod";
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        ajouterButton.setCursor(handcursor);
        modifierButton.setCursor(handcursor);
        supprimerButton.setCursor(handcursor);
        retourButton.setCursor(handcursor);
        try {

            ResultSet myRs=myStmt.executeQuery(sql);

            DefaultTableModel tableModel = new DefaultTableModel();

            //Retrieve meta data from ResultSet
            ResultSetMetaData metaData = myRs.getMetaData();

            int columnCount = metaData.getColumnCount();

            //Get all column names from meta data and add columns to table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                tableModel.addColumn(metaData.getColumnLabel(columnIndex));
            }

            //Create array of Objects with size of column count from meta data
            Object[] row = new Object[columnCount];


            while (myRs.next()) {
                //Get object from column with specific index of result set to array of objects
                for (int i = 0; i < columnCount; i++) {
                    row[i] = myRs.getObject(i + 1);
                }
                //Now add row to table model with that array of objects as an argument
                tableModel.addRow(row);
            }

            listeClients.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        listeClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addtransFrame addTrans=new addtransFrame(myStmt1);
                addTrans.setVisible(true);
                dispose();
            }
        });


        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame cli=new menuFrame();
                cli.setVisible(true);
                dispose();
            }

        });

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt)  {
        // get the model from the jtable
        DefaultTableModel model = (DefaultTableModel)listeClients.getModel();
        // get the selected row index
        int selectedRowIndex = listeClients.getSelectedRow();
        int numCmd=Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString());

        ClientTransaction clientTrans = new ClientTransaction(myStmt1);
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int option=JOptionPane.showConfirmDialog(null,"Are you sure ","Delete",JOptionPane.YES_NO_OPTION);
                // set the selected row data into jtextfields
                if (option==0){
                    if(clientTrans.supprimerTransaction(numCmd)){
                        JOptionPane.showMessageDialog(rootPanel, "Supression terminée");

                    }else{
                        JOptionPane.showMessageDialog(rootPanel, "Erreur");

                    }
                    transFrame trans=new transFrame(myStmt1);
                    dispose();
                    trans.setVisible(true);}
            }
        });
        // testTextField.setText(model.getValueAt(selectedRowIndex, 0).toString());
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edittransFrame editTrans=new edittransFrame(myStmt1,numCmd);
                editTrans.setVisible(true);
                dispose();
            }
        });

    }
}
