/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personel;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author rango
 */
public class appli extends javax.swing.JFrame {

    conect conn = new conect();
    Statement stm;
    ResultSet Rs;
    public String selected_pers;
    public String selected_abs;
    public String selected_id_abs;
    public String selected_id_conge;
    
    DefaultTableModel model_personel = new DefaultTableModel();
    DefaultTableModel model_pointage = new DefaultTableModel();
    DefaultTableModel model_sanction = new DefaultTableModel();
    DefaultTableModel model_salaire = new DefaultTableModel();
    DefaultTableModel model_sanction_salaire = new DefaultTableModel();
    DefaultTableModel model_absent_suivi = new DefaultTableModel();
    DefaultTableModel model_present_suivi = new DefaultTableModel();
    DefaultTableModel model_absent = new DefaultTableModel();
    DefaultTableModel model_conge = new DefaultTableModel();
    DefaultTableModel model_avance = new DefaultTableModel();
    DefaultTableModel model_recap = new DefaultTableModel();
    
    
    DefaultComboBoxModel combo_prenom_pointage = new DefaultComboBoxModel();
    DefaultComboBoxModel combo_prenom_sanction= new DefaultComboBoxModel();
    
    DefaultComboBoxModel combo_prenom_conge= new DefaultComboBoxModel();
    
    
    public appli() {
        initComponents();
        
        
        model_personel.addColumn("Immatriculation");
        model_personel.addColumn("Prénom/Nom");
        model_personel.addColumn("Catégorie");
        model_personel.addColumn("Fonction");
        
        
        model_pointage.addColumn("Prénom/Nom");
        //model_pointage.addColumn("nom");
        model_pointage.addColumn("Type");
        model_pointage.addColumn("Date/Heure");
        
        
        model_sanction.addColumn("#");
        model_sanction.addColumn("Immatriculation");
        model_sanction.addColumn("Prénom/Nom");
        model_sanction.addColumn("Date");
        model_sanction.addColumn("Motif");
        model_sanction.addColumn("Salaire deduit(Ar)");
        
        model_absent.addColumn("#");
        model_absent.addColumn("Immatriculation");
        model_absent.addColumn("Prénom/Nom");
        model_absent.addColumn("Début");
        model_absent.addColumn("Fin");
        model_absent.addColumn("Motif");
        
        model_avance.addColumn("#");
        model_avance.addColumn("Immatriculation");
        model_avance.addColumn("Nom");
        model_avance.addColumn("Avance (Ar)");
        model_avance.addColumn("Date");
        
        
        model_conge.addColumn("#");
        model_conge.addColumn("Prénom/Nom");
        model_conge.addColumn("Date début");
        model_conge.addColumn("Date fin");

        model_present_suivi.addColumn("Prénom/Nom");
        model_present_suivi.addColumn("Fonction");
        
        model_absent_suivi.addColumn("Prénom/Nom");
        model_absent_suivi.addColumn("Fonction");
        

        model_recap.addColumn("Type");
        model_recap.addColumn("Date/Debut");
        model_recap.addColumn("Date/Fin");
        model_recap.addColumn("Motif/Valeur");
        model_recap.setRowCount(0);
        tbl_recap.setModel(model_recap);
        
        
        
        
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from perso order by nom");
            while (fr.next()) {
                model_personel.addRow(new Object[]{fr.getString("nom"), fr.getString("sex"), fr.getString("matricul"),
                    fr.getString("cnaps"), fr.getString("datenais"), fr.getString("categori"),fr.getString("fonction"), 
                    fr.getString("salaire"), fr.getString("dateemb"), fr.getString("tel"), fr.getString("adrs")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_pers.setModel(model_personel);

        
                                
                                btn_suppre_pers.setEnabled(false);
                                btn_modif_pers.setEnabled(false);
                                btnmodifAbs.setEnabled(false);
                                btnSupprAbs1.setEnabled(false);
                                btnModifConge1.setEnabled(false);
                                jButton4.setEnabled(false);
                                jButton7.setEnabled(false);
                                
                                
       afficher_absent();
       afficher_sanction();
       deplace_combo_sanction();
       afficher_conge();
       afficher_avance();
       
       
       if(tbl_pers.getRowCount() == 0){
           jButton8.setEnabled(false);
           jButton3.setEnabled(false);
           jButton1.setEnabled(false);
           jButton2.setEnabled(false);
           jButton6.setEnabled(false);
           jButton12.setEnabled(false);
           
       }else{
           jButton8.setEnabled(true);
           jButton3.setEnabled(true);
           jButton1.setEnabled(true);
           jButton2.setEnabled(true);
           jButton6.setEnabled(true);
           jButton12.setEnabled(true);
       }
       
       
       
       if(com_nom_recap.getSelectedIndex() == -1){
           jButton10.setEnabled(false);
        }else{
           jButton10.setEnabled(true);
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

        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pers = new javax.swing.JTable();
        btn_ajout_pers = new javax.swing.JButton();
        btn_modif_pers = new javax.swing.JButton();
        btn_suppre_pers = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        btnmodifAbs = new javax.swing.JButton();
        btnSupprAbs1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_conge = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnModifConge1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_avance = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sanction = new javax.swing.JTable();
        txt_rech_sanction = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_recap = new javax.swing.JTable();
        com_nom_recap = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        txt_cong_rest = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txt_salair_rest = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion des personnels");
        setBackground(java.awt.Color.black);
        setForeground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 532));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(null);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jTabbedPane1.setForeground(new java.awt.Color(0, 51, 153));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        tbl_pers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tbl_pers.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_pers.setForeground(new java.awt.Color(0, 153, 153));
        tbl_pers.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_pers.setRowHeight(40);
        tbl_pers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_persMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_pers);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(6, 50, 870, 331);

        btn_ajout_pers.setBackground(java.awt.SystemColor.windowText);
        btn_ajout_pers.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btn_ajout_pers.setForeground(new java.awt.Color(255, 255, 255));
        btn_ajout_pers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/131 copie.png"))); // NOI18N
        btn_ajout_pers.setText("Ajouter");
        btn_ajout_pers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_persActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ajout_pers);
        btn_ajout_pers.setBounds(6, 6, 119, 38);

        btn_modif_pers.setBackground(java.awt.SystemColor.windowText);
        btn_modif_pers.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btn_modif_pers.setForeground(new java.awt.Color(255, 255, 255));
        btn_modif_pers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/modif.PNG"))); // NOI18N
        btn_modif_pers.setText("Modifier");
        btn_modif_pers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modif_persActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modif_pers);
        btn_modif_pers.setBounds(6, 387, 123, 42);

