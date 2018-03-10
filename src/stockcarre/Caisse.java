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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rango
 */
public class Caisse extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm,stm1;
    ResultSet Rs;
    
    public String selected_additionTable,selected_additionMenu,selected_additionQte,selected_additionPU,selected_additionAdd;
    

    DefaultTableModel model_caisse = new DefaultTableModel();
    DefaultTableModel model_encaissement = new DefaultTableModel();
    DefaultTableModel model_prelevement = new DefaultTableModel();
    
    DefaultComboBoxModel model_comCaisse = new DefaultComboBoxModel();

    DefaultComboBoxModel model_comCaisse2 = new DefaultComboBoxModel();
    
    public Caisse() {
        initComponents();
            
         btnEncaisse.setEnabled(false);
         jButton1.setEnabled(false);

            
    //TABLE HISTORIQUE-------------------------
         try{
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");

                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
         
         
    //----------------------------------------

         try{
                model_comCaisse2.removeAllElements();               
                model_comCaisse2.addElement("-- Choisir une table --");
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select des_table from commande where etat = 'En attente' group by des_table");

                while (fr.next()){
                    
                    model_comCaisse2.addElement(fr.getString("des_table"));
                   
                }
                comtablecaisse.setModel(model_comCaisse2);
 
            }catch (HeadlessException | SQLException ex) {
            }
         
         
         
         
         
         
         
         
    //COMBOBOX TABLE---------------------------------
//        try{
//                model_comCaisse.removeAllElements();               
//                model_comCaisse.addElement("-- Choisir une table --");
//                stm = conn.obtenirconnexion().createStatement();
//                ResultSet fr = stm.executeQuery("Select des_table from commande where etat = 'En attente' group by des_table");
//
//                while (fr.next()){
//                    
//                    model_comCaisse.addElement(fr.getString("des_table"));
//                   
//                }
//                comtablecaisse.setModel(model_comCaisse);
// 
//            }catch (HeadlessException | SQLException ex) {
//            }
       //---------------------------------------------------
        
        //CAISSE-------------------------------
        float encaiss = 0,pre = 0,caisse;
        try {
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from commande where etat ='paye'");
                fr2.next();
                encaiss = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        try {
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from prelevement");
                fr2.next();
                pre = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        caisse = encaiss - pre;
        txtCaisse.setText(caisse+"");
        
        
        
        //-----------------------------------------
        

    }
    
    
    private void afficher_detail() {
        
        if(comtablecaisse.getSelectedItem().equals("-- Choisir une table --") || comtablecaisse.getSelectedItem().equals("")){
            model_caisse.setColumnCount(0);
        
            model_caisse.addColumn("Menu & carte");
            model_caisse.addColumn("Qté");
            model_caisse.addColumn("P.U (Ar)");
            model_caisse.addColumn("Montant (Ar)");
            
            tblCaisse.setModel(model_caisse);
        }else{
 
        model_caisse.setColumnCount(0);
        
        model_caisse.addColumn("Menu & carte");
        model_caisse.addColumn("Qté");
        model_caisse.addColumn("P.U (Ar)");
        model_caisse.addColumn("Montant (Ar)");
        
        
        btnEncaisse.setEnabled(true);

    
        try {
                model_caisse.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where des_table = '"+comtablecaisse.getSelectedItem().toString()+"'"
                        + "&& etat ='En attente' ");
                    while (fr.next())   {
                        model_caisse.addRow(new Object[]{fr.getString("des_menu"),fr.getString("nbr_commande"),
                            fr.getString("prix_unitaire"), fr.getString("montant")});
                        txtdate.setText(fr.getString("date_payement"));
                        txtadd.setText(fr.getString("addition"));
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        try {
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from commande where des_table = '"+comtablecaisse.getSelectedItem().toString()+"'"
                        + "&& etat ='En attente' ");
                fr2.next();
                txttotal.setText(fr2.getString("sum(montant)")+" Ar");
               
                                        
            } catch (SQLException e) {System.err.println(e);}
        btnEncaisse.setEnabled(true);
        
        tblCaisse.setModel(model_caisse);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        comtablecaisse = new javax.swing.JComboBox();
        btnEncaisse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCaisse = new javax.swing.JTable();
        txttotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCaisse = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtadd = new javax.swing.JLabel();
        txtdate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_encaiss = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_preleve = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        dateFiltre = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txttotalEnc = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_menuPrincial = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(875, 562));
        setMinimumSize(new java.awt.Dimension(875, 562));
        setPreferredSize(new java.awt.Dimension(875, 562));
        setResizable(false);
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(null);

        comtablecaisse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Table n°" }));
        comtablecaisse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comtablecaisseItemStateChanged(evt);
            }
        });
        comtablecaisse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comtablecaisseMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comtablecaisseMouseExited(evt);
            }
        });
        jPanel2.add(comtablecaisse);
        comtablecaisse.setBounds(10, 30, 158, 34);

        btnEncaisse.setText(">>   Encaisser");
        btnEncaisse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEncaisse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncaisseActionPerformed(evt);
            }
        });
        jPanel2.add(btnEncaisse);
        btnEncaisse.setBounds(390, 410, 130, 30);

        tblCaisse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Designation", "Nombre", "Prix"
            }
        ));
        tblCaisse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaisseMouseClicked(evt);
            }
        });
        tblCaisse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCaisseKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCaisse);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 510, 300);

        txttotal.setBackground(new java.awt.Color(0, 102, 102));
        txttotal.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 255, 255));
        txttotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txttotal);
        txttotal.setBounds(160, 410, 220, 33);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NET A PAYER (Ar):");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 410, 150, 33);

        txtCaisse.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        txtCaisse.setForeground(new java.awt.Color(255, 255, 255));
        txtCaisse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCaisse.setText("900000 Ar");
        jPanel2.add(txtCaisse);
        txtCaisse.setBounds(620, 170, 210, 80);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("CAISSE");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(670, 110, 110, 80);

        jButton2.setText("Prélèvement");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(660, 410, 160, 30);

        txtadd.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtadd.setForeground(new java.awt.Color(255, 255, 255));
        txtadd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(txtadd);
        txtadd.setBounds(180, 30, 130, 40);

        txtdate.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtdate.setForeground(new java.awt.Color(255, 255, 255));
        txtdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(txtdate);
        txtdate.setBounds(330, 30, 190, 40);

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(10, 380, 130, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 880, 460);

        jTabbedPane1.addTab("Caisse", jPanel2);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        tbl_encaiss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_encaiss.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_encaiss);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Encaissement:");

        tbl_preleve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_preleve.setRowHeight(30);
        jScrollPane3.setViewportView(tbl_preleve);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Prélèvement:");

        dateFiltre.setDateFormatString("yyyy-MM-dd");
        dateFiltre.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                dateFiltreComponentAdded(evt);
            }
        });
        dateFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateFiltreMouseEntered(evt);
            }
        });
        dateFiltre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateFiltreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dateFiltreKeyTyped(evt);
            }
        });

        jButton4.setText("Filtrer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TOTAL:");

        txttotalEnc.setBackground(new java.awt.Color(0, 102, 102));
        txttotalEnc.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        txttotalEnc.setForeground(new java.awt.Color(255, 255, 255));
        txttotalEnc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField3.setBackground(new java.awt.Color(0, 102, 102));
        jTextField3.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(dateFiltre, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(126, 126, 126))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216)
                        .addComponent(txttotalEnc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(dateFiltre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotalEnc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Historique", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 60, 880, 480);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Caisse");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 10, 90, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/t.png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(-120, 10, 242, 40);

        btn_menuPrincial.setBackground(java.awt.Color.black);
        btn_menuPrincial.setForeground(new java.awt.Color(255, 255, 255));
        btn_menuPrincial.setText("Menus principal");
        btn_menuPrincial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuPrincialActionPerformed(evt);
            }
        });
        jPanel1.add(btn_menuPrincial);
        btn_menuPrincial.setBounds(730, 0, 140, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 880, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       login.menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CaissePrelevement caiss = new CaissePrelevement();
        caiss.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comtablecaisseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comtablecaisseItemStateChanged
        if (comtablecaisse.getSelectedItem().equals("-- Choisir une table --") || comtablecaisse.getSelectedItem().toString().length() == 0 || comtablecaisse.getSelectedItem() == null){
            txtadd.setText("");
            txtdate.setText("");
            txttotal.setText("");
            model_caisse.setRowCount(0);
            tblCaisse.setModel(model_caisse);
            btnEncaisse.setEnabled(false);
        }else{
            btnEncaisse.setEnabled(true);
            afficher_detail();
        }
    }//GEN-LAST:event_comtablecaisseItemStateChanged

    private void tblCaisseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCaisseKeyPressed
        

    }//GEN-LAST:event_tblCaisseKeyPressed

    private void tblCaisseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaisseMouseClicked
        int i = tblCaisse.getSelectedRow();
        MenuPrincipal.selected_additionAdd = txtadd.getText();
        MenuPrincipal.selected_additionMenu = tblCaisse.getValueAt(i, 0).toString();
        MenuPrincipal.selected_additionPU = tblCaisse.getValueAt(i, 1).toString();
        MenuPrincipal.selected_additionTable = comtablecaisse.getSelectedItem().toString();
         
        if(comtablecaisse.getSelectedItem().equals("-- Choisir une table --")){
            
        }else{
        jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_tblCaisseMouseClicked

    private void btnEncaisseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncaisseActionPerformed
        int i = comtablecaisse.getSelectedIndex();
        try {
                //String b = txtModifProduitID.getText();
                if (JOptionPane.showConfirmDialog(null, "Encaisser l'addition: "+txtadd.getText() , "Encaissement",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                stm.executeUpdate("UPDATE commande SET etat='paye' WHERE addition ='" + txtadd.getText()+"'");
                JOptionPane.showMessageDialog(null, "Succès!");
                comtablecaisse.setSelectedItem("-- Choisir une table --");
                comtablecaisse.removeItemAt(i);
                
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
       
        
        

    }//GEN-LAST:event_btnEncaisseActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
     
        
        jButton1.setEnabled(false);
        
        
        //CAISSE-------------------------------
        float encaiss = 0,pre = 0,caisse;
        try {
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from commande where etat ='paye'");
                fr2.next();
                encaiss = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        try {
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm.executeQuery("Select sum(montant) from prelevement");
                fr2.next();
                pre = fr2.getFloat("sum(montant)");
                    
            } catch (SQLException e) {System.err.println(e);}
        
        caisse = encaiss - pre;
        txtCaisse.setText(caisse+" Ar");
        
        
        
        //-----------------------------------------
 
        
        try{
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");

                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(dateFiltre.getDate() == null){
            
           try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where etat = 'paye' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur \n" + e.getMessage());
        }
        
        try{               
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select sum(montant) from commande where etat = 'paye'");
                
                
                if(fr.next()){
                txttotalEnc.setText(fr.getString("sum(montant)")+" Ar");}
                else{txttotalEnc.setText("0 Ar");}
            
        }catch (HeadlessException | SQLException ex) {
            }
        try{               
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select sum(montant) from prelevement");               
                if(fr.next()){
                jTextField3.setText(fr.getString("sum(montant)")+" Ar");}
                else{jTextField3.setText("0 Ar");}
                
            }catch (HeadlessException | SQLException ex) {
            }
        
        
            
                       
        }else{
            String dat = String.format("%1$tY-%1$tm-%1$td",dateFiltre.getDate());
            


        //ENCAISSEMENT-------------------------------------------
            try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where date_payement like '%"+dat+"%' && etat = 'paye' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        try{               
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select sum(montant) from commande where date_payement like '%"+dat+"%' && etat = 'paye'");
                
                if(fr.next()){
                    if(fr.getString("sum(montant)") == null){
                        txttotalEnc.setText("0 Ar");
                    }else{
                txttotalEnc.setText(fr.getString("sum(montant)")+" Ar");}
                
                }else{
                    txttotalEnc.setText("0 Ar");
                }
            }catch (HeadlessException | SQLException ex) {
            }
        
        
        //-------------------------------------------------------------
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement where date like '%"+dat+"%' order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        
        try{               
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select sum(montant) from prelevement where date like '%"+dat+"%'");
                fr.next();
                if(fr.getString("sum(montant)") == null){
                jTextField3.setText("0 Ar");}
                else{jTextField3.setText(fr.getString("sum(montant)")+" Ar");}
            }catch (HeadlessException | SQLException ex) {
            }
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void dateFiltreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateFiltreKeyTyped
        if(dateFiltre.getDate() == null){
                       
        }else{
            String dat = String.format("%1$tY-%1$tm-%1$td",dateFiltre.getDate());
            
            try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where date_payement like '%"+dat+"%' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement where date like '%"+dat+"%' order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        }
        
        
    }//GEN-LAST:event_dateFiltreKeyTyped

    private void dateFiltreComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_dateFiltreComponentAdded
        if(dateFiltre.getDate() == null){
                       
        }else{
            String dat = String.format("%1$tY-%1$tm-%1$td",dateFiltre.getDate());
            
            try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where date_payement like '%"+dat+"%' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement where date like '%"+dat+"%' order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        }
    }//GEN-LAST:event_dateFiltreComponentAdded

    private void dateFiltreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFiltreMouseEntered
        if(dateFiltre.getDate() == null){
                       
        }else{
            String dat = String.format("%1$tY-%1$tm-%1$td",dateFiltre.getDate());
            
            try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where date_payement like '%"+dat+"%' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement where date like '%"+dat+"%' order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        }
    }//GEN-LAST:event_dateFiltreMouseEntered

    private void dateFiltreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateFiltreKeyReleased
        if(dateFiltre.getDate() == null){
            JOptionPane.showMessageDialog(null, "error");
           try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur \n" + e.getMessage());
        }
            
            
            
            
        }else{
            String dat = String.format("%1$tY-%1$tm-%1$td",dateFiltre.getDate());
            
            try{
                model_encaissement.setColumnCount(0);
                model_encaissement.addColumn("Addition n°");
                model_encaissement.addColumn("Table");
                model_encaissement.addColumn("Menu & carte");
                model_encaissement.addColumn("Qté");
                model_encaissement.addColumn("Montant");
                
                model_encaissement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from commande where date_payement like '%"+dat+"%' order by addition desc");
                while (fr.next()) {
                model_encaissement.addRow(new Object[]{fr.getString("addition"),fr.getString("des_table"), fr.getString("des_menu"),
                    fr.getString("nbr_commande"),fr.getString("montant")});
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        tbl_encaiss.setModel(model_encaissement);
        
        
        
        try{    
                model_prelevement.setColumnCount(0);
                model_prelevement.addColumn("Montant");
                model_prelevement.addColumn("Date");
                
                
                model_prelevement.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from prelevement where date like '%"+dat+"%' order by date desc");
                while (fr.next()) {
                model_prelevement.addRow(new Object[]{fr.getString("montant")+" Ar",fr.getString("date")});
            }tbl_preleve.setModel(model_prelevement);
            
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur \n" + e.getMessage());
        }
        }
    }//GEN-LAST:event_dateFiltreKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int i = tblCaisse.getSelectedRow();
        selected_additionMenu = tblCaisse.getValueAt(i, 0).toString();
        selected_additionQte = tblCaisse.getValueAt(i, 1).toString();
        selected_additionPU = tblCaisse.getValueAt(i, 2).toString();
        
        selected_additionTable = comtablecaisse.getSelectedItem().toString();
        

        

            try {
                if (JOptionPane.showConfirmDialog(null, "voulez-vous vraiment annuler cette commande?" , "Annulation",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                
                    
                    
                    stm.executeUpdate("Delete From commande where des_table = '" + comtablecaisse.getSelectedItem().toString()+"' &&"
                            + " des_menu = '"+tblCaisse.getValueAt(i, 0).toString()+"' && etat ='En attente'");
                    JOptionPane.showMessageDialog(null, "Succès!");
                    
                    
                    if(tblCaisse.getRowCount() == 1){
                    int t = comtablecaisse.getSelectedIndex();
                    comtablecaisse.removeItemAt(t);
                    comtablecaisse.setSelectedIndex(0);
                    }else{afficher_detail();}
                    
                    
                    
                    
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
            

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
       
        
        
    }//GEN-LAST:event_formMouseWheelMoved

    private void comtablecaisseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comtablecaisseMouseClicked
        
    }//GEN-LAST:event_comtablecaisseMouseClicked

    private void comtablecaisseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comtablecaisseMouseExited
       
    }//GEN-LAST:event_comtablecaisseMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void btn_menuPrincialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuPrincialActionPerformed
        login.menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_menuPrincialActionPerformed

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
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caisse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caisse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncaisse;
    private javax.swing.JButton btn_menuPrincial;
    private javax.swing.JComboBox comtablecaisse;
    private com.toedter.calendar.JDateChooser dateFiltre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblCaisse;
    private javax.swing.JTable tbl_encaiss;
    private javax.swing.JTable tbl_preleve;
    private javax.swing.JLabel txtCaisse;
    private javax.swing.JLabel txtadd;
    private javax.swing.JLabel txtdate;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalEnc;
    // End of variables declaration//GEN-END:variables
}
