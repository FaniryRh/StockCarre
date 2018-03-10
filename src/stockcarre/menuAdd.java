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
import javax.swing.JOptionPane;

/**
 *
 * @author rango
 */
public class menuAdd extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    public menuAdd() {
        initComponents();
        
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnMenuAjouter = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMenuID = new javax.swing.JTextField();
        txtMenuDes = new javax.swing.JTextField();
        comMenuCategori = new javax.swing.JComboBox();
        txtMenuPU = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(430, 448));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ajout menu");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 10, 130, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/t.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(-60, 10, 242, 40);

        btnMenuAjouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        btnMenuAjouter.setText("Ajouter");
        btnMenuAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAjouterActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuAjouter);
        btnMenuAjouter.setBounds(210, 360, 143, 33);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("P.U:");
        jPanel1.add(jLabel27);
        jLabel27.setBounds(70, 260, 120, 28);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Catégorie:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(68, 210, 120, 26);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Designation:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(68, 152, 120, 26);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(68, 101, 118, 26);

        txtMenuID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMenuIDActionPerformed(evt);
            }
        });
        jPanel1.add(txtMenuID);
        txtMenuID.setBounds(200, 102, 160, 26);
        jPanel1.add(txtMenuDes);
        txtMenuDes.setBounds(200, 150, 160, 30);

        comMenuCategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boissons chaudes", "Boissons fraiches", "Bières", "Vins - Apéritifs", "Whiskys - Alcools", "Liqueurs - Digestifs", "Champagne", "Cocktails", "Salades", "Entréés", "Tartares", "Woks", "Poissons", "Humbergers", "Pates", "Soupes", "Viandes" }));
        jPanel1.add(comMenuCategori);
        comMenuCategori.setBounds(200, 210, 160, 30);
        jPanel1.add(txtMenuPU);
        txtMenuPU.setBounds(200, 260, 160, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 430, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAjouterActionPerformed

        

        
        try{

            if (txtMenuID.getText().length() == 0 || txtMenuDes.getText().length() == 0 ||
                txtMenuPU.getText().length() == 0 ) {
                
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
                
            }

            else   {String id = txtMenuID.getText();
                    String designation = txtMenuDes.getText();
                    String categori = comMenuCategori.getSelectedItem().toString();
                    String pu = txtMenuPU.getText();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr1;
                fr1 = stm.executeQuery("Select * from menu where id='"+id+"'");

                if (fr1.next()) {

                    JOptionPane.showMessageDialog(null, id+" existe déjà!");

                }else {

                    try {
                        String requete = "insert into menu(id,designation,categorie,pu)VALUES('"
                        + id + "','" + designation + "','" + categori
                        + "','" + pu + "')";
                        stm.executeUpdate(requete);
                        JOptionPane.showMessageDialog(null, "Nouveau menu ajouté");
                       
                        this.setVisible(false);
                        
                    } catch (HeadlessException | SQLException ex)   {
                        JOptionPane.showMessageDialog(null, "erreur 1");
                    }this.dispose();
                
                }

                }//afficher_fac_mouv();
            }catch (HeadlessException | SQLException ex) {

                JOptionPane.showMessageDialog(null, "arreur 2");

            }
    }//GEN-LAST:event_btnMenuAjouterActionPerformed

    private void txtMenuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMenuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMenuIDActionPerformed

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
            java.util.logging.Logger.getLogger(menuAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuAjouter;
    private javax.swing.JComboBox comMenuCategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMenuDes;
    private javax.swing.JTextField txtMenuID;
    private javax.swing.JTextField txtMenuPU;
    // End of variables declaration//GEN-END:variables
}