        btn_suppre_pers.setBackground(java.awt.SystemColor.windowText);
        btn_suppre_pers.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btn_suppre_pers.setForeground(new java.awt.Color(255, 255, 255));
        btn_suppre_pers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/suppr.png"))); // NOI18N
        btn_suppre_pers.setText("Supprimer");
        btn_suppre_pers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suppre_persActionPerformed(evt);
            }
        });
        jPanel1.add(btn_suppre_pers);
        btn_suppre_pers.setBounds(135, 387, 128, 42);

        jTextField9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(153, 153, 153));
        jTextField9.setText("Rechercher...");
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9MouseClicked(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField9);
        jTextField9.setBounds(710, 6, 166, 26);

        jButton8.setBackground(java.awt.SystemColor.windowText);
        jButton8.setFont(new java.awt.Font("HighlandGothicFLF", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/fiche.PNG"))); // NOI18N
        jButton8.setText("Fiche individuel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(710, 390, 168, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 890, 460);

        jTabbedPane1.addTab("Personnel", jPanel1);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setRowHeight(40);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jPanel4.add(jScrollPane6);
        jScrollPane6.setBounds(10, 50, 860, 340);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/actu copie.PNG"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);
        jButton5.setBounds(942, 11, 48, 28);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextField1);
        jTextField1.setBounds(730, 388, 140, 40);

        jComboBox2.setBackground(java.awt.SystemColor.control);
        jComboBox2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nom/Prénoms", "Date début", "Date fin", " " }));
        jPanel4.add(jComboBox2);
        jComboBox2.setBounds(560, 388, 163, 40);

        jButton3.setBackground(java.awt.SystemColor.windowText);
        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/valider.PNG"))); // NOI18N
        jButton3.setText("Ajouter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jButton3.setBounds(10, 10, 110, 40);

        btnmodifAbs.setBackground(java.awt.SystemColor.windowText);
        btnmodifAbs.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnmodifAbs.setForeground(new java.awt.Color(255, 255, 255));
        btnmodifAbs.setText("Modifier");
        btnmodifAbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifAbsActionPerformed(evt);
            }
        });
        jPanel4.add(btnmodifAbs);
        btnmodifAbs.setBounds(10, 390, 130, 40);

        btnSupprAbs1.setBackground(java.awt.SystemColor.windowText);
        btnSupprAbs1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSupprAbs1.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprAbs1.setText("Supprimer");
        btnSupprAbs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprAbs1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnSupprAbs1);
        btnSupprAbs1.setBounds(150, 390, 130, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel4.add(jLabel4);
        jLabel4.setBounds(0, 0, 890, 460);

        jTabbedPane1.addTab("Absence", jPanel4);

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(null);

        tbl_conge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tbl_conge.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_conge.setForeground(new java.awt.Color(0, 153, 153));
        tbl_conge.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_conge.setRowHeight(40);
        tbl_conge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_congeMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_conge);

        jPanel10.add(jScrollPane7);
        jScrollPane7.setBounds(10, 50, 860, 340);

        jButton1.setBackground(java.awt.SystemColor.windowText);
        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton1);
        jButton1.setBounds(10, 10, 150, 40);

        btnModifConge1.setBackground(java.awt.SystemColor.windowText);
        btnModifConge1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnModifConge1.setForeground(new java.awt.Color(255, 255, 255));
        btnModifConge1.setText("Modifier");
        btnModifConge1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifConge1ActionPerformed(evt);
            }
        });
        jPanel10.add(btnModifConge1);
        btnModifConge1.setBounds(10, 391, 160, 40);

        jButton9.setBackground(java.awt.SystemColor.windowText);
        jButton9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Réinitialiser");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton9);
        jButton9.setBounds(733, 390, 140, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel10.add(jLabel5);
        jLabel5.setBounds(0, 0, 890, 460);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab("Congé", jPanel7);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(null);

        tbl_avance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tbl_avance.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_avance.setForeground(new java.awt.Color(0, 153, 153));
        tbl_avance.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_avance.setRowHeight(40);
        tbl_avance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_avanceMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_avance);

        jPanel9.add(jScrollPane10);
        jScrollPane10.setBounds(10, 50, 860, 340);

        jButton2.setBackground(java.awt.SystemColor.windowText);
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2);
        jButton2.setBounds(10, 10, 130, 40);

        jButton4.setBackground(java.awt.SystemColor.windowText);
        jButton4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Annuler");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton4);
        jButton4.setBounds(10, 390, 130, 40);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel9.add(jLabel6);
        jLabel6.setBounds(0, 0, 890, 460);

        jTabbedPane1.addTab("Avance salaire", jPanel9);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(null);

        tbl_sanction.setAutoCreateRowSorter(true);
        tbl_sanction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tbl_sanction.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_sanction.setForeground(new java.awt.Color(0, 153, 153));
        tbl_sanction.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sanction.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbl_sanction.setRowHeight(40);
        tbl_sanction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanctionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_sanction);

        jPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(10, 50, 860, 340);

        txt_rech_sanction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rech_sanctionKeyTyped(evt);
            }
        });
        jPanel3.add(txt_rech_sanction);
        txt_rech_sanction.setBounds(720, 10, 150, 29);

        jButton6.setBackground(java.awt.SystemColor.windowText);
        jButton6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Ajouter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);
        jButton6.setBounds(10, 10, 130, 40);

        jButton7.setBackground(java.awt.SystemColor.windowText);
        jButton7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Annuler");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);
        jButton7.setBounds(10, 390, 130, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel3.add(jLabel7);
        jLabel7.setBounds(0, 0, 890, 460);

        jTabbedPane1.addTab("Sanction", jPanel3);

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(null);

        tbl_recap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tbl_recap.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_recap.setForeground(new java.awt.Color(0, 153, 153));
        tbl_recap.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_recap.setRowHeight(40);
        jScrollPane11.setViewportView(tbl_recap);

        jPanel11.add(jScrollPane11);
        jScrollPane11.setBounds(6, 100, 883, 282);

        com_nom_recap.setBackground(java.awt.SystemColor.control);
        jPanel11.add(com_nom_recap);
        com_nom_recap.setBounds(6, 6, 185, 36);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Congé restant :");
        jPanel11.add(jLabel41);
        jLabel41.setBounds(487, 56, 143, 34);

        txt_cong_rest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_cong_rest.setForeground(new java.awt.Color(255, 255, 255));
        txt_cong_rest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel11.add(txt_cong_rest);
        txt_cong_rest.setBounds(636, 56, 99, 38);

        jButton10.setBackground(java.awt.SystemColor.windowText);
        jButton10.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Réinitialiser");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton10);
        jButton10.setBounds(6, 388, 173, 39);

        jButton12.setBackground(java.awt.SystemColor.windowText);
        jButton12.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText(">>");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton12);
        jButton12.setBounds(197, 6, 60, 36);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Salaire restante :");
        jPanel11.add(jLabel43);
        jLabel43.setBounds(487, 7, 143, 34);

        txt_salair_rest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_salair_rest.setForeground(new java.awt.Color(255, 255, 255));
        txt_salair_rest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel11.add(txt_salair_rest);
        txt_salair_rest.setBounds(636, 6, 99, 38);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel11.add(jLabel3);
        jLabel3.setBounds(0, 0, 890, 460);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Récapitulation", jPanel8);

        jPanel6.add(jTabbedPane1);
        jTabbedPane1.setBounds(6, 68, 1018, 465);

        jLabel22.setFont(new java.awt.Font("MontereyFLF", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("PERSONNEL");
        jPanel6.add(jLabel22);
        jLabel22.setBounds(21, 34, 136, 28);

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\projet le carré\\design\\Capture3 copie.png")); // NOI18N
        jPanel6.add(jLabel1);
        jLabel1.setBounds(10, 0, 1010, 530);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(-10, 0, 1030, 620);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void afficher_perso() {

        try {
             model_personel.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from perso order by nom");
            while (fr.next()) {
                model_personel.addRow(new Object[]{fr.getString("matricul"), fr.getString("nom")
                       , fr.getString("categori"),fr.getString("fonction")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_pers.setModel(model_personel);
                     
    }
  
    private void afficher_sanction() {

        try {
            model_sanction.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from sanction order by nom");
            while (fr.next()) {
                model_sanction.addRow(new Object[]{fr.getString("numero"),fr.getString("matricul")
                        ,fr.getString("nom"), fr.getString("date"), fr.getString("motif"), fr.getString("salaire_deduit")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_sanction.setModel(model_sanction);
        
                            
    }
    
    
    
    private void afficher_recap() {
        
        String salaire_rest = null;
        String conge_rest = null;
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select salaire_rest,conge_rest from perso where nom = '"+com_nom_recap.getSelectedItem().toString()+"' order by nom");
            fr.next();
            
            salaire_rest = fr.getString(1);
            conge_rest = fr.getString(2);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        txt_salair_rest.setText(salaire_rest+" Ar");
        txt_cong_rest.setText(conge_rest+" jr");
        
        
        

        try {
            model_recap.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select type,datedebut,datefin,motif from absent where nom ='"+com_nom_recap.getSelectedItem().toString()+"' order by nom");
            while (fr.next()) {
                model_recap.addRow(new Object[]{fr.getString("type"), fr.getString("datedebut"), fr.getString("datefin"), fr.getString("motif")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select type,date,avance from avance where nom ='"+com_nom_recap.getSelectedItem().toString()+"' order by nom");
            while (fr.next()) {
                model_recap.addRow(new Object[]{fr.getString("type"), fr.getString("date"),"", "-"+fr.getString("avance")+" Ar"});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select type,datedebut,datefin,nbr_jr from conge where nom = '"+com_nom_recap.getSelectedItem().toString()+"' order by nom");
            while (fr.next()) {
                model_recap.addRow(new Object[]{"Congé", fr.getString("datedebut"), fr.getString("datefin"), fr.getString("nbr_jr")+" jr"});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select type,date,motif,salaire_deduit from sanction where nom = '"+com_nom_recap.getSelectedItem().toString()+"' order by nom");
            while (fr.next()) {
                model_recap.addRow(new Object[]{fr.getString("type"), fr.getString("date"),"", 
                    "(-"+fr.getString("salaire_deduit")+" Ar) "+fr.getString("motif")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        
        
        
        tbl_recap.setModel(model_recap);
        
                            
    }
    
    
    
    private void afficher_avance() {

        try {
            model_avance.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from avance order by nom");
            while (fr.next()) {
                model_avance.addRow(new Object[]{fr.getString("numero"),fr.getString("matricul"),fr.getString("nom"), fr.getString("avance"), fr.getString("date")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_avance.setModel(model_avance);
        
                            
    }
    
    
    
    
    
    
    
    private void afficher_conge() {
        
        try {
            model_conge.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from conge where type = 'encours' order by nom");
            while (fr.next()) {
                model_conge.addRow(new Object[]{fr.getString("numero"),fr.getString("nom"), fr.getString("datedebut"), fr.getString("datefin")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_conge.setModel(model_conge);
        
                            
    }
    
    
    
    private void afficher_absent() {

        try {
             model_absent.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from absent order by nom");
            while (fr.next()) {
                model_absent.addRow(new Object[]{fr.getString("numero"),fr.getString("matricul"),fr.getString("nom"),
                    fr.getString("datedebut"),fr.getString("datefin"),fr.getString("motif")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        jTable1.setModel(model_absent);
        
                            
    }
    

    
    
    
    private void deplace_combo_sanction() {
        
//        String value = (String) comb_categori_select_stock.getSelectedItem();

        try {
            combo_prenom_sanction.removeAllElements();
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select nom from perso order by nom");
            while (fr.next()) {
                combo_prenom_sanction.addElement(fr.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        com_nom_recap.setModel(combo_prenom_sanction);
        
                            
    }
  
 
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stockcarre.MenuPrincipal menu = new  stockcarre.MenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        AbsentAdd a = new AbsentAdd();
        a.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        fichemploye p=new fichemploye();
        p.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        try{
            model_personel.setRowCount(0);
            ResultSet fr = stm.executeQuery("Select * from perso where nom like '%"+jTextField9.getText()+"%'");
            while (fr.next()) {
                model_personel.addRow(new Object[]{fr.getString("matricul"), fr.getString("nom"),
                    fr.getString("categori"),fr.getString("fonction")});}
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        tbl_pers.setModel(model_personel);
    }//GEN-LAST:event_jTextField9KeyTyped

    private void btn_suppre_persActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suppre_persActionPerformed
                int i = tbl_pers.getSelectedRow();
                String val = tbl_pers.getValueAt(i, 0).toString();

                        try {
                                if (JOptionPane.showConfirmDialog(null, "voulez-vous vraiment supprimer: "
                                        + ""+tbl_pers.getValueAt(i, 1).toString()+" "+tbl_pers.getValueAt(i, 0).toString()+" ?" , 
                                        "supprimer le personnel",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                               
                                        stm.executeUpdate("Delete From perso where matricul = '" + val+"'");
                                        stm.executeUpdate("Delete From absent where matricul = '" + val+"'");
                                        stm.executeUpdate("Delete From conge where matricul = '" + val+"'");
                                        stm.executeUpdate("Delete From sanction where matricul = '" + val+"'");
                                        stm.executeUpdate("Delete From avance where matricul = '" + val+"'");
                    
                                        JOptionPane.showMessageDialog(null, "\""+tbl_pers.getValueAt(i, 1).toString()+" "
                                                + "" +tbl_pers.getValueAt(i, 0).toString()+"\" supprimé de la liste!");
                                        
                                        
        if(tbl_pers.getRowCount() == 0){
           jButton8.setEnabled(false);
           jButton3.setEnabled(false);
           jButton1.setEnabled(false);
           jButton2.setEnabled(false);
           jButton6.setEnabled(false);
           jButton12.setEnabled(false);
           
       }else{
           jButton8.setEnabled(true);
           jButton3.setEnabled(true);
           jButton1.setEnabled(true);
           jButton2.setEnabled(true);
           jButton6.setEnabled(true);
           jButton12.setEnabled(true);
       }
  
                            }
                    } catch (HeadlessException e) {
                        JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
                    } catch (SQLException ex) {
            Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_btn_suppre_persActionPerformed

    private void btn_modif_persActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modif_persActionPerformed
  
     PersonnelModif a = new PersonnelModif();
     a.setVisible(true);
        
    }//GEN-LAST:event_btn_modif_persActionPerformed

    private void btn_ajout_persActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_persActionPerformed
        PersonnelAdd pers = new PersonnelAdd();
        pers.setVisible(true);
    }//GEN-LAST:event_btn_ajout_persActionPerformed

    private void tbl_persMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_persMouseClicked
        btn_suppre_pers.setEnabled(true);
        btn_modif_pers.setEnabled(true);
        try {
            int i = tbl_pers.getSelectedRow();
            selected_pers = tbl_pers.getValueAt(i, 0).toString();
            btn_suppre_pers.setVisible(true);
            btn_modif_pers.setVisible(true);

        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "erreur de deplacement" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_tbl_persMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        afficher_absent();
        afficher_perso();
        afficher_conge();
        afficher_avance();
        afficher_sanction();
        deplace_combo_sanction();
        
        btn_modif_pers.setEnabled(false);
        btn_suppre_pers.setEnabled(false);
        btnmodifAbs.setEnabled(false);
        btnSupprAbs1.setEnabled(false);
        btnModifConge1.setEnabled(false);
        jButton4.setEnabled(false);
        jButton7.setEnabled(false);
        
        
        if(tbl_pers.getRowCount() == 0){
           jButton8.setEnabled(false);
           jButton3.setEnabled(false);
           jButton1.setEnabled(false);
           jButton2.setEnabled(false);
           jButton6.setEnabled(false);
           jButton12.setEnabled(false);
           
       }else{
           jButton8.setEnabled(true);
           jButton3.setEnabled(true);
           jButton1.setEnabled(true);
           jButton2.setEnabled(true);
           jButton6.setEnabled(true);
           jButton12.setEnabled(true);
       }
        
        if(com_nom_recap.getSelectedIndex() == -1){
           jButton10.setEnabled(false);
        }else{
           jButton10.setEnabled(true);
        }
            
        
    }//GEN-LAST:event_formWindowGainedFocus

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        switch (jComboBox2.getSelectedItem().toString()) {
            case "Nom/Prénoms":
            if (jTextField1.getText().equals("")) {
                //JOptionPane.showMessageDialog(this, "Aucun resultat");
                jTable1.setModel(null);
            }
            else {

                try{
                    model_absent.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from absent where nom like '%"+jTextField1.getText()+"%'");
                    while (fr.next()) {
                        model_absent.addRow(new Object[]{fr.getString("numero"),fr.getString("nom"), fr.getString("datedebut"), fr.getString("datefin"), fr.getString("motif")});

                    }
                }catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                jTable1.setModel(model_absent);

            }       break;
            case "Date debut":
            if (jTextField1.getText().equals("")) {
                jTable1.setModel(model_absent);
            }
            else {

                try{
                    model_absent.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from absent where datedebut like '%"+jTextField1.getText()+"%'");
                    while (fr.next()) {
                        model_absent.addRow(new Object[]{fr.getString("numero"),fr.getString("nom"), fr.getString("datedebut"), fr.getString("datefin"), fr.getString("motif")});

                    }
                }catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                jTable1.setModel(model_absent);

            }       break;
            default:
            if (jTextField1.getText().equals("")) {
                jTable1.setModel(model_absent);
            }
            else {

                try{
                    model_absent.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from absent where datefin like '%"+jTextField1.getText()+"%'");
                    while (fr.next()) {
                        model_absent.addRow(new Object[]{fr.getString("numero"),fr.getString("nom"), fr.getString("datedebut"), fr.getString("datefin"), fr.getString("motif")});

                    }
                }catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                jTable1.setModel(model_absent);

            }       break;
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void btnmodifAbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifAbsActionPerformed
        AbsentModif a = new AbsentModif();
        a.setVisible(true);
    }//GEN-LAST:event_btnmodifAbsActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int i = jTable1.getSelectedRow();
        selected_id_abs = jTable1.getValueAt(i, 0).toString();
        btnmodifAbs.setEnabled(true);
         btnSupprAbs1.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnSupprAbs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprAbs1ActionPerformed
        
        int i = jTable1.getSelectedRow();
                String val = jTable1.getValueAt(i, 0).toString();

                        try {
                                if (JOptionPane.showConfirmDialog(null, "Confirmez la suppression?" , 
                                        "supprimer l'absence",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                               
                                        stm.executeUpdate("Delete From absent where numero = '" + val+"'");
                                        
                    
                                        JOptionPane.showMessageDialog(null, "Succès");
  
                            }
                    } catch (HeadlessException e) {
                        JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
                    } catch (SQLException ex) {
            Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnSupprAbs1ActionPerformed

    private void btnModifConge1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifConge1ActionPerformed
       CongeModif a = new CongeModif();
       a.setVisible(true);

    }//GEN-LAST:event_btnModifConge1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CongeAdd a = new CongeAdd();
        a.setVisible(true);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_congeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_congeMouseClicked
       int i = tbl_conge.getSelectedRow();
       selected_id_conge = tbl_conge.getValueAt(i, 0).toString();
       btnModifConge1.setEnabled(true);
    }//GEN-LAST:event_tbl_congeMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AvanceAdd a = new AvanceAdd();
        a.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int i = tbl_avance.getSelectedRow();
        String avance = tbl_avance.getValueAt(i, 3).toString();
        String num = tbl_avance.getValueAt(i, 0).toString();
        String matricul = tbl_avance.getValueAt(i, 1).toString();
        
        try {
       if (JOptionPane.showConfirmDialog(null, "Confirmer l'annulation?" , "Annulation",
       JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
        stm.executeUpdate("UPDATE perso SET salaire_rest = salaire_rest + '"+avance+"' WHERE matricul ='" +matricul+"'");
        stm.executeUpdate("Delete From avance where numero = '" + num+"'");
        
         JOptionPane.showMessageDialog(null, "Succès!");

         }
     } catch (HeadlessException | SQLException e) {
      JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
       System.err.println(e);
       }
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_avanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_avanceMouseClicked
        jButton4.setEnabled(true);
    }//GEN-LAST:event_tbl_avanceMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SanctionAdd a = new SanctionAdd();
        a.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
         int i = tbl_sanction.getSelectedRow();
        String deduit = tbl_sanction.getValueAt(i, 5).toString();
        String num = tbl_sanction.getValueAt(i, 0).toString();
        String matricul = tbl_sanction.getValueAt(i, 1).toString();
        
        try {
       if (JOptionPane.showConfirmDialog(null, "Confirmer l'annulation?" , "Annulation",
       JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
        stm.executeUpdate("UPDATE perso SET salaire_rest = salaire_rest + '"+deduit+"' WHERE matricul ='" +matricul+"'");
        stm.executeUpdate("Delete From sanction where numero = '" + num+"'");
        
         JOptionPane.showMessageDialog(null, "Succès!");

         }
     } catch (HeadlessException | SQLException e) {
      JOptionPane.showMessageDialog(null, "erreur!" + e.getMessage());
       System.err.println(e);
       }
        
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tbl_sanctionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanctionMouseClicked
       jButton7.setEnabled(true);
    }//GEN-LAST:event_tbl_sanctionMouseClicked

    private void txt_rech_sanctionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rech_sanctionKeyTyped
        if (txt_rech_sanction.getText().equals("")) {
            afficher_sanction();
        }
        else {

            try{
                model_sanction.setRowCount(0);
                ResultSet fr = stm.executeQuery("Select * from sanction where nom like '%"+txt_rech_sanction.getText()+"%'");
                while (fr.next()) {
                    model_sanction.addRow(new Object[]{fr.getString("numero"),fr.getString("matricul")
                            ,fr.getString("nom"), fr.getString("date"), fr.getString("motif"), fr.getString("salaire_deduit")});}
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur \n" + e.getMessage());}
            tbl_sanction.setModel(model_sanction);

        }
    }//GEN-LAST:event_txt_rech_sanctionKeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        try {
       if (JOptionPane.showConfirmDialog(null, "Confirmer la réinitialisation?" , "Confirmation",
       JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                
                stm.executeUpdate("update perso set conge_rest = conge");
                stm.executeUpdate("delete from conge");
                JOptionPane.showMessageDialog(null, "Effectué");

         }
     } catch (HeadlessException | SQLException e) {
      JOptionPane.showMessageDialog(null, "erreur!" + e.getMessage());
       System.err.println(e);
       }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseClicked
       jTextField9.setText("");
    }//GEN-LAST:event_jTextField9MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if (com_nom_recap.getSelectedItem().equals("")){

        }else{

            afficher_recap();

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        String nom = com_nom_recap.getSelectedItem().toString();
        String requete = "update perso set salaire_rest = salaire where nom='"+nom+"'";
        String requet2 = "delete from sanction where nom='"+nom+"'";
        String requet3 = "delete from absent where nom='"+nom+"'";
        String requet4 = "delete from avance where nom='"+nom+"'";
        try {
            stm.executeUpdate(requete);
            stm.executeUpdate(requet2);
            stm.executeUpdate(requet3);
            stm.executeUpdate(requet4);

            afficher_recap();
            afficher_sanction();
            afficher_absent();
            afficher_avance();

        } catch (HeadlessException | SQLException ex)   {

        }
        JOptionPane.showMessageDialog(null, "Effectué");
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(appli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(appli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(appli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(appli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new appli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModifConge1;
    private javax.swing.JButton btnSupprAbs1;
    private javax.swing.JButton btn_ajout_pers;
    private javax.swing.JButton btn_modif_pers;
    private javax.swing.JButton btn_suppre_pers;
    private javax.swing.JButton btnmodifAbs;
    private javax.swing.JComboBox com_nom_recap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable tbl_avance;
    private javax.swing.JTable tbl_conge;
    private javax.swing.JTable tbl_pers;
    private javax.swing.JTable tbl_recap;
    private javax.swing.JTable tbl_sanction;
    private javax.swing.JLabel txt_cong_rest;
    private javax.swing.JTextField txt_rech_sanction;
    private javax.swing.JLabel txt_salair_rest;
    // End of variables declaration//GEN-END:variables
}
