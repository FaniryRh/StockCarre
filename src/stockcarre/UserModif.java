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
public class UserModif extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    String id_user = MenuPrincipal.user.selected_user;
    
    
    public UserModif() {
        initComponents();
        jTextField1.setText(id_user);
        jTextField1.setEditable(false);
       
        try{
                stm = conn.obtenirconnexion().createStatement();
                //String des = txtModifProduitDesignation.getText();
                ResultSet fr = stm.executeQuery("Select * from user where id ='"+id_user+"'");

                fr.next();

                        jTextField3.setText(fr.getString(3));
                        comUserModifType.setSelectedItem(fr.getString(4));

  
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

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comUserModifType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(399, 331));
        setMinimumSize(new java.awt.Dimension(399, 331));
        setPreferredSize(new java.awt.Dimension(399, 331));
        getContentPane().setLayout(null);

        jLabel5.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Modification");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 10, 140, 40);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(150, 70, 168, 35);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 70, 102, 35);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mot de passe:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 110, 102, 35);
        jPanel1.add(jTextField3);
        jTextField3.setBounds(150, 110, 168, 35);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Type:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 150, 102, 35);

        comUserModifType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrateur", "Caissier", "Cuisinier", "Comptable" }));
        jPanel1.add(comUserModifType);
        comUserModifType.setBounds(150, 150, 168, 35);

        jButton1.setText("Modifier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(180, 200, 113, 48);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/t.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(-50, 10, 242, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 330);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
                //String b = txtModifProduitID.getText();
                if (JOptionPane.showConfirmDialog(null, "modification sur l'utilisateur: "+id_user , "modification",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                stm.executeUpdate("UPDATE user SET mdp='" + jTextField1.getText() + "',type='" 
                        + comUserModifType.getSelectedItem().toString()
                    
                    + "' WHERE id='" + id_user+"'");
                JOptionPane.showMessageDialog(null, "Succès!");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UserModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserModif().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comUserModifType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}