package ma.societe.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class cliFrame extends JFrame  {
    private JTable listeClients;
    private JPanel rootPanel;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton retourButton;
    private Statement myStmt1;


    cliFrame(Statement myStmt0){
        add(rootPanel);
        setSize(600,500);
        setLocationRelativeTo(null);
        String column[]={"ID","Nom Complet","Adresse"};
        String sql = "Select *from client";
        myStmt1=myStmt0;
        Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
        ajouterButton.setCursor(handcursor);
        modifierButton.setCursor(handcursor);
        supprimerButton.setCursor(handcursor);
        retourButton.setCursor(handcursor);

        try {

            ResultSet myRs=myStmt1.executeQuery(sql);

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addcliFrame addCli=new addcliFrame(myStmt1);
                addCli.setVisible(true);
                dispose();
            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame menu=new menuFrame();
                menu.setVisible(true);
                dispose();
            }
        });




    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt)  {
        // get the model from the jtable
        DefaultTableModel model = (DefaultTableModel)listeClients.getModel();
        // get the selected row index
        int selectedRowIndex = listeClients.getSelectedRow();
        int id=Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());


        Client client = new Client(myStmt1);
        ClientTransaction clientTrans = new ClientTransaction(myStmt1);
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int option=JOptionPane.showConfirmDialog(null,"Are you sure ?","Delete",JOptionPane.YES_NO_OPTION);
                // set the selected row data into jtextfields
                if (option==0){
                    String sql = "SELECT d.id_cmd FROM client as c,commande as d WHERE c.id_cli=d.id_cli ";
                    try{
                        ResultSet myRs=myStmt1.executeQuery(sql);

                        if(myRs.next()){

                            myRs.first();

                            int tmp=myRs.getInt("id_cmd");
                            if(clientTrans.supprimerTransaction(tmp)){
                                JOptionPane.showMessageDialog(rootPanel, "Supression terminée");

                            }else{
                                JOptionPane.showMessageDialog(rootPanel, "Erreur");
                            }

                        }



                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    client.supprimerClient(id);
                    cliFrame cli=new cliFrame(myStmt1);
                    dispose();
                    cli.setVisible(true);
                }
            }
        });
        // testTextField.setText(model.getValueAt(selectedRowIndex, 0).toString());
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editcliFrame editCli=new editcliFrame(myStmt1,id);
                editCli.setVisible(true);
                dispose();
            }
        });

    }
}
