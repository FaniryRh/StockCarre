/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.awt.HeadlessException;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
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
import javax.swing.Timer;

/**
 *
 * @author rango
 */
public class AjoutStock extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    public AjoutStock() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        txtAjoutProduitDesignation = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAjoutProduitqteact = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAjoutProduitqtealert = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAjoutProduitprixunit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboAjoutProduitUnite = new javax.swing.JComboBox();
        comboAjoutProduitcategori = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtAjoutProduitID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(467, 513));
        setMinimumSize(new java.awt.Dimension(467, 513));
        setPreferredSize(new java.awt.Dimension(467, 513));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("VertigoFLF", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nouveau produit");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 150, 40);

        txtAjoutProduitDesignation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(txtAjoutProduitDesignation);
        txtAjoutProduitDesignation.setBounds(190, 110, 217, 32);

        jLabel3.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Désignation:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 110, 129, 34);

        txtAjoutProduitqteact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(txtAjoutProduitqteact);
        txtAjoutProduitqteact.setBounds(190, 150, 217, 32);

        jLabel4.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Qté actuelle:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 150, 129, 34);

        txtAjoutProduitqtealert.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(txtAjoutProduitqtealert);
        txtAjoutProduitqtealert.setBounds(190, 190, 217, 32);

        jLabel5.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Qté d'alert:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 190, 129, 34);

        jLabel6.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Unité de mesure:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 230, 129, 34);

        txtAjoutProduitprixunit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(txtAjoutProduitprixunit);
        txtAjoutProduitprixunit.setBounds(190, 270, 217, 32);

        jLabel7.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Prix unitaire (Ar):");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 270, 129, 34);

        jLabel8.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Catégorie:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 310, 129, 34);

        comboAjoutProduitUnite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ariary", "Litre", "Sachet", "Bocal", "Boite", "Paquet", "Pièce", "Botte", "Kapoaka", "Brique", "Carton", "Barquette", "Kg", "Bouteille" }));
        comboAjoutProduitUnite.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(comboAjoutProduitUnite);
        comboAjoutProduitUnite.setBounds(190, 230, 217, 34);

        comboAjoutProduitcategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Condiments", "Fromages", "Fruits", "Féculents", "Légumes", "Oeufs", "Poudres", "Viandes", "Crèmerie", "Poisson", "Epicerie", "Materiels de cuisine" }));
        comboAjoutProduitcategori.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(comboAjoutProduitcategori);
        comboAjoutProduitcategori.setBounds(190, 310, 219, 34);

        jButton1.setBackground(java.awt.SystemColor.windowText);
        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 440, 110, 40);

        jLabel11.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ID:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(50, 70, 129, 34);

        txtAjoutProduitID.setText("Pxxx");
        txtAjoutProduitID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        getContentPane().add(txtAjoutProduitID);
        txtAjoutProduitID.setBounds(190, 70, 217, 32);

        jButton2.setBackground(java.awt.SystemColor.windowText);
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(280, 440, 120, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/t.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(-70, 10, 242, 40);

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 470, 520);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 470, 520);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        

        
        try{

            if (txtAjoutProduitID.getText().length() == 0 ||txtAjoutProduitDesignation.getText().length() == 0 || txtAjoutProduitprixunit.getText().length() == 0 || txtAjoutProduitqteact.getText().length() == 0 || txtAjoutProduitqtealert.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Veuillez completer tous les champs!");
            }

            else   {
                
                String id = txtAjoutProduitID.getText();
                    String designation = txtAjoutProduitDesignation.getText();
                    String qte_act = txtAjoutProduitqteact.getText();
                    String qte_alert = txtAjoutProduitqtealert.getText();
                    String unite = (String) comboAjoutProduitUnite.getSelectedItem();
                    Float prixunit = parseFloat(txtAjoutProduitprixunit.getText());
                    String categori = (String) comboAjoutProduitcategori.getSelectedItem();
                
                stm = conn.obtenirconnexion().createStatement();
                //String id = txtAjoutProduitID.getText();
                ResultSet fr = stm.executeQuery("Select id,designation from produit where id='"+id+"'");

                if (fr.next()) {

                    JOptionPane.showMessageDialog(null, id+" ("+fr.getString(2)+") existe déjà!");

                }else {
                    //prixunit = parseFloat(txtAjoutProduitprixunit.getText());
                    try {
                        
                     
                    stm = conn.obtenirconnexion().createStatement();
                    String requete = "insert into produit(id,designation,qte_actuel,qte_alert,unite,prixunit,categori)VALUES('" + id + "','" 
                + designation + "','" + qte_act + "','" + qte_alert + "','" + unite + "','" + prixunit + "','" + categori + "')";
                    stm.executeUpdate(requete);
                    JOptionPane.showMessageDialog(null, "Produit ajouté");
                    
                    
                    this.dispose();
                    //this.dispose();

                } catch (HeadlessException | SQLException ex)   {
                    
                }}

            } 
        }catch (HeadlessException | SQLException ex) {
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
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
            java.util.logging.Logger.getLogger(AjoutStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjoutStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjoutStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboAjoutProduitUnite;
    private javax.swing.JComboBox comboAjoutProduitcategori;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAjoutProduitDesignation;
    private javax.swing.JTextField txtAjoutProduitID;
    private javax.swing.JTextField txtAjoutProduitprixunit;
    private javax.swing.JTextField txtAjoutProduitqteact;
    private javax.swing.JTextField txtAjoutProduitqtealert;
    // End of variables declaration//GEN-END:variables
}