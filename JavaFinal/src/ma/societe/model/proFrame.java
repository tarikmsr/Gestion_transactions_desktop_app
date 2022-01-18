package ma.societe.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class proFrame extends JFrame {
    private JTable listeProduits;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton Retourbutton;
    private Statement myStmt1;
    private JPanel rootPanel;
    private javax.swing.JScrollPane jScrollPane1;


    proFrame(Statement myStmt0) {


        add(rootPanel);
        setSize(600, 500);
        setLocationRelativeTo(null);
        String column[] = {"ID", "Nom Produit", "Prix unitaire"};
        String sql = "Select *from produit";
        myStmt1 = myStmt0;
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        ajouterButton.setCursor(handcursor);
        modifierButton.setCursor(handcursor);
        supprimerButton.setCursor(handcursor);
        Retourbutton.setCursor(handcursor);

        try {
            ResultSet myRs = myStmt0.executeQuery(sql);

            DefaultTableModel tableModel = new DefaultTableModel();

            //Retrieve meta data from ResultSet
            ResultSetMetaData metaData = myRs.getMetaData();

            int columnCount = metaData.getColumnCount();

            //Get all column names from meta data and add columns to table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
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
            JTableHeader head = new JTableHeader();


            listeProduits.setTableHeader(head);
            listeProduits.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        listeProduits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addproFrame addproduit = new addproFrame(myStmt1);
                addproduit.setVisible(true);

                dispose();
            }
        });
        Retourbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame menu = new menuFrame();
                menu.setVisible(true);
                dispose();
            }
        });
    }
    private void jTable1MouseClicked (java.awt.event.MouseEvent evt){
        // get the model from the jtable
        DefaultTableModel model = (DefaultTableModel) listeProduits.getModel();
        // get the selected row index
        int selectedRowIndex = listeProduits.getSelectedRow();
        int id = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());

        Produit produit = new Produit(myStmt1);
        ClientTransaction clientTrans = new ClientTransaction(myStmt1);
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int option = JOptionPane.showConfirmDialog(null, "Are you sure ", "Delete", JOptionPane.YES_NO_OPTION);
                // set the selected row data into jtextfields
                if (option == 0) {
                    String sql = "SELECT d.id_cmd FROM produit as c,ligne_cmd as d WHERE c.id_prod=d.id_prod ";
                    try{
                        ResultSet myRs=myStmt1.executeQuery(sql);

                        if(myRs.next()) {

                            myRs.first();

                            int tmp = myRs.getInt("id_cmd");
                            clientTrans.supprimerTransaction(tmp);

                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    if(produit.supprimerProduit(id)){
                        JOptionPane.showMessageDialog(rootPanel, "Supression terminée");

                    }else{
                        JOptionPane.showMessageDialog(rootPanel, "Erreur");
                    }

                    proFrame pro = new proFrame(myStmt1);
                    dispose();
                    pro.setVisible(true);
                }
            }
        });


        // testTextField.setText(model.getValueAt(selectedRowIndex, 0).toString());
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editproFrame editpro = new editproFrame(myStmt1, id);
                editpro.setVisible(true);
                dispose();
            }
        });


    }
}

