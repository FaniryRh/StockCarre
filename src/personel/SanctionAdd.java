/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personel;

import java.awt.HeadlessException;
import static java.lang.Float.parseFloat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author rango
 */
public class SanctionAdd extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    
    DefaultComboBoxModel combo_nom= new DefaultComboBoxModel();
    
    public SanctionAdd() {
        initComponents();
        try {
            combo_nom.removeAllElements();
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select nom from perso order by nom");
            while (fr.next()) {
                combo_nom.addElement(fr.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        comb_prenom_sanction.setModel(combo_nom);
        
        try {

                String nom = comb_prenom_sanction.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select matricul,salaire_rest from perso where nom ='"+nom+"'");
                fr.next();
                txt_imm_sanction.setText(fr.getString(1));
                txt_imm_salaire.setText(fr.getString("salaire_rest")+" Ar");
            } catch (SQLException e) {
                System.err.println(e);
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

        jScrollPane3 = new javax.swing.JScrollPane();
        txt_motif_sanction = new javax.swing.JTextArea();
        btn_valider_saction = new javax.swing.JButton();
        comb_prenom_sanction = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txt_imm_sanction = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_imm_salaire = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(296, 383));
        setMinimumSize(new java.awt.Dimension(296, 383));
        setPreferredSize(new java.awt.Dimension(296, 383));
        getContentPane().setLayout(null);

        txt_motif_sanction.setColumns(20);
        txt_motif_sanction.setRows(5);
        jScrollPane3.setViewportView(txt_motif_sanction);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(90, 206, 186, 80);

        btn_valider_saction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/valider.PNG"))); // NOI18N
        btn_valider_saction.setText("Enregistrer");
        btn_valider_saction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valider_sactionActionPerformed(evt);
            }
        });
        getContentPane().add(btn_valider_saction);
        btn_valider_saction.setBounds(30, 294, 130, 35);

        comb_prenom_sanction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comb_prenom_sanctionItemStateChanged(evt);
            }
        });
        getContentPane().add(comb_prenom_sanction);
        comb_prenom_sanction.setBounds(90, 40, 186, 29);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Prénom:");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(10, 38, 70, 29);

        txt_imm_sanction.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_imm_sanction.setForeground(new java.awt.Color(255, 255, 255));
        txt_imm_sanction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_imm_sanction.setText("Immatriculation");
        getContentPane().add(txt_imm_sanction);
        txt_imm_sanction.setBounds(90, 75, 186, 31);

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(166, 294, 110, 35);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Motif:");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Somme déduite(Ar)");

        txt_imm_salaire.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_imm_salaire.setForeground(new java.awt.Color(255, 255, 255));
        txt_imm_salaire.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_imm_salaire.setText("salaire");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Salaire restant:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_imm_salaire, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(4, 4, 4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_imm_salaire, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 300, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_valider_sactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valider_sactionActionPerformed
        if (comb_prenom_sanction.getSelectedItem() == null){

        }else{

            String matricul = txt_imm_sanction.getText();
            String nom = null;
            Float salaire = null;
            Float salaire_rest;

            try {
                ResultSet fr = stm.executeQuery("Select nom,salaire_rest from perso where matricul='"+matricul+"'");
                fr.next();
                nom = fr.getString(1);
                salaire = fr.getFloat(2);

            } catch (SQLException e) {
                System.err.println(e);
            }
            Float sanction = parseFloat(jTextField4.getText());
            salaire_rest = salaire - sanction;

            String date = null;
            try {
                ResultSet fr = stm.executeQuery("Select now()");
                fr.next();
                date = fr.getString(1);
            } catch (SQLException e) {
                System.err.println(e);
            }
            String motif = txt_motif_sanction.getText();

            String requete = "insert into sanction(matricul,nom,date,motif,salaire_deduit)VALUES('"
            + matricul + "','" + nom + "','" + date + "','" + motif + "','" + sanction + "')";

            String requete2 = "update perso set salaire_rest = '"+salaire_rest+"' where matricul = '"+matricul+"' ";
            try {
                stm.executeUpdate(requete);
                stm.executeUpdate(requete2);
                this.dispose();
                JOptionPane.showMessageDialog(null, "Effectué");


            } catch (HeadlessException | SQLException ex)   {
                JOptionPane.showMessageDialog(null, "erreur\n" + ex.getMessage());

            }

        }
    }//GEN-LAST:event_btn_valider_sactionActionPerformed

    private void comb_prenom_sanctionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comb_prenom_sanctionItemStateChanged
        if(comb_prenom_sanction.getSelectedItem() == null){

        }else{

            if (comb_prenom_sanction.getSelectedItem() == null){

            }else{
                try {

                    String nom = comb_prenom_sanction.getSelectedItem().toString();
                    stm = conn.obtenirconnexion().createStatement();
                    ResultSet fr = stm.executeQuery("Select matricul,salaire_rest from perso where nom='"+nom+"'");
                    fr.next();
                    txt_imm_sanction.setText(fr.getString(1));
                    txt_imm_salaire.setText(fr.getString(2));
                } catch (SQLException e) {
                    System.err.println(e);
                }

            }

        }
    }//GEN-LAST:event_comb_prenom_sanctionItemStateChanged

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
            java.util.logging.Logger.getLogger(SanctionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanctionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanctionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanctionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanctionAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_valider_saction;
    private javax.swing.JComboBox comb_prenom_sanction;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel txt_imm_salaire;
    private javax.swing.JLabel txt_imm_sanction;
    private javax.swing.JTextArea txt_motif_sanction;
    // End of variables declaration//GEN-END:variables
}