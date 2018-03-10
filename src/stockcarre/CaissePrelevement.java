/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.awt.HeadlessException;
import static java.lang.Float.parseFloat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author rango
 */
public class CaissePrelevement extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm,stm1;
    ResultSet Rs;
    float max;
    public CaissePrelevement() {
        initComponents();
        
        float encaiss = 0,pre = 0,caisse;
        try {
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from commande where etat ='paye'");
                fr2.next();
                encaiss = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        try {
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from prelevement");
                fr2.next();
                pre = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        caisse = encaiss - pre;
        max = caisse;
        total.setText(caisse+"");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(193, 300));
        setMinimumSize(new java.awt.Dimension(193, 300));
        setPreferredSize(new java.awt.Dimension(194, 317));
        setResizable(false);
        getContentPane().setLayout(null);

        jTextField1.setBackground(new java.awt.Color(0, 102, 102));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jTextField1);
        jTextField1.setBounds(10, 171, 173, 32);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAISSE:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 173, 35);

        total.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(0, 204, 204));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("900000 Ar");
        getContentPane().add(total);
        total.setBounds(10, 52, 173, 35);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Prélèvement de:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 136, 173, 29);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 252, 47, 37);

        jButton2.setText("Annuler");
        getContentPane().add(jButton2);
        jButton2.setBounds(63, 252, 120, 37);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 190, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        float val = parseFloat(jTextField1.getText());
        String date = null;
        try {
            ResultSet fr = stm.executeQuery("Select now()");
            fr.next();
            date = fr.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        if(max < val){
            JOptionPane.showMessageDialog(null, "Caisse insuffisant!");
        }else{
            
            try{
                    try { 
                    stm = conn.obtenirconnexion().createStatement();
                    String requete = "insert into prelevement(montant,date)VALUES('"+jTextField1.getText()+"','"+date+"')";
                    stm.executeUpdate(requete);
                    JOptionPane.showMessageDialog(null, "Prélèvement éffectué!");

                    this.dispose();
                    
                } catch (HeadlessException | SQLException ex)   {
                    
                }
                    
                    
                    this.dispose();

             
        }catch (HeadlessException ex) {
        }
        
        
        }
        
        
        
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
            java.util.logging.Logger.getLogger(CaissePrelevement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaissePrelevement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaissePrelevement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaissePrelevement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaissePrelevement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
