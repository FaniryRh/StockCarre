/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rango
 */
public class ModifFournisseur extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    String test;
    
    public String selected_f = MenuPrincipal.mainAp.selected_fournisseur;
    public ModifFournisseur() {
        initComponents();
        
        txtModiffournisseurID.setText(selected_f);
        txtModiffournisseurID.setEditable(false);
        
        try{
                stm = conn.obtenirconnexion().createStatement();
                //String des = txtModifProduitDesignation.getText();
                ResultSet fr = stm.executeQuery("Select * from fournisseur where id ='"+selected_f+"'");

                fr.next();

                        txtModifFournisseurDesignation.setText(fr.getString(3));
                        txtModifFournisseurTel.setText(fr.getString(4));
                        txtModifFournisseurMail.setText(fr.getString(5));
                        txtModifFournisseurAdress.setText(fr.getString(6));
                        

                //}else { }

                
            }catch (HeadlessException | SQLException ex) {
            }
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtModifFournisseurDesignation = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtModifFournisseurTel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtModifFournisseurMail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtModifFournisseurAdress = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAjoutProduitimage = new javax.swing.JLabel();
        btnModifFournisseurModif = new javax.swing.JButton();
        txtModiffournisseurID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(429, 446));
        setMinimumSize(new java.awt.Dimension(429, 446));
        setPreferredSize(new java.awt.Dimension(429, 446));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modification");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 170, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/t.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 10, 240, 50);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);
        jPanel1.add(txtModifFournisseurDesignation);
        txtModifFournisseurDesignation.setBounds(170, 110, 217, 32);

        jLabel3.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Désignation:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 110, 129, 34);

        jLabel4.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tel:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 150, 129, 34);
        jPanel1.add(txtModifFournisseurTel);
        txtModifFournisseurTel.setBounds(170, 150, 217, 32);

        jLabel5.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("E-mail:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 190, 129, 34);
        jPanel1.add(txtModifFournisseurMail);
        txtModifFournisseurMail.setBounds(170, 190, 217, 32);

        jLabel6.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Adresse:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 230, 129, 34);
        jPanel1.add(txtModifFournisseurAdress);
        txtModifFournisseurAdress.setBounds(170, 230, 217, 32);

        jLabel9.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Logo:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, 280, 130, 34);
        jPanel1.add(txtAjoutProduitimage);
        txtAjoutProduitimage.setBounds(170, 280, 220, 50);

        btnModifFournisseurModif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/valider.PNG"))); // NOI18N
        btnModifFournisseurModif.setText("Mettre à jour");
        btnModifFournisseurModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifFournisseurModifActionPerformed(evt);
            }
        });
        jPanel1.add(btnModifFournisseurModif);
        btnModifFournisseurModif.setBounds(210, 380, 140, 40);

        txtModiffournisseurID.setBackground(new java.awt.Color(204, 204, 204));
        txtModiffournisseurID.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtModiffournisseurID.setForeground(new java.awt.Color(153, 0, 0));
        jPanel1.add(txtModiffournisseurID);
        txtModiffournisseurID.setBounds(170, 70, 217, 32);

        jLabel11.setFont(new java.awt.Font("Raavi", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ID:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 70, 129, 34);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 430, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifFournisseurModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifFournisseurModifActionPerformed

        try {
            
            if (JOptionPane.showConfirmDialog(null, "modification sur le produit: "+selected_f , "modification",
                JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    stm.executeUpdate("UPDATE fournisseur SET designation='" + txtModifFournisseurDesignation.getText() + "',tel='" + txtModifFournisseurTel.getText()
                            + "',mail='" + txtModifFournisseurMail.getText() + "',adress='" + txtModifFournisseurAdress.getText() + "' WHERE id='" + selected_f+"'");
                } catch (SQLException ex) {
                    Logger.getLogger(ModifFournisseur.class.getName()).log(Level.SEVERE, null, ex);
                }
            JOptionPane.showMessageDialog(null, "Succès!");
        }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }

        this.dispose();

    }//GEN-LAST:event_btnModifFournisseurModifActionPerformed

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
            java.util.logging.Logger.getLogger(ModifFournisseur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifFournisseur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifFournisseur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifFournisseur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifFournisseur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModifFournisseurModif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtAjoutProduitimage;
    private javax.swing.JTextField txtModifFournisseurAdress;
    private javax.swing.JTextField txtModifFournisseurDesignation;
    private javax.swing.JTextField txtModifFournisseurMail;
    private javax.swing.JTextField txtModifFournisseurTel;
    private javax.swing.JTextField txtModiffournisseurID;
    // End of variables declaration//GEN-END:variables
}
