/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personel;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author rango
 */
public class AbsentModif extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    
    String id_abs = stockcarre.MenuPrincipal.pers.selected_id_abs;
    String id_pers = stockcarre.MenuPrincipal.pers.selected_abs;
    
    public AbsentModif() {
        initComponents();
        

        try{
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from absent where numero ='"+id_abs+"'");

                fr.next();
                        txtNom.setText(fr.getString("nom"));
                        jLabel17.setText(fr.getString("matricul"));
                        datedebut.setDate(fr.getDate("datedebut"));
                        datefin.setDate(fr.getDate("datefin"));
                        jTextArea1.setText(fr.getString("motif"));

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

        jLabel13 = new javax.swing.JLabel();
        datedebut = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        datefin = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNom = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(330, 411));
        setMinimumSize(new java.awt.Dimension(330, 411));
        setPreferredSize(new java.awt.Dimension(330, 411));
        getContentPane().setLayout(null);

        jLabel13.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nom:");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 51, 80, 28);

        datedebut.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(datedebut);
        datedebut.setBounds(98, 128, 207, 26);

        jLabel21.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Début:");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(10, 128, 80, 26);

        jLabel23.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Fin:");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(10, 172, 80, 26);

        datefin.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(datefin);
        datefin.setBounds(98, 172, 207, 26);

        jLabel18.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Motif:");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(10, 229, 78, 27);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(98, 229, 206, 83);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/valider.PNG"))); // NOI18N
        jButton3.setText("Valider");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(98, 330, 110, 40);

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(214, 330, 90, 40);

        jLabel17.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 204, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Immatriculation");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(98, 86, 207, 29);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        txtNom.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        txtNom.setForeground(new java.awt.Color(0, 204, 204));
        txtNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNom.setText("nom");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 330, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    try {
       if (JOptionPane.showConfirmDialog(null, "Confirmer la modification?" , "modification",
       JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
        stm.executeUpdate("UPDATE absent SET datedebut = '"+String.format("%1$tY-%1$tm-%1$td",datedebut.getDate())
                +"',datefin ='"+String.format("%1$tY-%1$tm-%1$td",datefin.getDate())+"'"
                + ",motif = '"+jTextArea1.getText()+"' WHERE numero='" +id_abs+"'");
        
         JOptionPane.showMessageDialog(null, "Succès!");
         this.dispose();
         }
     } catch (HeadlessException | SQLException e) {
      JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
       System.err.println(e);
       }
                
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
            java.util.logging.Logger.getLogger(AbsentModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AbsentModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AbsentModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AbsentModif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AbsentModif().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser datedebut;
    private com.toedter.calendar.JDateChooser datefin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel txtNom;
    // End of variables declaration//GEN-END:variables
}