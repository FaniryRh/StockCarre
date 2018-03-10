/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;



public class fichefourni extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    
    DefaultTableModel model_recherch_avance = new DefaultTableModel();
    DefaultComboBoxModel com_prod_rech = new DefaultComboBoxModel();
    
    public fichefourni() {
        initComponents();
        
        model_recherch_avance.addColumn("Designation");
        model_recherch_avance.addColumn("Numero");
        model_recherch_avance.addColumn("E-mail");
        model_recherch_avance.addColumn("Adresse");
        
    if (com_categori_rech_avance.getSelectedItem().equals("Tous")){   
        
        try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit order by designation");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            com_prod_rech_avance.setModel(com_prod_rech);
            
            afficher_rech();
            
        
    }else{String val = com_categori_rech_avance.getSelectedItem().toString();
    
    try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit where categori = '"+val+"'");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            com_prod_rech_avance.setModel(com_prod_rech);
            
            afficher_rech();
    
    }
        
        
    }

    private void afficher_rech() {
        
//        String value = (String) comb_categori_select_stock.getSelectedItem();

        try {
            String val = com_prod_rech_avance.getSelectedItem().toString();
            model_recherch_avance.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();

            ResultSet fr = stm.executeQuery("Select * from produit_fourni where designation = '"+val+"' ");

            while (fr.next()) {
                model_recherch_avance.addRow(new Object[]{fr.getString("fournisseur"), fr.getString("num"), fr.getString("mail"),
                    fr.getString("adrs")});
             }
           } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fourni_prod2.setModel(model_recherch_avance);
                            
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        com_prod_rech_avance = new javax.swing.JComboBox();
        com_categori_rech_avance = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_fourni_prod2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btn_affiche_prod_fourni = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recherche avancée");
        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(933, 482));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Designation du produit:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(307, 11, 133, 27);

        com_prod_rech_avance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_prod_rech_avanceItemStateChanged(evt);
            }
        });
        getContentPane().add(com_prod_rech_avance);
        com_prod_rech_avance.setBounds(458, 11, 148, 27);

        com_categori_rech_avance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Condiments", "Fromages", "Fruits", "Féculents", "Légumes", "Oeufs", "Poudres", "Viandes", "Crèmerie", "Poisson", "Epicerie", "Materiels de cuisine" }));
        com_categori_rech_avance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_categori_rech_avanceItemStateChanged(evt);
            }
        });
        getContentPane().add(com_categori_rech_avance);
        com_categori_rech_avance.setBounds(100, 11, 148, 27);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Categorie:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 11, 86, 27);

        tbl_fourni_prod2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_fourni_prod2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 86, 913, 385);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Liste des fournisseurs:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 57, 136, 23);

        btn_affiche_prod_fourni.setText("Afficher");
        btn_affiche_prod_fourni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_affiche_prod_fourniActionPerformed(evt);
            }
        });
        getContentPane().add(btn_affiche_prod_fourni);
        btn_affiche_prod_fourni.setBounds(776, 11, 147, 27);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 930, 480);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void com_prod_rech_avanceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_prod_rech_avanceItemStateChanged
       // btn_affiche_prod_fourni.set
//        if (com_prod_rech_avance.getSelectedItem().toString() == null){
//        
//        //model_recherch_avance.setRowCount(0);
//        //tbl_fourni_prod2.setModel(model_recherch_avance);
//        
//        }else{
//            
//        
//        try {
//            String val = com_prod_rech_avance.getSelectedItem().toString();
//            model_recherch_avance.setRowCount(0);
//            stm = conn.obtenirconnexion().createStatement();
//
//            ResultSet fr = stm.executeQuery("Select * from produit_fourni where designation = '"+val+"' ");
//
//            while (fr.next()) {
//                model_recherch_avance.addRow(new Object[]{fr.getString("fournisseur"), fr.getString("num"), fr.getString("mail"),
//                    fr.getString("adrs")});
//             }
//           } catch (SQLException e) {
//            System.err.println(e);
//        }
//        tbl_fourni_prod2.setModel(model_recherch_avance);
//        
//        }
        
        
        
    }//GEN-LAST:event_com_prod_rech_avanceItemStateChanged

    private void com_categori_rech_avanceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_categori_rech_avanceItemStateChanged
        if (com_categori_rech_avance.getSelectedItem().equals("Tous")){   
        
        try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit order by designation");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            com_prod_rech_avance.setModel(com_prod_rech);
            
            afficher_rech();
            
        
    }else 
    
    {String val = com_categori_rech_avance.getSelectedItem().toString();
    
    try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit where categori = '"+val+"'");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            com_prod_rech_avance.setModel(com_prod_rech);
            
            //afficher_rech();
    
    }
        
        
        
    }//GEN-LAST:event_com_categori_rech_avanceItemStateChanged

    private void btn_affiche_prod_fourniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_affiche_prod_fourniActionPerformed
        
        if (com_prod_rech_avance.getSelectedItem() == null){
        
        model_recherch_avance.setRowCount(0);
        tbl_fourni_prod2.setModel(model_recherch_avance);
        
        }else{
            
        
        try {
            String val = com_prod_rech_avance.getSelectedItem().toString();
            model_recherch_avance.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();

            ResultSet fr = stm.executeQuery("Select * from produit_fourni where designation = '"+val+"' ");

            while (fr.next()) {
                model_recherch_avance.addRow(new Object[]{fr.getString("fournisseur"), fr.getString("num"), fr.getString("mail"),
                    fr.getString("adrs")});
             }
           } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fourni_prod2.setModel(model_recherch_avance);
        
        }
        
        
    }//GEN-LAST:event_btn_affiche_prod_fourniActionPerformed

    
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
            java.util.logging.Logger.getLogger(fichefourni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fichefourni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fichefourni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fichefourni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fichefourni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_affiche_prod_fourni;
    private javax.swing.JComboBox com_categori_rech_avance;
    private javax.swing.JComboBox com_prod_rech_avance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_fourni_prod2;
    // End of variables declaration//GEN-END:variables
}
