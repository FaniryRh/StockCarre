/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.*;
import java.awt.print.*;
import static java.lang.Float.parseFloat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
public class inventaire extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    
    DefaultTableModel model_stock = new DefaultTableModel();
    DefaultTableModel model_entre = new DefaultTableModel();
    DefaultTableModel model_sorti = new DefaultTableModel();
    
    public inventaire() {
        initComponents();
        
        model_stock.addColumn("Designation");
        model_stock.addColumn("Qté actuelle");
        model_stock.addColumn("Unité de mesure");
        
        model_entre.addColumn("Designation");
        model_entre.addColumn("Qté");
        model_entre.addColumn("Unité");
        model_entre.addColumn("Prix");
        
        
        model_sorti.addColumn("Designation");
        model_sorti.addColumn("Qté");
        model_sorti.addColumn("Unité");
        model_sorti.addColumn("Prix");
        
        
        afficher();
    }

    private void afficher() {
        
        
        try {
                model_stock.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        
        
        
        try {
                model_entre.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                
                String c = String.format("%1$tY-%1$tm-%1$td",jDateChooser1.getDate());
                String d = String.format("%1$tY-%1$tm-%1$td",jDateChooser2.getDate());
                ResultSet fr = stm.executeQuery("Select designation_produit,qte_recu,unite,prixTotal from entre where date between '"+c+"' and '"+d+"' order by designation_produit");
                    while (fr.next())   {
                        model_entre.addRow(new Object[]{fr.getString("designation_produit"),fr.getString("qte_recu"),
                        fr.getString("unite"),fr.getString("prixTotal")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_entre.setModel(model_entre);
        
        
        
        
        try {
                model_sorti.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                
                String c = String.format("%1$tY-%1$tm-%1$td",jDateChooser1.getDate());
                String d = String.format("%1$tY-%1$tm-%1$td",jDateChooser2.getDate());
                ResultSet fr = stm.executeQuery("Select designation,qte_ex,unite from sortie where date between '"+c+"' and '"+d+"' order by designation");
                    while (fr.next())   {
                        model_sorti.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_ex"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_sorti.setModel(model_sorti);
                            
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_entre = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_sorti = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_inventaire = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1229, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 204, 204))); // NOI18N

        tbl_entre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_entre);

        tbl_sorti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbl_sorti);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ENTREE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SORTIE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(447, 44, 772, 500);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/filtre.png"))); // NOI18N
        jButton2.setText("Filtrer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(1060, 11, 129, 27);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DU:");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AU:");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 51, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "En alert", "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Crèmerie", "Epicérie", "Fromages", "Fruits", "Féculents", "Materiels de cuisine", "Légumes", "Poissons", "Viandes", "Autres" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Catégorie:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tbl_inventaire.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_inventaire);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1230, 590);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText().equals("")){
        
            afficher();
        
        }else if (jComboBox1.getSelectedItem().toString().equals("Tous")){
        
        try {
                model_stock.setRowCount(0);
                //String val = jComboBox1.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit where designation like '%"+jTextField1.getText()+"%' order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        
        }else if(jComboBox1.getSelectedItem().toString().equals("En alert")){
        
        try {
                model_stock.setRowCount(0);
                //String val = jComboBox1.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit where designation like '%"+jTextField1.getText()+"%' && qte_actuel <= qte_alert order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        }else
        {
            
            try {
                model_stock.setRowCount(0);
                String val = jComboBox1.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit where categori='"+val+"' && designation like '%"+jTextField1.getText()+"%' order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        
        if (jComboBox1.getSelectedItem().toString().equals("Tous")){
            
            afficher();
        
        }else if (jComboBox1.getSelectedItem().toString().equals("En alert")){
        
        
        try {
                model_stock.setRowCount(0);
                String val = jComboBox1.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit where qte_actuel <= qte_alert order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        
        
        }else
        
        
        {
        
        
        try {
                model_stock.setRowCount(0);
                String val = jComboBox1.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,qte_actuel,unite from produit where categori ='"+val+"' order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"),
                        fr.getString("unite")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_inventaire.setModel(model_stock);
        
        
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        afficher();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventaire().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl_entre;
    private javax.swing.JTable tbl_inventaire;
    private javax.swing.JTable tbl_sorti;
    // End of variables declaration//GEN-END:variables
}
