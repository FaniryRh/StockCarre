/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.HeadlessException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class appli extends javax.swing.JFrame {
    //DEBUT EN COMMUN
    conect conn = new conect();
    Statement stm,stm1,stm2;
    ResultSet Rs;
    int selected_index_entre = 0;
    
    public String selected,selected_fournisseur,selected_facture;
    DefaultTableModel model_defaut = new DefaultTableModel();
    //FIN EN COMMUN
    
    
    
    //DEBUT TAB PRODUIT
    DefaultTableModel model_stock = new DefaultTableModel();
    //FIN TAB PRODUIT
    
    DefaultTableModel model_evaluation = new DefaultTableModel();
    
    //FOURNISSEUR
    
    DefaultTableModel model_tbl_fournisseur = new DefaultTableModel();
    //FIN FOURNISSEUR
    
    
    //FACTURE
    conect connec_payer = new conect();
    DefaultTableModel model_payer = new DefaultTableModel();
    DefaultTableModel model_nonp = new DefaultTableModel();
    DefaultTableModel model_detail = new DefaultTableModel();
    DefaultTableModel model_detail_nonp = new DefaultTableModel();
    //FIN FACTURE
    
    //MOUVEMENT
    
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model_entre = new DefaultTableModel();
    DefaultTableModel model_sortie = new DefaultTableModel();
    DefaultListModel list = new DefaultListModel();
    Calendar cal = null;
    DefaultComboBoxModel com = new DefaultComboBoxModel();
    DefaultComboBoxModel com_fac = new DefaultComboBoxModel();
    DefaultComboBoxModel com_fac_produit = new DefaultComboBoxModel();
    DefaultComboBoxModel com_sorti = new DefaultComboBoxModel();
    DefaultComboBoxModel com_fourni_rech = new DefaultComboBoxModel();
    DefaultComboBoxModel com_prod_rech = new DefaultComboBoxModel();
    DefaultComboBoxModel com_entre = new DefaultComboBoxModel();
    DefaultComboBoxModel com_entre_facture = new DefaultComboBoxModel();
    
    DefaultComboBoxModel com_prod_evaluation = new DefaultComboBoxModel();
    
    DefaultTableModel model_prod_fourni = new DefaultTableModel();
    //FIN MOUV
    
    public appli() {
        initComponents();
        txt_date_evaluation.setText("yyyy-mm-dd");
        
        try { 
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
}
        
        btn_annul_stock.setVisible(false);
        btn_ajout_stock.setVisible(false);
        comb_categ_stock.setVisible(false);
        txt_prixunit_prod.setVisible(false);
        comb_unit_stock.setVisible(false);
        txt_qteAlert_stock.setVisible(false);
        txt_qteAct_stock.setVisible(false);
        txt_des_stock.setVisible(false);
        btn_modif_stock.setVisible(false);


//DEBUT STOCK
   

        model_stock.addColumn("ID");
        model_stock.addColumn("Designation");
        model_stock.addColumn("Qté actuelle");
        model_stock.addColumn("Qté d'alert");
        model_stock.addColumn("Unité de mesure");
        model_stock.addColumn("Prix unitaire(Ar)");
        model_stock.addColumn("Catégorie");
        
        try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from produit order by designation");
            while (fr.next()) {
                model_stock.addRow(new Object[]{fr.getString("id"),fr.getString("designation"),
                    fr.getString("qte_actuel"), fr.getString("qte_alert"), fr.getString("unite"),
                    fr.getString("prixunit"), fr.getString("categori")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_stock.setModel(model_stock);
        
        
        
        
        
        
//        btn_modif_stock.setVisible(false);
//        btn_suppr_stock.setVisible(false);
        
//FIN STOCK
        model_evaluation.addColumn("ID");
        model_evaluation.addColumn("Désignation");
        model_evaluation.addColumn("Qté actuelle");
        model_evaluation.addColumn("Qté commandée");
        model_evaluation.addColumn("Prix d'achat");
        model_evaluation.addColumn("Qté consommée");
        
        
        
//DEBUT FOURNISSEUR
        
         model_tbl_fournisseur.addColumn("ID");
        model_tbl_fournisseur.addColumn("Designation");
        //model_tbl_fournisseur.addColumn("Catégorie");
        model_tbl_fournisseur.addColumn("Tel");
        model_tbl_fournisseur.addColumn("Mail");
        model_tbl_fournisseur.addColumn("Adresse");       
        
        try {
            model_tbl_fournisseur.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from fournisseur order by designation");
            while (fr.next()) {
                model_tbl_fournisseur.addRow(new Object[]{fr.getString("id"),fr.getString("designation"), fr.getString("tel"), fr.getString("mail"),
                    fr.getString("adress")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fournisseur.setModel(model_tbl_fournisseur);
//        btn_suppr_stock1.setVisible(false);
//        btn_modif_stock1.setVisible(false);
        
//FIN FOURNISSEUR

        
        
        
//FACTURE
        
        model_payer.addColumn("N°");
        model_payer.addColumn("ID");
        model_payer.addColumn("Fournisseur");
        model_payer.addColumn("Date d'acquisition");
        model_payer.addColumn("date de paiement");
        
        //model_nonp.addColumn("N°");
        model_nonp.addColumn("ID");
        model_nonp.addColumn("Fournisseur");
        model_nonp.addColumn("Date d'acquisition");
        model_nonp.addColumn("date de payement");
        

        model_detail.addColumn("Designation");
        model_detail.addColumn("Qté réçu");
        model_detail.addColumn("Unité");
        model_detail.addColumn("Montant (Ar)");
        

        model_detail_nonp.addColumn("Designation");
        model_detail_nonp.addColumn("Qté réçu");
        model_detail_nonp.addColumn("Unité");
        model_detail_nonp.addColumn("Montant (Ar)");
        
        
        try {
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture where etat = 'oui'");
            while (fr.next()) {
                
                
                model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                    fr.getString("dateAcqui"), fr.getString("datePayemn")});

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture_paye.setModel(model_payer);
        afficher_nonp();
        
//FIN FACTURE

        
        
//MOUVEMENT 
        
    jButton16.setEnabled(false);
    
    
    
        
        model.addColumn("Designation");
        model.addColumn("Fournisseur");
        model.addColumn("Date d'acquisition");
        model.addColumn("Date limite");
        model.addColumn("Etat");
        
        
        model_entre.addColumn("#");
        model_entre.addColumn("Designation");
        model_entre.addColumn("Quantité ajouté");
        model_entre.addColumn("Unité de mesure");
        model_entre.addColumn("Prix total");
        model_entre.addColumn("Date");
        
        
        model_sortie.addColumn("#");
        model_sortie.addColumn("Designation");
        model_sortie.addColumn("Qté exclu");
        model_sortie.addColumn("Unité");
        model_sortie.addColumn("Date/heur");
        
        
        try {
                com_entre_facture.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from facture where etat2='ouvert'");
                while (fr.next()) {
                    com_entre_facture.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            comMouvEntreFacture.setModel(com_entre_facture);
        
        
        
        try {
            
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture order by dateAcqui desc");
            String ouvert = "Ouverte";
            String ferme = "Fermeé";
            
            while (fr.next()) {
                if (fr.getString("etat2").equals("ferme")){
                
                model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Fermée"});
                }else{
                    
                     model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Ouverte"});
                }
                
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture.setModel(model);       
        //update_3();
        //update_sorti();
        afficher_sortie();
        
        
        //deplacement dans entré
        
        if(com_prod_entre.getItemCount() == 0){

            jLabel26.setText("");
            jLabel29.setText("");
            jLabel33.setText("");

        }else{

            try {
                String e = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                while (fr.next()) {

                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
        
        //deplacement dans combobox produit dans l'onglet SORTI
        if (comb_unit_stock5.getItemCount() == 0){
        
        
        
        }else{
        
                try {
                    stm = conn.obtenirconnexion().createStatement();
                    String a = comb_unit_stock5.getSelectedItem().toString();
                    ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                    fc.next();
                    jLabel26.setText(fc.getString(1));
                    jLabel29.setText(fc.getString(2));
                    jLabel33.setText(fc.getString(2));
        } catch (SQLException e) {
            System.err.println(e);
        } 
        }
                         try {
                                com.removeAllElements();
                                stm = conn.obtenirconnexion().createStatement();

                                ResultSet fr = stm.executeQuery("Select designation from fournisseur");

                                while (fr.next()) {
                                    com.addElement(fr.getString(1));
                                 }
                               } catch (SQLException e) {
                                System.err.println(e);
                            }//comb_fourni_fac.setModel(com);
                            //deplace_combobox_facture_entrer();
                            
                            
                            if (comb_unit_stock5.getItemCount() == 0){

                                }else{

                                        try {

                                            stm = conn.obtenirconnexion().createStatement();
                                            String a = comb_unit_stock5.getSelectedItem().toString();
                                            ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                                            fc.next();
                                            
                                            jLabel40.setText(fc.getString(1));
                                            jLabel41.setText(fc.getString(2));
                                            jLabel43.setText(fc.getString(2));
                                        } catch (SQLException e) {
                                            System.err.println(e);
                                        }

                                        }
                            
                            
                            btnMouvSortiAnnul.setEnabled(false);
        
//FIN MOUVEMENT
                            
 
try {
                com_fourni_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from fournisseur");
                while (fr.next()) {
                    com_fourni_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_fourni_recherch.setModel(com_fourni_rech);
            
            
            try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_produit_recherch.setModel(com_prod_rech);
               
        model_prod_fourni.addColumn("Produit");
        model_prod_fourni.addColumn("Fournisseur");
        
        
        
        afficher_prod_fourni();

        
        //EVALUATION
        
        tbl_evaluation.setGridColor(java.awt.Color.pink);
//        try {
//            com_entre.removeAllElements();
//                com_prod_evaluation.removeAllElements();
//                stm = conn.obtenirconnexion().createStatement();
//                ResultSet fr = stm.executeQuery("Select designation from produit");
//                while (fr.next()) {
//                    com_prod_evaluation.addElement(fr.getString(1));
//                    com_entre.addElement(fr.getString(1));
//                }
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//            //com_designation_produit_evaluation.setModel(com_prod_evaluation);
//            com_prod_entre.setModel(com_entre);
//            
//         if (txt_date_evaluation.getText().equals("")){
//        
//            
//        }else{   
//        try {
//            String total = null;
//            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre date like '%"+txt_date_evaluation.getText()+"%'");
//            fr.next();
//            total = fr.getString(1);
//            txt_somm_eval.setText(total);
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        }
         
         
         
         
         
         
         btn_actu_eval.setVisible(false);
         jButton12.setVisible(false);
         
         
         
         if(com_prod_entre.getItemCount() == 0){

            jLabel26.setText("");
            jLabel29.setText("");
            jLabel33.setText("");

        }else{

            try {
                String e = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                while (fr.next()) {

                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
         
         
         try {

            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select designation from produit");
            while (fr.next()) {
                com_sorti.addElement(fr.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        comb_unit_stock5.setModel(com_sorti);
         
         
         
        
        
        
        if (comb_unit_stock5.getItemCount() == 0){

        }else{

            try {
                stm = conn.obtenirconnexion().createStatement();
                String a = comb_unit_stock5.getSelectedItem().toString();
                ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                fc.next();
                jLabel40.setText(fc.getString(1));
                jLabel41.setText(fc.getString(2));
                jLabel43.setText(fc.getString(2));
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        
        
        
        
         
        afficher_entre();
        
        
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
        jPanel1 = new javax.swing.JPanel();
        background_stock = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_stock = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txt_des_stock = new javax.swing.JTextField();
        txt_qteAct_stock = new javax.swing.JTextField();
        txt_qteAlert_stock = new javax.swing.JTextField();
        comb_categ_stock = new javax.swing.JComboBox();
        comb_unit_stock = new javax.swing.JComboBox();
        btn_ajout_stock = new javax.swing.JButton();
        btn_modif_stock = new javax.swing.JButton();
        btn_suppr_stock = new javax.swing.JButton();
        btn_annul_stock = new javax.swing.JButton();
        btn_recherch_stock = new javax.swing.JButton();
        txt_recherch_stock = new javax.swing.JTextField();
        txt_prixunit_prod = new javax.swing.JTextField();
        btnstockajout = new javax.swing.JButton();
        comboStockCategori = new javax.swing.JComboBox();
        btnStockModifProd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_fournisseur = new javax.swing.JTable();
        btn_suppr_stock1 = new javax.swing.JButton();
        btn_recherch_fournisseur = new javax.swing.JButton();
        txt_recherch_stock1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_facture = new javax.swing.JTable();
        btn_suppr_fac = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        btn_recherch_facture = new javax.swing.JButton();
        txt_recherch_facture = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_facture_non_p = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_detail_non_p = new javax.swing.JTable();
        comb_option_recherch_fac_non_p = new javax.swing.JComboBox();
        txt_recherche_fac_non_p = new javax.swing.JTextField();
        btn_recherch_fac_non_p = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        totalPrixentre = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_facture_paye = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_recherch_fac_p = new javax.swing.JButton();
        txt_recherche_fac_p = new javax.swing.JTextField();
        comb_option_recherch_fac_p = new javax.swing.JComboBox();
        btn_imprim_factur_p = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        com_prod_entre = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_qteAlert_stock2 = new javax.swing.JTextField();
        btn_ajout_stock2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_prixunit_ariv = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_entre_new = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        comMouvEntreFacture = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtMouvEntreID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMouvEntreFournisseur = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        comb_unit_stock5 = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_qteAlert_stock3 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btn_ajout_stock3 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_historique = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        btnMouvSortiAnnul = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        combo_fourni_recherch = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        combo_produit_recherch = new javax.swing.JComboBox();
        btn_ajout_prodfourni = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_recherch = new javax.swing.JTable();
        btn_recherch_prodfourni = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        txt_date_evaluation = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        btn_actu_eval = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        txt_somm_eval = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_evaluation = new javax.swing.JTable();
        comInventaireCategorie = new javax.swing.JComboBox();
        comInventaireType = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_menuPrincial = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de stock");
        setMinimumSize(new java.awt.Dimension(1045, 624));
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
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
        });
        getContentPane().setLayout(null);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        jTabbedPane1.setForeground(new java.awt.Color(0, 102, 153));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        background_stock.setBackground(new java.awt.Color(102, 102, 102));
        background_stock.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        background_stock.setLayout(null);

        tbl_stock.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_stock.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_stock.setForeground(new java.awt.Color(0, 153, 153));
        tbl_stock.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_stock.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_stock.setRowHeight(40);
        tbl_stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_stockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_stock);

        background_stock.add(jScrollPane1);
        jScrollPane1.setBounds(5, 52, 880, 376);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        background_stock.add(jButton1);
        jButton1.setBounds(816, 2, 69, 33);
        background_stock.add(txt_des_stock);
        txt_des_stock.setBounds(11, 73, 126, 26);
        background_stock.add(txt_qteAct_stock);
        txt_qteAct_stock.setBounds(11, 113, 126, 26);
        background_stock.add(txt_qteAlert_stock);
        txt_qteAlert_stock.setBounds(11, 143, 126, 26);

        comb_categ_stock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Condiments", "Fromages", "Fruits", "Féculents", "Légumes", "Oeufs", "Poudres", "Viandes", "Crèmerie", "Poisson", "Epicerie", "Materiels de cuisine" }));
        background_stock.add(comb_categ_stock);
        comb_categ_stock.setBounds(11, 243, 153, 23);

        comb_unit_stock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ariary", "Litre", "Sachet", "Bocal", "Boite", "Paquet", "Pièce", "Botte", "Kapoaka", "Brique", "Carton", "Barquette", "Kg", "Bouteille" }));
        background_stock.add(comb_unit_stock);
        comb_unit_stock.setBounds(11, 173, 126, 23);

        btn_ajout_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        btn_ajout_stock.setText("Ajouter");
        btn_ajout_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_stockActionPerformed(evt);
            }
        });
        background_stock.add(btn_ajout_stock);
        btn_ajout_stock.setBounds(11, 273, 126, 33);

        btn_modif_stock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_modif_stock.setForeground(new java.awt.Color(0, 51, 51));
        btn_modif_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/modif.PNG"))); // NOI18N
        btn_modif_stock.setText("Modifier");
        btn_modif_stock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_modif_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modif_stockActionPerformed(evt);
            }
        });
        background_stock.add(btn_modif_stock);
        btn_modif_stock.setBounds(360, 505, 40, 44);

        btn_suppr_stock.setBackground(java.awt.Color.black);
        btn_suppr_stock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_suppr_stock.setForeground(new java.awt.Color(255, 255, 255));
        btn_suppr_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/suppr.png"))); // NOI18N
        btn_suppr_stock.setText("Supprimer");
        btn_suppr_stock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_suppr_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suppr_stockActionPerformed(evt);
            }
        });
        background_stock.add(btn_suppr_stock);
        btn_suppr_stock.setBounds(141, 434, 130, 44);

        btn_annul_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/annul.PNG"))); // NOI18N
        btn_annul_stock.setText("Annuler");
        btn_annul_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annul_stockActionPerformed(evt);
            }
        });
        background_stock.add(btn_annul_stock);
        btn_annul_stock.setBounds(11, 313, 126, 33);

        btn_recherch_stock.setBackground(java.awt.Color.black);
        btn_recherch_stock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_recherch_stock.setForeground(new java.awt.Color(255, 255, 255));
        btn_recherch_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/search.PNG"))); // NOI18N
        btn_recherch_stock.setText("Rechercher");
        btn_recherch_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_stockActionPerformed(evt);
            }
        });
        background_stock.add(btn_recherch_stock);
        btn_recherch_stock.setBounds(751, 434, 134, 44);

        txt_recherch_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recherch_stockActionPerformed(evt);
            }
        });
        background_stock.add(txt_recherch_stock);
        txt_recherch_stock.setBounds(596, 434, 149, 44);
        background_stock.add(txt_prixunit_prod);
        txt_prixunit_prod.setBounds(11, 203, 126, 25);

        btnstockajout.setBackground(java.awt.Color.black);
        btnstockajout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnstockajout.setForeground(new java.awt.Color(255, 255, 255));
        btnstockajout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        btnstockajout.setText("Ajouter");
        btnstockajout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnstockajout.setInheritsPopupMenu(true);
        btnstockajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstockajoutActionPerformed(evt);
            }
        });
        background_stock.add(btnstockajout);
        btnstockajout.setBounds(5, 2, 130, 44);

        comboStockCategori.setBackground(java.awt.Color.darkGray);
        comboStockCategori.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        comboStockCategori.setForeground(new java.awt.Color(255, 255, 255));
        comboStockCategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Condiments", "Fromages", "Fruits", "Féculents", "Légumes", "Oeufs", "Poudres", "Viandes", "Crèmerie", "Poisson", "Epicerie", "Materiels de cuisine" }));
        comboStockCategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboStockCategoriItemStateChanged(evt);
            }
        });
        background_stock.add(comboStockCategori);
        comboStockCategori.setBounds(360, 2, 220, 44);

        btnStockModifProd.setBackground(java.awt.Color.black);
        btnStockModifProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnStockModifProd.setForeground(new java.awt.Color(255, 255, 255));
        btnStockModifProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/modif.PNG"))); // NOI18N
        btnStockModifProd.setText("Modifier");
        btnStockModifProd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStockModifProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockModifProdActionPerformed(evt);
            }
        });
        background_stock.add(btnStockModifProd);
        btnStockModifProd.setBounds(5, 434, 130, 44);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        background_stock.add(jLabel6);
        jLabel6.setBounds(0, 0, 890, 490);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background_stock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Stock", jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(null);

        tbl_fournisseur.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_fournisseur.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_fournisseur.setForeground(new java.awt.Color(0, 153, 153));
        tbl_fournisseur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "ID", "Designation", "Catégorie", "Tel", "E-mail", "Adresse", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_fournisseur.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_fournisseur.setRowHeight(40);
        tbl_fournisseur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_fournisseurMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_fournisseur);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(3, 45, 882, 390);

        btn_suppr_stock1.setBackground(java.awt.SystemColor.windowText);
        btn_suppr_stock1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btn_suppr_stock1.setForeground(new java.awt.Color(255, 255, 255));
        btn_suppr_stock1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/suppr.png"))); // NOI18N
        btn_suppr_stock1.setText("Supprimer");
        btn_suppr_stock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suppr_stock1ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_suppr_stock1);
        btn_suppr_stock1.setBounds(140, 440, 134, 40);

        btn_recherch_fournisseur.setBackground(java.awt.SystemColor.windowText);
        btn_recherch_fournisseur.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btn_recherch_fournisseur.setForeground(new java.awt.Color(255, 255, 255));
        btn_recherch_fournisseur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/search.PNG"))); // NOI18N
        btn_recherch_fournisseur.setText("Rechercher");
        btn_recherch_fournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_fournisseurActionPerformed(evt);
            }
        });
        jPanel2.add(btn_recherch_fournisseur);
        btn_recherch_fournisseur.setBounds(740, 440, 142, 40);

        txt_recherch_stock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recherch_stock1ActionPerformed(evt);
            }
        });
        jPanel2.add(txt_recherch_stock1);
        txt_recherch_stock1.setBounds(600, 440, 142, 40);

        jButton2.setBackground(java.awt.SystemColor.windowText);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(837, 6, 48, 33);

        jButton6.setBackground(java.awt.SystemColor.windowText);
        jButton6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        jButton6.setText("Ajouter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6);
        jButton6.setBounds(3, 2, 134, 37);

        jButton7.setBackground(java.awt.SystemColor.windowText);
        jButton7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/modif.PNG"))); // NOI18N
        jButton7.setText("Modifier");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(0, 440, 134, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/personel/Capture3 copie2.png"))); // NOI18N
        jPanel2.add(jLabel7);
        jLabel7.setBounds(0, 0, 890, 490);

        jTabbedPane1.addTab("Fournisseur", jPanel2);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jTabbedPane2.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        tbl_facture.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_facture.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_facture.setForeground(new java.awt.Color(0, 153, 153));
        tbl_facture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "ID", "Designation", "Fournisseur", "Nette à payer", "Date d'acquisition", "date limite payement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_facture.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_facture.setRowHeight(40);
        tbl_facture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_factureMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_facture);

        btn_suppr_fac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/suppr.png"))); // NOI18N
        btn_suppr_fac.setText("Supprimer");
        btn_suppr_fac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suppr_facActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton14.setText("Ajouter");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Modifier");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        btn_recherch_facture.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_recherch_facture.setForeground(new java.awt.Color(0, 51, 51));
        btn_recherch_facture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/search.PNG"))); // NOI18N
        btn_recherch_facture.setText("Rechercher");
        btn_recherch_facture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_factureActionPerformed(evt);
            }
        });

        txt_recherch_facture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recherch_factureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_suppr_fac, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288)
                        .addComponent(txt_recherch_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_recherch_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_recherch_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_recherch_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_suppr_fac))
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Liste des Factures", jPanel18);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        tbl_facture_non_p.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_facture_non_p.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        tbl_facture_non_p.setForeground(new java.awt.Color(0, 153, 153));
        tbl_facture_non_p.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero", "ID (facture)", "Fournisseur", "Date d'aquisition", "Date limite"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_facture_non_p.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_facture_non_p.setRowHeight(40);
        tbl_facture_non_p.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_facture_non_pMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_facture_non_p);

        tbl_detail_non_p.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_detail_non_p.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        tbl_detail_non_p.setForeground(new java.awt.Color(0, 153, 153));
        tbl_detail_non_p.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID (produit)", "Designation", "Qté réçu", "Unité de mesure", "Prix total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_detail_non_p.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane6.setViewportView(tbl_detail_non_p);

        comb_option_recherch_fac_non_p.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date d'acquisition", "Date de payement", "ID", "Fournisseur" }));

        btn_recherch_fac_non_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/search.PNG"))); // NOI18N
        btn_recherch_fac_non_p.setText("Rechercher");
        btn_recherch_fac_non_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_fac_non_pActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/valider.PNG"))); // NOI18N
        jButton3.setText("Payer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Montant total:");

        totalPrixentre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        totalPrixentre.setForeground(new java.awt.Color(255, 255, 255));
        totalPrixentre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Ar");

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/imprime.PNG"))); // NOI18N
        jButton13.setText("Imprimer...");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Ar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalPrixentre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_recherche_fac_non_p, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comb_option_recherch_fac_non_p, javax.swing.GroupLayout.Alignment.LEADING, 0, 141, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_recherch_fac_non_p))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_recherch_fac_non_p, comb_option_recherch_fac_non_p});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comb_option_recherch_fac_non_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_recherche_fac_non_p, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_recherch_fac_non_p, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(totalPrixentre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_recherch_fac_non_p, comb_option_recherch_fac_non_p, jButton3, jButton9});

        jTabbedPane2.addTab("En attente", jPanel5);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        tbl_facture_paye.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 153)));
        tbl_facture_paye.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        tbl_facture_paye.setForeground(new java.awt.Color(0, 153, 153));
        tbl_facture_paye.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero", "ID", "Fournisseur", "Date d'aquisition", "Date de payement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_facture_paye.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_facture_paye.setRowHeight(40);
        tbl_facture_paye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_facture_payeMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_facture_paye);

        jTable1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 153)));
        jTable1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 153, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Designation", "Qté réçu", "Unité de mesure", "Prix total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(0, 204, 204));
        jScrollPane8.setViewportView(jTable1);

        btn_recherch_fac_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/search.PNG"))); // NOI18N
        btn_recherch_fac_p.setText("Rechercher");
        btn_recherch_fac_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_fac_pActionPerformed(evt);
            }
        });

        comb_option_recherch_fac_p.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date d'acquisition", "Date de payement", "ID", "Fournisseur" }));

        btn_imprim_factur_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/imprime.PNG"))); // NOI18N
        btn_imprim_factur_p.setText("Imprimer...");
        btn_imprim_factur_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprim_factur_pActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Montant total:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Ar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_recherche_fac_p)
                                    .addComponent(comb_option_recherch_fac_p, 0, 151, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_recherch_fac_p)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(128, 128, 128))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btn_imprim_factur_p, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comb_option_recherch_fac_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_recherche_fac_p, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_recherch_fac_p, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(btn_imprim_factur_p, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addGap(58, 58, 58))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_imprim_factur_p, btn_recherch_fac_p, comb_option_recherch_fac_p, jButton4});

        jTabbedPane2.addTab("Payé", jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Facture", jPanel3);

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Produit:");

        com_prod_entre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_prod_entreItemStateChanged(evt);
            }
        });
        com_prod_entre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_prod_entreActionPerformed(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("qté_actuel");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Qté en stock:");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("unité");

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("unité");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Entrées:");

        txt_qteAlert_stock2.setBackground(new java.awt.Color(0, 204, 255));
        txt_qteAlert_stock2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btn_ajout_stock2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        btn_ajout_stock2.setText("Ajouter à la facture");
        btn_ajout_stock2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_stock2ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Prix unitaire:");

        txt_prixunit_ariv.setForeground(new java.awt.Color(255, 255, 255));
        txt_prixunit_ariv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_prixunit_ariv.setText("Prix unitaire");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ar");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Detail de la facture:");

        tbl_entre_new.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_entre_new.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_entre_new.setForeground(new java.awt.Color(0, 153, 153));
        tbl_entre_new.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
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
        tbl_entre_new.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_entre_new.setRowHeight(40);
        tbl_entre_new.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_entre_newMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_entre_new);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Facture:");

        comMouvEntreFacture.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comMouvEntreFactureItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID:");

        txtMouvEntreID.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        txtMouvEntreID.setForeground(new java.awt.Color(0, 153, 153));

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fournisseur");

        txtMouvEntreFournisseur.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        txtMouvEntreFournisseur.setForeground(new java.awt.Color(0, 153, 153));

        jButton16.setText("Annuler");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setText("Fermer la facture");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qteAlert_stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_prixunit_ariv, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(com_prod_entre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comMouvEntreFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btn_ajout_stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMouvEntreID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMouvEntreFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMouvEntreFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMouvEntreID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comMouvEntreFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(com_prod_entre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_prixunit_ariv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_qteAlert_stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(btn_ajout_stock2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {com_prod_entre, jLabel26, jLabel29, jLabel33, txt_qteAlert_stock2});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel23, jLabel28, jLabel34});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel8, txt_prixunit_ariv});

        jTabbedPane3.addTab("Entré", jPanel8);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Produit:");

        comb_unit_stock5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comb_unit_stock5ItemStateChanged(evt);
            }
        });
        comb_unit_stock5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_unit_stock5ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Qté en stock:");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Qté en stock");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("unité");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Qté sortie:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("unité");

        btn_ajout_stock3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/valider.PNG"))); // NOI18N
        btn_ajout_stock3.setText("Valider");
        btn_ajout_stock3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_stock3ActionPerformed(evt);
            }
        });

        tbl_historique.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        tbl_historique.setForeground(new java.awt.Color(0, 153, 153));
        tbl_historique.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Designation", "Qté exclu", "Unité", "Date/heure", "Commentaire"
            }
        ));
        tbl_historique.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_historique.setGridColor(new java.awt.Color(0, 204, 204));
        tbl_historique.setRowHeight(40);
        tbl_historique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_historiqueMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbl_historique);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("HISTORIQUE");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        btnMouvSortiAnnul.setText("Annuler");
        btnMouvSortiAnnul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMouvSortiAnnulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_ajout_stock3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_qteAlert_stock3, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comb_unit_stock5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnMouvSortiAnnul, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 218, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton11)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comb_unit_stock5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_qteAlert_stock3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addComponent(btn_ajout_stock3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMouvSortiAnnul, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comb_unit_stock5, jLabel24});

        jTabbedPane3.addTab("Consommation", jPanel9);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Movement de stock", jPanel4);

        jPanel10.setBackground(new java.awt.Color(102, 102, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Fournisseur:");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Produit:");

        btn_ajout_prodfourni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/131 copie.png"))); // NOI18N
        btn_ajout_prodfourni.setText("Ajouter");
        btn_ajout_prodfourni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_prodfourniActionPerformed(evt);
            }
        });

        tbl_recherch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_recherch.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_recherch.setForeground(new java.awt.Color(0, 153, 153));
        tbl_recherch.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_recherch.setGridColor(new java.awt.Color(0, 153, 153));
        tbl_recherch.setRowHeight(40);
        tbl_recherch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_recherchMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tbl_recherch);

        btn_recherch_prodfourni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/filtre.png"))); // NOI18N
        btn_recherch_prodfourni.setText("Detaillée....");
        btn_recherch_prodfourni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recherch_prodfourniActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_ajout_prodfourni, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addComponent(combo_fourni_recherch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(combo_produit_recherch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btn_recherch_prodfourni))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_fourni_recherch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_produit_recherch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(btn_ajout_prodfourni, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_recherch_prodfourni, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combo_fourni_recherch, combo_produit_recherch});

        jTabbedPane1.addTab("Recherche", jPanel10);

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel13.setLayout(null);

        txt_date_evaluation.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_date_evaluation.setForeground(new java.awt.Color(153, 153, 153));
        txt_date_evaluation.setText("yyyy-mm-dd");
        txt_date_evaluation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_date_evaluationMouseClicked(evt);
            }
        });
        txt_date_evaluation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_date_evaluationKeyTyped(evt);
            }
        });
        jPanel13.add(txt_date_evaluation);
        txt_date_evaluation.setBounds(720, 10, 157, 31);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/filtre.png"))); // NOI18N
        jButton12.setText("Filtrer");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton12);
        jButton12.setBounds(10, 460, 100, 31);

        btn_actu_eval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stockcarre/actu copie.PNG"))); // NOI18N
        btn_actu_eval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actu_evalActionPerformed(evt);
            }
        });
        jPanel13.add(btn_actu_eval);
        btn_actu_eval.setBounds(10, 420, 48, 30);

        txt_somm_eval.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_somm_eval.setForeground(new java.awt.Color(102, 102, 102));
        txt_somm_eval.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Dépense globale:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Ar");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_somm_eval, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_somm_eval, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel17);
        jPanel17.setBounds(300, 400, 380, 60);

        tbl_evaluation.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        tbl_evaluation.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbl_evaluation.setForeground(new java.awt.Color(0, 153, 153));
        tbl_evaluation.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_evaluation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_evaluation.setEnabled(false);
        tbl_evaluation.setGridColor(new java.awt.Color(153, 255, 255));
        tbl_evaluation.setRowHeight(30);
        tbl_evaluation.setSelectionBackground(new java.awt.Color(0, 102, 153));
        jScrollPane4.setViewportView(tbl_evaluation);

        jPanel13.add(jScrollPane4);
        jScrollPane4.setBounds(10, 50, 870, 340);

        comInventaireCategorie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tous", "Boisson non alcolique", "Boisson alcolique", "Boisson chaud", "Boisson (sirop)", "Charcuteries", "Condiments", "Fromages", "Fruits", "Féculents", "Légumes", "Oeufs", "Poudres", "Viandes", "Crèmerie", "Poisson", "Epicerie", "Materiels de cuisine" }));
        comInventaireCategorie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comInventaireCategorieItemStateChanged(evt);
            }
        });
        jPanel13.add(comInventaireCategorie);
        comInventaireCategorie.setBounds(180, 10, 150, 30);

        comInventaireType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Stock", "Facture" }));
        comInventaireType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comInventaireTypeItemStateChanged(evt);
            }
        });
        jPanel13.add(comInventaireType);
        comInventaireType.setBounds(10, 10, 150, 30);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventaire", jPanel12);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(-3, 82, 1050, 540);

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(null);

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("GESTIONNAIRE DE STOCK");
        jPanel11.add(jLabel50);
        jLabel50.setBounds(0, 11, 208, 32);

        jLabel21.setIcon(new javax.swing.ImageIcon("D:\\projet le carré\\t.png")); // NOI18N
        jPanel11.add(jLabel21);
        jLabel21.setBounds(0, 10, 240, 40);

        btn_menuPrincial.setBackground(java.awt.Color.black);
        btn_menuPrincial.setForeground(new java.awt.Color(255, 255, 255));
        btn_menuPrincial.setIcon(new javax.swing.ImageIcon("D:\\projet le carré\\design\\test1-11-512 copie.png")); // NOI18N
        btn_menuPrincial.setText("Menus principal");
        btn_menuPrincial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuPrincialActionPerformed(evt);
            }
        });
        jPanel11.add(btn_menuPrincial);
        btn_menuPrincial.setBounds(899, 0, 140, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\projet le carré\\design\\Capture3 stock.png")); // NOI18N
        jPanel11.add(jLabel4);
        jLabel4.setBounds(0, 0, 1040, 570);

        getContentPane().add(jPanel11);
        jPanel11.setBounds(0, 0, 1050, 630);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Ajouter produit ...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Ajouter fournisseur ...");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Ajouter facture ...");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Inventaire");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Utilisateur");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Configuration");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 
    
 
    
private void afficher_evaluation_facture() {    
            if (txt_date_evaluation.getText().equals("Date de payement") || txt_date_evaluation.getText().equals("")){
                
                try{
                model_evaluation.setRowCount(0);
                model_evaluation.setColumnCount(0);
                
                model_evaluation.addColumn("Designation");
                model_evaluation.addColumn("Fournisseur");
                model_evaluation.addColumn("Date d'acquisition");
                model_evaluation.addColumn("Date de payement");
                model_evaluation.addColumn("Montant");
                model_evaluation.addColumn("Etat");
                
                
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,fournisseur,dateAcqui,datePayemn,etat from facture order by datePayemn desc");
                
                Statement stm3 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm3.executeQuery("Select sum(prixTotal) from entre");
                fr3.next();
                
                if (fr3.getString(1) == null){
                txt_somm_eval.setText("0");
                
                }else{
                
                txt_somm_eval.setText(fr3.getString(1));
                }
                
                while (fr.next()) {
                    String designation = fr.getString("designation");
                    String fournisseur = fr.getString("fournisseur");
                    String datedebut = fr.getString("dateAcqui");
                    String datefin = fr.getString("datePayemn");
                    String etat = fr.getString("etat");
                    
                    
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(prixTotal) from entre where id_facture ='"+designation+"'");
                
                fr2.next();
                
                
                if (fr2.getString(1) == null){
                    
                    if (etat.equals("oui")){

                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,"0 Ar","Payée"});
                    
                }else{
                    
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,"0 Ar","En attente"});
                }
                    
                    
                    
                }else{
                    
                    if (etat.equals("oui")){

                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,fr2.getString(1)+" Ar","Payée"});
                    
                }else{
                    
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,fr2.getString(1)+" Ar","En attente"});
                }
                }
                
                
                

            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
                }

            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
                
            
            
            
            }else{
                
            try{
                model_evaluation.setRowCount(0);
                model_evaluation.setColumnCount(0);
                
                model_evaluation.addColumn("Designation");
                model_evaluation.addColumn("Fournisseur");
                model_evaluation.addColumn("Date d'acquisition");
                model_evaluation.addColumn("Date de payement");
                model_evaluation.addColumn("Montant");
                model_evaluation.addColumn("Etat");
                
                
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation,fournisseur,dateAcqui,datePayemn,etat from facture where datePayemn like '%"+txt_date_evaluation.getText()+"%' order by datePayemn desc");
                
                float prixtot = 0;
                
                
                while (fr.next()) {
                    String designation = fr.getString("designation");
                    String fournisseur = fr.getString("fournisseur");
                    String datedebut = fr.getString("dateAcqui");
                    String datefin = fr.getString("datePayemn");
                    String etat = fr.getString("etat");
                    
                    
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(prixTotal) from entre where id_facture ='"+fr.getString("designation")+"'");
                
               
                
                fr2.next();
                
//                if(fr2.getString(1) == null){
//                
//                }else{
//                
//                }
                
                if (fr2.getString(1) == null){
                    
                    if (etat.equals("oui")){
                    //prixtot = prixtot + parseInt(fr2.getString(1));
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,"0 Ar","Payée"});
                    
                }else{
                    //prixtot = prixtot + parseInt(fr2.getString(1));
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,"0 Ar","En attente"});
                }
                    
                    
                    
                }else{
                    
                    if (etat.equals("oui")){
                    prixtot = prixtot + parseInt(fr2.getString(1));
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,fr2.getString(1)+" Ar","Payée"});
                    
                }else{
                    prixtot = prixtot + parseInt(fr2.getString(1));
                    model_evaluation.addRow(new Object[]{designation,fournisseur,
                    datedebut, datefin,fr2.getString(1)+" Ar","En attente"});
                }
                }
                

            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
                } txt_somm_eval.setText(prixtot+"");

            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
            
    
            }
}
    
 private void afficher_evaluation() { 
     
     String id,designation;  
if (txt_date_evaluation.getText().length() == 0){

    if(comInventaireCategorie.getSelectedItem().equals("Tous")){

    try{
                model_evaluation.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select id,designation,qte_actuel,unite from produit order by designation");
                
                Statement stm4 = conn.obtenirconnexion().createStatement();
                ResultSet fr4 = stm4.executeQuery("Select sum(prixTotal) from entre");
                fr4.next();
                
                if (fr4.getString(1) == null){
                txt_somm_eval.setText("0");
                }else{
                txt_somm_eval.setText(fr4.getString(1));
                }
                
                while (fr.next()) {
                    id = fr.getString("id");
                    designation = fr.getString("designation");
                    String qte = fr.getString("qte_actuel");
                    String unite = fr.getString("unite");
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(qte_recu),sum(prixTotal) from entre where designation_produit ='"+designation+"'");
                stm2 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm2.executeQuery("Select sum(qte_ex),unite from sortie where designation ='"+designation+"'");
                fr2.next();
                fr3.next();

                
                
                
                
                if (fr2.getString(1) == null && fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                        "0 "+unite, "0 Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null && fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, "0 Ar","0 "+unite});
                    
                    
                }else if (fr2.getString(1) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else{
                
                
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                }
                
                
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    

                
                }
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
}else{
    
                
                
                
                try{
                model_evaluation.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select id,designation,qte_actuel,unite from produit where categori = '"+comInventaireCategorie.getSelectedItem().toString()+"' order by designation");
                
                float prixtot = 0;
                
                
                
                while (fr.next()) {
                    id = fr.getString("id");
                    designation = fr.getString("designation");
                    String qte = fr.getString("qte_actuel");
                    String unite = fr.getString("unite");
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(qte_recu),sum(prixTotal) from entre where designation_produit ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%' ");
                stm2 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm2.executeQuery("Select sum(qte_ex),unite from sortie where designation ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%'");
                fr2.next();
                fr3.next();
                
               if(fr2.getString(1) == null){
                
                }else{
                prixtot = prixtot + parseInt(fr2.getString(2));
                }
                
                
                
                
                if (fr2.getString(1) == null && fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                        "0 "+unite, "0 Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null && fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, "0 Ar","0 "+unite});
                    
                    
                }else if (fr2.getString(1) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else{
                
                
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                }
                
                
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    

                
                }
               
                txt_somm_eval.setText(prixtot+" ");
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
                
                
                
                
            
    
}
    
    
    
    
        }



        else{ //String id,designation;
            
            if (comInventaireCategorie.getSelectedItem() == "Tous"){
                if (txt_date_evaluation.getText().length() == 0){
            
    
            try{
                model_evaluation.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select id,designation,qte_actuel,unite from produit order by designation");
                
                Statement stm4 = conn.obtenirconnexion().createStatement();
                ResultSet fr4 = stm4.executeQuery("Select sum(prixTotal) from entre");
                fr4.next();
                
                if (fr4.getString(1) == null){
                txt_somm_eval.setText("0");
                }else{
                txt_somm_eval.setText(fr4.getString(1));
                }
                
                while (fr.next()) {
                    id = fr.getString("id");
                    designation = fr.getString("designation");
                    String qte = fr.getString("qte_actuel");
                    String unite = fr.getString("unite");
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(qte_recu),sum(prixTotal) from entre where designation_produit ='"+designation+"'");
                stm2 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm2.executeQuery("Select sum(qte_ex),unite from sortie where designation ='"+designation+"'");
                fr2.next();
                fr3.next();

                
                
                
                
                if (fr2.getString(1) == null && fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                        "0 "+unite, "0 Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null && fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, "0 Ar","0 "+unite});
                    
                    
                }else if (fr2.getString(1) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else{
                
                
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                }
                
                
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    

                
                }
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
                
                
                
                
            }
                else{
            try{
                model_evaluation.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select id,designation,qte_actuel,unite from produit order by designation");
                
                Statement stm4 = conn.obtenirconnexion().createStatement();
                ResultSet fr4 = stm4.executeQuery("Select sum(prixTotal) from entre where date like '%"+txt_date_evaluation.getText()+"%' ");
                fr4.next();
                
                if (fr4.getString(1) == null){
                txt_somm_eval.setText("0");
                }else{
                txt_somm_eval.setText(fr4.getString(1));
                }
                
                while (fr.next()) {
                    id = fr.getString("id");
                    designation = fr.getString("designation");
                    String qte = fr.getString("qte_actuel");
                    String unite = fr.getString("unite");
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(qte_recu),sum(prixTotal) from entre where designation_produit ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%' ");
                stm2 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm2.executeQuery("Select sum(qte_ex),unite from sortie where designation ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%'");
                fr2.next();
                fr3.next();

                
                
                
                
                if (fr2.getString(1) == null && fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                        "0 "+unite, "0 Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null && fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, "0 Ar","0 "+unite});
                    
                    
                }else if (fr2.getString(1) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else{
                
                
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                }
                
                
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    

                
                }
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
            
                        }  
            
            
            
            
            
            }else{
                
                
                
                try{
                model_evaluation.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select id,designation,qte_actuel,unite from produit where categori = '"+comInventaireCategorie.getSelectedItem().toString()+"' order by designation");
                
                float prixtot = 0;
                
                
                
                while (fr.next()) {
                    id = fr.getString("id");
                    designation = fr.getString("designation");
                    String qte = fr.getString("qte_actuel");
                    String unite = fr.getString("unite");
                    try{
                
                //model_evaluation.setRowCount(0);
                
                stm1 = conn.obtenirconnexion().createStatement();
                ResultSet fr2 = stm1.executeQuery("Select sum(qte_recu),sum(prixTotal) from entre where designation_produit ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%' ");
                stm2 = conn.obtenirconnexion().createStatement();
                ResultSet fr3 = stm2.executeQuery("Select sum(qte_ex),unite from sortie where designation ='"+designation+"' && date like '%"+txt_date_evaluation.getText()+"%'");
                fr2.next();
                fr3.next();
                
               if(fr2.getString(1) == null){
                
                }else{
                prixtot = prixtot + parseInt(fr2.getString(2));
                }
                
                
                
                
                if (fr2.getString(1) == null && fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                        "0 "+unite, "0 Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null && fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, "0 Ar","0 "+unite});
                    
                    
                }else if (fr2.getString(1) == null && fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else if (fr2.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    "0 "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr2.getString(2) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite,"0 Ar",fr3.getString(1)+" "+unite});
                    
                }else if (fr3.getString(1) == null){
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar","0 "+unite});
                    
                }else{
                
                
                    model_evaluation.addRow(new Object[]{id,designation,qte+" "+unite,
                    fr2.getString(1)+" "+unite, fr2.getString(2)+" Ar",fr3.getString(1)+" "+unite});
                }
                
                
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    

                
                }
               
                txt_somm_eval.setText(prixtot+" ");
                
                
        
            
            
            }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            
            tbl_evaluation.setModel(model_evaluation);
                
                
                
                
            }
        }    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
private void big_update() {
    afficher_entre();
    afficher();
    try {
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from produit order by designation");
            while (fr.next()) {
                model_stock.addRow(new Object[]{fr.getString("id"),fr.getString("designation"),
                    fr.getString("qte_actuel"), fr.getString("qte_alert"), fr.getString("unite"),
                    fr.getString("prixunit"), fr.getString("categori")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_stock.setModel(model_stock);
    
    try {
            model_tbl_fournisseur.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from fournisseur order by designation");
            while (fr.next()) {
                model_tbl_fournisseur.addRow(new Object[]{fr.getString("id"),fr.getString("designation"), fr.getString("tel"), fr.getString("mail"),
                    fr.getString("adress")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fournisseur.setModel(model_tbl_fournisseur);
    
    
        try {
            model_payer.setRowCount(0);
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture where etat = 'oui'");
            while (fr.next()) {
                model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                    fr.getString("dateAcqui"), fr.getString("datePayemn")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture_paye.setModel(model_payer);
        afficher_nonp();
    
    
    
//try {
//                com_entre_facture.removeAllElements();
//                stm = conn.obtenirconnexion().createStatement();
//                ResultSet fr = stm.executeQuery("Select designation from facture where etat2='ouvert'");
//                while (fr.next()) {
//                    com_entre_facture.addElement(fr.getString(1));
//                }
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//            comMouvEntreFacture.setModel(com_entre_facture);
        
        
        
        try {
            
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture order by dateAcqui desc");
            while (fr.next()) {
                
                if (fr.getString("etat2").equals("ferme")){
                
                model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Fermée"});
                }else{
                    
                     model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Ouverte"});
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture.setModel(model);       
        //update_3();
        //update_sorti();
        afficher_sortie();    
    
    if(com_prod_entre.getItemCount() == 0){

            jLabel26.setText("");
            jLabel29.setText("");
            jLabel33.setText("");

        }else{

            try {
                String e = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                while (fr.next()) {

                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    
    
    
    
    //deplacement dans combobox produit dans l'onglet SORTI
        if (comb_unit_stock5.getItemCount() == 0){
        
        
        
        }else{
        
                try {
                    stm = conn.obtenirconnexion().createStatement();
                    String a = comb_unit_stock5.getSelectedItem().toString();
                    ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                    fc.next();
                    jLabel26.setText(fc.getString(1));
                    jLabel29.setText(fc.getString(2));
                    jLabel33.setText(fc.getString(2));
        } catch (SQLException e) {
            System.err.println(e);
        } 
        }
                         try {
                                com.removeAllElements();
                                stm = conn.obtenirconnexion().createStatement();

                                ResultSet fr = stm.executeQuery("Select designation from fournisseur");

                                while (fr.next()) {
                                    com.addElement(fr.getString(1));
                                 }
                               } catch (SQLException e) {
                                System.err.println(e);
                            }//comb_fourni_fac.setModel(com);
                            //deplace_combobox_facture_entrer();
                            
                            
                            if (comb_unit_stock5.getItemCount() == 0){

                                }else{

                                        try {

                                            stm = conn.obtenirconnexion().createStatement();
                                            String a = comb_unit_stock5.getSelectedItem().toString();
                                            ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                                            fc.next();
                                            
                                            jLabel40.setText(fc.getString(1));
                                            jLabel41.setText(fc.getString(2));
                                            jLabel43.setText(fc.getString(2));
                                        } catch (SQLException e) {
                                            System.err.println(e);
                                        }

                                        }
                            
   
                            
                            
                            
try {
                com_fourni_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from fournisseur");
                while (fr.next()) {
                    com_fourni_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_fourni_recherch.setModel(com_fourni_rech);
            
            
            try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_produit_recherch.setModel(com_prod_rech);
               
        model_prod_fourni.addColumn("Produit");
        model_prod_fourni.addColumn("Fournisseur");
        
        
        
        afficher_prod_fourni();

        
        //EVALUATION
        
        
//        try {
//            com_entre.removeAllElements();
//                com_prod_evaluation.removeAllElements();
//                stm = conn.obtenirconnexion().createStatement();
//                ResultSet fr = stm.executeQuery("Select designation from produit");
//                while (fr.next()) {
//                    com_prod_evaluation.addElement(fr.getString(1));
//                    com_entre.addElement(fr.getString(1));
//                }
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//            com_designation_produit_evaluation.setModel(com_prod_evaluation);
//            com_prod_entre.setModel(com_entre);
//            
//         if (txt_date_evaluation.getText().equals("")){
//        
//            
//        }else{   
//        try {
//            String total = null;
//            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre date like '%"+txt_date_evaluation.getText()+"%'");
//            fr.next();
//            total = fr.getString(1);
//            txt_somm_eval.setText(total);
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        }
         
         
         
         
         
         
         
         
         
         
         if(com_prod_entre.getItemCount() == 0){

            jLabel26.setText("");
            jLabel29.setText("");
            jLabel33.setText("");

        }else{

            try {
                String e = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                while (fr.next()) {

                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
         
         
         try {

            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select designation from produit");
            while (fr.next()) {
                com_sorti.addElement(fr.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        comb_unit_stock5.setModel(com_sorti);
         
         
         
        
        
        
        if (comb_unit_stock5.getItemCount() == 0){

        }else{

            try {
                stm = conn.obtenirconnexion().createStatement();
                String a = comb_unit_stock5.getSelectedItem().toString();
                ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                fc.next();
                jLabel40.setText(fc.getString(1));
                jLabel41.setText(fc.getString(2));
                jLabel43.setText(fc.getString(2));
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        
        
        
        
         
        afficher_entre();                            
                            
                            
    
    
    
    
        
                
}    
    
    
    
//DEBUT FONCTION STOCK
 
 
 private void afficher() {
        try {
                model_stock.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from produit order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("id"),fr.getString("designation"),fr.getString("qte_actuel"), fr.getString("qte_alert"),
                        fr.getString("unite"), fr.getString("prixunit"), fr.getString("categori")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_stock.setModel(model_stock);
        
        try {
            com_entre.removeAllElements();
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select designation from produit order by designation");
            while (fr.next()) {
                com_entre.addElement(fr.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        com_prod_entre.setModel(com_entre);
        comb_unit_stock5.setModel(com_entre);
        combo_produit_recherch.setModel(com_entre);
        //com_designation_produit_evaluation.setModel(com_entre);
}
 
 
 private void afficher_entre() {
        if(comMouvEntreFacture.getSelectedIndex() == -1){
            //jTextField1.setText(comMouvEntreFacture.getSelectedIndex()+"");
            model_entre.setRowCount(0);
            tbl_entre_new.setModel(model_entre);
        
        }else {
            //jTextField1.setText(comMouvEntreFacture.getSelectedIndex()+"");
     
            try {
                
                model_entre.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from entre where id_facture = '"+comMouvEntreFacture.getSelectedItem().toString()+"'");
                    while (fr.next())   {
                        model_entre.addRow(new Object[]{fr.getString("numero"),fr.getString("designation_produit"),fr.getString("qte_recu"),
                        fr.getString("unite"), fr.getString("prixTotal"), fr.getDate("date")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_entre_new.setModel(model_entre);
        
        try {
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select fournisseur from facture where designation = '"+comMouvEntreFacture.getSelectedItem().toString()+"'");
                fr.next();
                txtMouvEntreFournisseur.setText(fr.getString(1));
                txtMouvEntreID.setText(comMouvEntreFacture.getSelectedItem().toString());
            } catch (SQLException e) {System.err.println(e);}
        
 }              
}
 
 
 
// private void update_combo_categori() {
//        String a = (String) comb_categori_select_stock.getSelectedItem();
//        if (a.equals("Tous"))   {  
//                                    afficher();
//                                    
//                                }else if (a.equals("En alert")){
//        
//                                    try {
//                                            model_stock.setRowCount(0);
//                                            stm = conn.obtenirconnexion().createStatement();
//                                            ResultSet fr = stm.executeQuery("Select * from produit where qte_actuel <= qte_alert");
//
//                                            while (fr.next()) {
//                                            model_stock.addRow(new Object[]{
//                                                fr.getString("designation"),fr.getString("qte_actuel"), 
//                                                fr.getString("qte_alert"), fr.getString("unite"), fr.getString("prixunit"), 
//                                                fr.getString("categori")});
//                                            }
//                                        } catch (SQLException e) {System.err.println(e);}
//                            
//                                    tbl_stock.setModel(model_stock);
//        
//        
//                                }else   {
//                                            try {
//                                                    model_stock.setRowCount(0);
//                                                    stm = conn.obtenirconnexion().createStatement();
//                                                    ResultSet fr = stm.executeQuery("Select * from produit where categori ='"+a+"'");
//
//                                                    while (fr.next()) {
//                                                    model_stock.addRow(new Object[]{fr.getString("designation"),fr.getString("qte_actuel"), 
//                                                fr.getString("qte_alert"), fr.getString("unite"), fr.getString("prixunit"), 
//                                                fr.getString("categori")});
//                                 }
//                               } catch (SQLException e) {System.err.println(e);}
//
//                            tbl_stock.setModel(model_stock);
//                }
//    }
    
    
//FIN FONCTION STOCK
    
 
 
//FONCTION FOURNISSEUR
 
 
 
 private void afficher_fournisseur() {
        
//        String value = (String) comb_categori_select_stock.getSelectedItem();

        try {
            model_tbl_fournisseur.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();

            ResultSet fr = stm.executeQuery("Select * from fournisseur order by designation");

            while (fr.next()) {
                model_tbl_fournisseur.addRow(new Object[]{fr.getString("designation"), fr.getString("tel"), fr.getString("mail"),
                    fr.getString("adress")});
             }
           } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fournisseur.setModel(model_tbl_fournisseur);
                            
    }
 
 
 
 
//FIN FOURNISSEUR
 
 
 
 
 
 
 
//FONCTION FACTURE
 
private void afficher_payer() {
try {
            model_payer.setRowCount(0);
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture where etat = 'oui'&& etat2='ferme'");
            while (fr.next()) {
                model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                    fr.getString("dateAcqui"), fr.getString("datePayemn")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture_paye.setModel(model_payer);                     
   }

private void afficher_prod_fourni() {
try {
            model_prod_fourni.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from produit_fourni");
            while (fr.next()) {
                model_prod_fourni.addRow(new Object[]{fr.getString("designation"), fr.getString("fournisseur")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_recherch.setModel(model_prod_fourni);                     
   }
    
    
    
    private void afficher_nonp() {
try {
            model_nonp.setRowCount(0);
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture where etat = 'non' && etat2='ferme' order by designation desc");
            while (fr.next()) {
                model_nonp.addRow(new Object[]{fr.getString("designation"),fr.getString("fournisseur"),
                    fr.getString("dateAcqui"), fr.getString("datePayemn")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture_non_p.setModel(model_nonp);                     
   }
    
    
    
    private void afficher_detail() {
        
        try {
            model_detail.setRowCount(0);

            int i = tbl_facture_paye.getSelectedRow();
            String val = tbl_facture_paye.getValueAt(i, 1).toString();
            //txt_recherche_fac_p.setText(val);
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from entre where id_facture = '"+val+"'");
            while (fr.next()) {
                model_detail.addRow(new Object[]{fr.getString("designation_produit"),fr.getString("qte_recu"),
                    fr.getString("unite"), fr.getString("prixTotal")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        jTable1.setModel(model_detail); 
        
        try {
            String total;
            int i = tbl_facture_paye.getSelectedRow();
            String val = tbl_facture_paye.getValueAt(i, 1).toString();
            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where id_facture = '"+val+"'");
            fr.next();
            total = fr.getString(1);
            jLabel31.setText(total);
        } catch (SQLException e) {
            System.err.println(e);
        }
   }
    
    
    private void afficher_detail_nonp() {
        
        
        
        try {
            model_detail_nonp.setRowCount(0);

            int i = tbl_facture_non_p.getSelectedRow();
            String val = tbl_facture_non_p.getValueAt(i, 0).toString();
            //txt_recherche_fac_p.setText(val);
            stm = connec_payer.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from entre where id_facture = '"+val+"'");
            while (fr.next()) {
                model_detail_nonp.addRow(new Object[]{fr.getString("designation_produit"),fr.getString("qte_recu"),
                    fr.getString("unite"), fr.getString("prixTotal")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_detail_non_p.setModel(model_detail_nonp);     
        
        try {
            String total;
            int i = tbl_facture_non_p.getSelectedRow();
            String val = tbl_facture_non_p.getValueAt(i, 0).toString();
            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where id_facture = '"+val+"'");
            fr.next();
            total = fr.getString(1);
            totalPrixentre.setText(total);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
    
   }
 
 
//FIN

    
//MOUVEMENT
    
private void afficher_fac_mouv() {
        
        try {
            model.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from facture order by dateAcqui desc");
           
            while (fr.next()) {
                if (fr.getString("etat2").equals("ferme")){
                
                model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Fermée"});
                }else{
                    
                     model.addRow(new Object[]{fr.getString("designation"),
                    fr.getString("fournisseur"), fr.getString("dateAcqui"),fr.getString("datePayemn"),"Ouverte"});
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_facture.setModel(model);
                            
    }
    
    
    
    private void afficher_sortie() {
        
//        String value = (String) comb_categori_select_stock.getSelectedItem();

        try {
            model_sortie.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from sortie order by date desc");
            while (fr.next()) {
                model_sortie.addRow(new Object[]{fr.getString("numero"),fr.getString("designation"),
                    fr.getString("qte_ex"), fr.getString("unite"), fr.getString("date")});
            }tbl_historique.setModel(model_sortie);
        } catch (SQLException e) {
            System.err.println(e);
        }
        
                            
    }
    
    
    
    
//    private void afficher_entre() {
//        
////        String value = (String) comb_categori_select_stock.getSelectedItem();
//
//        try {
//            model_entre.setRowCount(0);
//            stm = conn.obtenirconnexion().createStatement();
//            //ResultSet fr = stm.executeQuery("Select numero,id_facture,designation_produit,qte_recu,unite,prixTotal from entre where id_facture ='"+comb_unit_stock3.getSelectedItem().toString()+"' order by numero desc");
//            if (comb_unit_stock3.getSelectedItem() == "aucune facture"){
//                
//            //comb_unit_stock3.addItem("aucun facture");
//            }else{
//            ResultSet fr = stm.executeQuery("Select numero,id_facture,designation_produit,qte_recu,unite,prixTotal from entre where id_facture ='"+comb_unit_stock3.getSelectedItem().toString()+"' order by numero desc");
//            while (fr.next()) {
//                model_entre.addRow(new Object[]{fr.getString(1),fr.getString(3), fr.getString(4), fr.getString(5),
//                    fr.getString(6)});
//                //jLabel36.setText(fr.getString(2));
//            }tbl_entre.setModel(model_entre);
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//                            
//    }
    
    
    
    
//    private void update() {
//    if(comb_unit_stock4.getItemCount() == 0){
//    jComboBox1.setSelectedItem("Tous");
//    }else{
//        
//        try {
//            String e = comb_unit_stock4.getSelectedItem().toString();
//            stm = conn.obtenirconnexion().createStatement();
//            ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
//            while (fr.next()) {
//                
//                txt_prixunit_ariv.setText(fr.getString(1));
//                    jLabel26.setText(fr.getString(2));
//                    jLabel29.setText(fr.getString(3));
//                    jLabel33.setText(fr.getString(3));
//                
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//    }
//    }
    
    
    
    
    
    private void update_1() {
    if(com_prod_entre.getItemCount() == 0){
    
    
    
    }else{
        
        try {
            
            String e = com_prod_entre.getSelectedItem().toString();
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
            while (fr.next()) {
                
                txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));
                
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    }
    
    //supprime la facture fermé dans la combobox ID facture
//    private void update_2() {
//        
//        int ab = comb_unit_stock3.getSelectedIndex();
//        comb_unit_stock3.removeItemAt(ab);
//
//    
//    }
    
    
  //deplacement dans le combobox ID de la facture
//    private void update_3() {
//        

    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        afficher();
        //update_combo_categori();
        
        txt_des_stock.setText("");
        txt_qteAct_stock.setText("");
        txt_qteAlert_stock.setText("");
        txt_prixunit_prod.setText("");

        
//        btn_ajout_stock.setVisible(true);
//        btn_modif_stock.setVisible(false);
//        btn_suppr_stock.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_ajout_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_stockActionPerformed

        String designation = txt_des_stock.getText();
        String qte_act = txt_qteAct_stock.getText();
        String qte_alert = txt_qteAlert_stock.getText();
        String unite = (String) comb_unit_stock.getSelectedItem();
        Float prixunit = parseFloat(txt_prixunit_prod.getText());
        String categori = (String) comb_categ_stock.getSelectedItem();

        String requete = "insert into produit(designation,qte_actuel,qte_alert,unite,prixunit,categori)VALUES('" 
                + designation + "','" + qte_act + "','" + qte_alert + "','" + unite + "','" + prixunit + "','" + categori + "')";
        try{

            if (txt_des_stock.getText().length() == 0 || txt_des_stock.getText().length() == 0 || txt_qteAct_stock.getText().length() == 0 || txt_qteAlert_stock.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Veuillez completer tous les champs!");
            }

            else   {
                String des = txt_des_stock.getText();
                ResultSet fr = stm.executeQuery("Select * from produit where designation='"+des+"'");

                if (fr.next()) {

                    JOptionPane.showMessageDialog(null, des+" existe déjà!");

                }else {try {
                    stm.executeUpdate(requete);
                    JOptionPane.showMessageDialog(null, "Produit ajouté");
                    
                    txt_des_stock.setText("");
                    txt_qteAct_stock.setText("");
                    txt_qteAlert_stock.setText("");
                    txt_prixunit_prod.setText("");
                    afficher();

                } catch (HeadlessException | SQLException ex)   {
                    JOptionPane.showMessageDialog(null, "erreur 1");
                }}

            }afficher();//update_combo_categori();
        }catch (HeadlessException | SQLException ex) {
        }
    }//GEN-LAST:event_btn_ajout_stockActionPerformed

    private void btn_modif_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modif_stockActionPerformed

        if(txt_des_stock.getText().length() == 0){ JOptionPane.showMessageDialog(null, "Séléctionner le produit à modifier!"); }
        else{
            try {
                String b = txt_des_stock.getText();
                if (JOptionPane.showConfirmDialog(null, "modification sur le produit: "+b , "modification",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                stm.executeUpdate("UPDATE produit SET qte_actuel='" + txt_qteAct_stock.getText()
                    + "',qte_alert='" + txt_qteAlert_stock.getText() + "',unite='" + comb_unit_stock.getSelectedItem().toString()
                    + "',prixunit='" + parseFloat(txt_prixunit_prod.getText())+ "',categori='" + comb_categ_stock.getSelectedItem()
                    + "' WHERE designation='" + b+"'");
                afficher();//update_combo_categori();

                txt_des_stock.setText("");
                txt_qteAct_stock.setText("");
                txt_qteAlert_stock.setText("");
                txt_prixunit_prod.setText("");
                
            
//            btn_ajout_stock.setVisible(true);
////            btn_modif_stock.setVisible(false);
////            btn_suppr_stock.setVisible(false);
//            txt_des_stock.setEditable(true);

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
        }
    }//GEN-LAST:event_btn_modif_stockActionPerformed

    private void btn_suppr_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suppr_stockActionPerformed
        if (tbl_stock.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Séléctionnez le produit à supprimer");
        }else{

        int i = tbl_stock.getSelectedRow();
        String id = tbl_stock.getValueAt(i, 0).toString();
        String des = tbl_stock.getValueAt(i, 1).toString();

        if(id.length() == 0){JOptionPane.showMessageDialog(null, "veuillez séléctionner le produit à supprimer");}else {
            try {
                if (JOptionPane.showConfirmDialog(null, "voulez-vous vraiment supprimer: "+id+" ?" , "supprimer le produit",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                if (id.length() != 0) {
                    stm.executeUpdate("Delete From produit where id = '" + id+"'");
                    stm.executeUpdate("Delete From produit_fourni where designation = '" + des+"'");
                    stm.executeUpdate("Delete From entre where etat = 'ouvert' && designation_produit ='"+des+"'");

                    JOptionPane.showMessageDialog(null, "\""+id +"\" supprimé de la liste!");
                    afficher();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplire le champ Numero");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        }
        
        }
    }//GEN-LAST:event_btn_suppr_stockActionPerformed

    private void btn_annul_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annul_stockActionPerformed

        txt_des_stock.setText("");
        txt_qteAct_stock.setText("");
        txt_qteAlert_stock.setText("");
        txt_prixunit_prod.setText("");
        
        btn_ajout_stock.setVisible(true);
        btn_modif_stock.setVisible(false);
        btn_suppr_stock.setVisible(false);
    }//GEN-LAST:event_btn_annul_stockActionPerformed

    private void btn_recherch_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_stockActionPerformed
        if (txt_recherch_stock.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun resultat");
        }
        else {

            try{
                model_stock.setRowCount(0);
                ResultSet fr = stm.executeQuery("Select * from produit where designation like '%"+txt_recherch_stock.getText()+"%'");
                while (fr.next()) {
                    model_stock.addRow(new Object[]{fr.getString("id"),fr.getString("designation"),
                        fr.getString("qte_actuel"), fr.getString("qte_alert"),
                        fr.getString("unite"), fr.getString("prixunit"), fr.getString("categori")});
                
                }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        
        if (model_stock.getRowCount() == 0){
        JOptionPane.showMessageDialog(null, "Aucun résultat");
        //tbl_stock.setModel(model_stock);
        }else{
        tbl_stock.setModel(model_stock);
        }
        }
    }//GEN-LAST:event_btn_recherch_stockActionPerformed

    private void txt_recherch_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recherch_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recherch_stockActionPerformed

    private void tbl_stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_stockMouseClicked
       try {
            int i = tbl_stock.getSelectedRow();
            txt_des_stock.setEditable(false);
            //txt_des_stock.setText(model_stock.getValueAt(i, 0).toString());
            
            
            selected = model_stock.getValueAt(i, 0).toString();
            
            
//            txt_qteAct_stock.setText(model_stock.getValueAt(i, 1).toString());
//            txt_qteAlert_stock.setText(model_stock.getValueAt(i, 2).toString());
//            comb_unit_stock.setSelectedItem(model_stock.getValueAt(i, 3).toString());
//            txt_prixunit_prod.setText(model_stock.getValueAt(i, 4).toString());
//            
//            comb_categ_stock.setSelectedItem(model_stock.getValueAt(i, 5).toString());

            
            
//            btn_ajout_stock.setVisible(false);
//            btn_modif_stock.setVisible(true);
//            btn_suppr_stock.setVisible(true);
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "erreur de deplacement" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_tbl_stockMouseClicked

    private void btn_suppr_stock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suppr_stock1ActionPerformed
 
        if(tbl_fournisseur.getSelectedRow() == -1){JOptionPane.showMessageDialog(null, "veuillez séléctionner le fournisseur à supprimer");
        
        }else {
            try {
                int val = tbl_fournisseur.getSelectedRow();
                String design = tbl_fournisseur.getValueAt(val, 1).toString();
                
                if (JOptionPane.showConfirmDialog(null, "voulez-vous vraiment supprimer: "+design+" ?" , "supprimer le fournisseur",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
               
                    stm.executeUpdate("Delete From fournisseur where designation = '" +design+"'");
                    stm.executeUpdate("Delete From produit_fourni where fournisseur = '" +design+"'");
                    
                    JOptionPane.showMessageDialog(null, "\""+design +"\" supprimé de la liste!");
                    afficher_fournisseur();

                }
            } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_suppr_stock1ActionPerformed

    private void btn_recherch_fournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_fournisseurActionPerformed
        if (txt_recherch_stock1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun resultat");
            }
        else {

            try{
                model_tbl_fournisseur.setRowCount(0);
                ResultSet fr = stm.executeQuery("Select * from fournisseur where designation like '%"+txt_recherch_stock1.getText()+"%'");
                while (fr.next()) {
                    model_tbl_fournisseur.addRow(new Object[]{fr.getString("id"),
                        fr.getString("designation"), fr.getString("tel"), fr.getString("mail"),fr.getString("adress")});}
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        tbl_fournisseur.setModel(model_tbl_fournisseur);
        txt_recherch_stock1.setText("");

        }
    }//GEN-LAST:event_btn_recherch_fournisseurActionPerformed

    private void txt_recherch_stock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recherch_stock1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recherch_stock1ActionPerformed

    private void tbl_fournisseurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_fournisseurMouseClicked
        
        
        try {
            int i = tbl_fournisseur.getSelectedRow();
           
            selected_fournisseur = model_tbl_fournisseur.getValueAt(i, 0).toString();
            
            
                    

            
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "erreur de deplacement" + e.getLocalizedMessage());
        }
        
        
    }//GEN-LAST:event_tbl_fournisseurMouseClicked

    private void tbl_facture_non_pMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_facture_non_pMouseClicked
        afficher_detail_nonp();
    }//GEN-LAST:event_tbl_facture_non_pMouseClicked

    private void btn_recherch_fac_non_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_fac_non_pActionPerformed

        String val = comb_option_recherch_fac_non_p.getSelectedItem().toString();
        if (val.equals("Date d'acquisition")){

            if (txt_recherche_fac_non_p.getText().equals("")) {
                //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                afficher_nonp();}
            else {

                try{
                    model_nonp.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from facture where dateAcqui like '%"+txt_recherche_fac_non_p.getText()+"%'");
                    while (fr.next()) {

                        model_nonp.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                            fr.getString("dateAcqui"), fr.getString("datePayemn")});
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            tbl_facture_non_p.setModel(model_nonp);
        }

        }else if (val.equals("Date de payement")){

            if (txt_recherche_fac_non_p.getText().equals("")) {
                //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                afficher_nonp();}
            else {

                try{
                    model_nonp.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from facture where datePayemn like '%"+txt_recherche_fac_non_p.getText()+"%'");
                    while (fr.next()) {

                        model_nonp.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                            fr.getString("dateAcqui"), fr.getString("datePayemn")});
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            tbl_facture_non_p.setModel(model_nonp);
        }

        }else if (val.equals("ID")){

            if (txt_recherche_fac_non_p.getText().equals("")) {
                //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                afficher_nonp();}
            else {

                try{
                    model_nonp.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from facture where designation like '%"+txt_recherche_fac_non_p.getText()+"%'");
                    while (fr.next()) {

                        model_nonp.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                            fr.getString("dateAcqui"), fr.getString("datePayemn")});
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            tbl_facture_non_p.setModel(model_nonp);
        }

        }else if (val.equals("Fournisseur")){

            if (txt_recherche_fac_non_p.getText().equals("")) {
                //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                afficher_nonp();}
            else {

                try{
                    model_nonp.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select * from facture where designation like '%"+txt_recherche_fac_non_p.getText()+"%'");
                    while (fr.next()) {

                        model_nonp.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                            fr.getString("dateAcqui"), fr.getString("datePayemn")});
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            tbl_facture_non_p.setModel(model_nonp);
        }

        }

    }//GEN-LAST:event_btn_recherch_fac_non_pActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if(tbl_facture_non_p.getSelectedRow() == -1){

            JOptionPane.showMessageDialog(null, "Selectionner la facture!");

        }else{
            int i = tbl_facture_non_p.getSelectedRow();
            String val = tbl_facture_non_p.getValueAt(i, 0).toString();
            String date = null;
            try {
                ResultSet fr = stm.executeQuery("Select now()");
                fr.next();
                date = fr.getString(1);
            } catch (SQLException e) {
                System.err.println(e);
            }

            if (JOptionPane.showConfirmDialog(null, "Payement du facture n° "+val+"\nConfirmer?" , "Payement",
                JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

            try {
                stm.executeUpdate("UPDATE facture SET etat='oui',datePayemn = '"+date+"' where designation = '"+val+"'");
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        afficher_payer();
        afficher_nonp();

        //        if(tbl_facture_non_p.getSelectedRow() == 0){
            //        afficher_payer();
            //
            //        }else{
            //        afficher_detail_nonp();
            //        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_facture_payeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_facture_payeMouseClicked
        afficher_detail();
    }//GEN-LAST:event_tbl_facture_payeMouseClicked

    private void btn_recherch_fac_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_fac_pActionPerformed

        String val = comb_option_recherch_fac_p.getSelectedItem().toString();
        switch (val) {
            case "Date d'acquisition":
                if (txt_recherche_fac_p.getText().equals("")) {
                    //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                    afficher_payer();}
                else {
                    
                    try{
                        model_payer.setRowCount(0);
                        ResultSet fr = stm.executeQuery("Select * from facture where dateAcqui like '%"+txt_recherche_fac_p.getText()+"%'");
                        while (fr.next()) {
                            
                            model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                                fr.getString("dateAcqui"), fr.getString("datePayemn")});
                        }
                    }catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    tbl_facture_paye.setModel(model_payer);
                }       break;
            case "Date de payement":
                if (txt_recherche_fac_p.getText().equals("")) {
                    //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                    afficher_payer();}
                else {
                    
                    try{
                        model_payer.setRowCount(0);
                        ResultSet fr = stm.executeQuery("Select * from facture where datePayemn like '%"+txt_recherche_fac_p.getText()+"%'");
                        while (fr.next()) {
                            
                            model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                                fr.getString("dateAcqui"), fr.getString("datePayemn")});
                        }
                    }catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    tbl_facture_paye.setModel(model_payer);
                }       break;
            case "ID":
                if (txt_recherche_fac_p.getText().equals("")) {
                    //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                    afficher_payer();}
                else {
                    
                    try{
                        model_payer.setRowCount(0);
                        ResultSet fr = stm.executeQuery("Select * from facture where designation like '%"+txt_recherche_fac_p.getText()+"%'");
                        while (fr.next()) {
                            
                            model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                                fr.getString("dateAcqui"), fr.getString("datePayemn")});
                        }
                    }catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
                    tbl_facture_paye.setModel(model_payer);
                }       break;
            case "Fournisseur":
                if (txt_recherche_fac_p.getText().equals("")) {
                    //JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
                    afficher_payer();}
                else {
                    
                    try{
                        model_payer.setRowCount(0);
                        ResultSet fr = stm.executeQuery("Select * from facture where designation like '%"+txt_recherche_fac_p.getText()+"%'");
                        while (fr.next()) {
                            
                            model_payer.addRow(new Object[]{fr.getString("numero"), fr.getString("designation"),fr.getString("fournisseur"),
                            fr.getString("dateAcqui"), fr.getString("datePayemn")});
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
            tbl_facture_paye.setModel(model_payer);
        }       break;
        }

    }//GEN-LAST:event_btn_recherch_fac_pActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        afficher_payer();
        afficher_nonp();
        jTable1.setModel(model_defaut);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_factureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_factureMouseClicked
        int val = tbl_facture.getSelectedRow();
         selected_facture = tbl_facture.getValueAt(val, 0).toString();

    }//GEN-LAST:event_tbl_factureMouseClicked

    private void btn_suppr_facActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suppr_facActionPerformed
        if (tbl_facture.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Séléctionnez la facture à supprimer");
        }else{ 
            

            int val = tbl_facture.getSelectedRow();
        String des = tbl_facture.getValueAt(val, 0).toString();
        String etat = "";
        
        Statement stm5;
            try {
                stm5 = conn.obtenirconnexion().createStatement();
                ResultSet fr5 = stm5.executeQuery("Select etat2 from facture where designation = '"+des+"' ");
                fr5.next();
                etat = fr5.getString(1);
                
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            
        if (etat.equals("ferme")){
            JOptionPane.showMessageDialog(null, "Cette facture est déja fermée!");
            
        }else{ 
        
        try {
                if (JOptionPane.showConfirmDialog(null, "voulez-vous vraiment supprimer la facture: "+des+" ?\nNB: Cette action affectera la quantité des produits au stock" , "supprimer",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                
                    
                    
                    Statement stm4 = conn.obtenirconnexion().createStatement();
                    ResultSet fr4 = stm4.executeQuery("Select id_facture,designation_produit,qte_recu from entre where id_facture = '"+des+"' ");
                    
                    while (fr4.next()){
                    stm.executeUpdate("Update produit set qte_actuel = qte_actuel - '"+fr4.getFloat(3)+"' where designation = '"+fr4.getString("designation_produit")+"'");
                    }
                    
                    stm.executeUpdate("Delete From entre where id_facture = '" + des+"'");
                    stm.executeUpdate("Delete From facture where designation = '" + des+"'");
                    
                    JOptionPane.showMessageDialog(null, "\""+des +"\" supprimé de la liste et de la base de donnée");
                    afficher_fac_mouv();
                    big_update();
                    

                
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        
        
            
        }
        
        }

    }//GEN-LAST:event_btn_suppr_facActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //deplace_combobox_facture_entrer();
        afficher_fac_mouv();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void comb_unit_stock5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comb_unit_stock5ItemStateChanged

        if (comb_unit_stock5.getItemCount() == 0){

        }else{

            try {
                stm = conn.obtenirconnexion().createStatement();
                String a = comb_unit_stock5.getSelectedItem().toString();
                ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                fc.next();
                jLabel40.setText(fc.getString(1));
                jLabel41.setText(fc.getString(2));
                jLabel43.setText(fc.getString(2));
            } catch (SQLException e) {
                System.err.println(e);
            }

        }

    }//GEN-LAST:event_comb_unit_stock5ItemStateChanged

    private void comb_unit_stock5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_unit_stock5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_unit_stock5ActionPerformed

    private void btn_ajout_stock3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_stock3ActionPerformed

        String date = null;
        try {
            ResultSet fr = stm.executeQuery("Select now()");
            fr.next();
            date = fr.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }
       

        try{

            if (comb_unit_stock5.getItemCount() == 0 || date.length() == 0 || txt_qteAlert_stock3.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Veuillez completer tous les champs necessaire!");
            }

            else   {
                String designation_produit = null;
                
                        try {
                            ResultSet fr = stm.executeQuery("Select id from produit where designation = '"+com_prod_entre.getSelectedItem().toString()+"'");
                            fr.next();
                            designation_produit = fr.getString("id");
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                
                
                String designation = comb_unit_stock5.getSelectedItem().toString();
                String qte_ex = txt_qteAlert_stock3.getText();
                String unite = jLabel43.getText();
                //String id = jLabel37.getText();
                float a = parseFloat(qte_ex);
                String requete = "insert into sortie(designation,qte_ex,unite,date,id_produit)VALUES('" + designation + "','" + qte_ex + "','"
                + unite + "','" + date + "','"+designation_produit+"')";
                
                //String requete3 = "Select qte_actuel from produit where qte_actuel >= '"+a+"' && designation='"+designation+"'";
                String requete2 = "Update produit set qte_actuel = qte_actuel -'"+a+"' where designation='"+designation+"'";

                
                try {
                    stm.executeUpdate(requete);

                    JOptionPane.showMessageDialog(null, qte_ex+" "+unite+" de "+designation+" exclue du stock!");
                    txt_qteAlert_stock3.setText("");
                    

                    afficher_sortie();

                } catch (HeadlessException | SQLException ex)   {
                    JOptionPane.showMessageDialog(null, "erreur 1");
                }

                try {
                    stm.executeUpdate(requete2);

                } catch (HeadlessException | SQLException ex)   {
                    JOptionPane.showMessageDialog(null, "erreur 2");
                }

            }
        }catch (HeadlessException ex) {

        }

        //mise à jour du combobox produit sorti

        if (comb_unit_stock5.getItemCount() == 0){

        }else{

            try {

                stm = conn.obtenirconnexion().createStatement();
                String a = comb_unit_stock5.getSelectedItem().toString();
                ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                
                fc.next();
          
                jLabel40.setText(fc.getString(1));
                jLabel41.setText(fc.getString(2));
                jLabel43.setText(fc.getString(2));
                
            } catch (SQLException e) {
                System.err.println(e);
            }

        }

    }//GEN-LAST:event_btn_ajout_stock3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        afficher_payer();
        afficher_nonp();
        tbl_detail_non_p.setModel(model_defaut);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        afficher_fournisseur();

//        txt_des_fourni.setText("");
//        txt_tel_fourni.setText("");
//        txt_mail_fourni.setText("");
//        txt_adrs_fourni.setText("");
//
//        txt_des_fourni.setEditable(true);
//        btn_suppr_stock1.setVisible(false);
//        btn_modif_stock1.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
                com_fourni_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from fournisseur");
                while (fr.next()) {
                    com_fourni_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_fourni_recherch.setModel(com_fourni_rech);
            
            
            try {
                com_prod_rech.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from produit");
                while (fr.next()) {
                    com_prod_rech.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            combo_produit_recherch.setModel(com_prod_rech);
            
            afficher_prod_fourni();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void tbl_recherchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_recherchMouseClicked
        
        int i = tbl_recherch.getSelectedRow();
        String fourni = tbl_recherch.getValueAt(i, 0).toString();
        String prod = tbl_recherch.getValueAt(i, 1).toString();
        
        
        
        
    }//GEN-LAST:event_tbl_recherchMouseClicked

    private void btn_recherch_prodfourniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_prodfourniActionPerformed
        
        fichefourni p=new fichefourni();
        p.setVisible(true);
        
    }//GEN-LAST:event_btn_recherch_prodfourniActionPerformed

    private void btn_ajout_prodfourniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_prodfourniActionPerformed
        
        if (combo_fourni_recherch.getSelectedItem().equals("") || combo_produit_recherch.getSelectedItem().equals("")){
        
            JOptionPane.showMessageDialog(null, "Completez les information necessaire!");
        }else{
            
            
            
            String fournisseur = combo_fourni_recherch.getSelectedItem().toString();
            String produit = combo_produit_recherch.getSelectedItem().toString();
            
            ResultSet zf = null;
            try {
                zf = stm.executeQuery("Select * from produit_fourni where designation='"+produit+"' && fournisseur='"+fournisseur+"'");
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if (zf.next()) {
                    JOptionPane.showMessageDialog(null, "Dejà dans la liste");
                }else{
                
                
            
                    
            String num = null;
            String mail = null;
            String adrs = null;
                                        try {
                                        ResultSet fr = stm.executeQuery("Select tel,mail,adress from fournisseur where designation='"+fournisseur+"' ");
                                        fr.next();
                                        num = fr.getString(1);
                                        mail = fr.getString(2);
                                        adrs = fr.getString(3);
                                        } catch (SQLException e) {
                                            System.err.println(e);
                                        }
            String id_p = null;
            String id_f = null;
            try {
                 ResultSet fr = stm.executeQuery("Select id from produit where designation='"+combo_produit_recherch.getSelectedItem().toString()+"' ");
                 fr.next();
                 id_p = fr.getString(1);    
             } catch (SQLException e) {
              System.err.println(e);
             }
            
             try {
                 ResultSet fr = stm.executeQuery("Select id from fournisseur where designation='"+combo_fourni_recherch.getSelectedItem().toString()+"' ");
                 fr.next();
                 id_f = fr.getString(1);    
             } catch (SQLException e) {
              System.err.println(e);
             }

            String requete = "insert into produit_fourni(designation,fournisseur,num,mail,adrs,id_produit,id_fournisseur)VALUES('" 
                + produit + "','" + fournisseur + "','" + num + "','" + mail + "','" + adrs + "','" + id_p + "','" + id_f + "')";
            
            try {            
                                stm.executeUpdate(requete);
                                JOptionPane.showMessageDialog(null, "Effectué!");
                                afficher_prod_fourni();

                            } catch (HeadlessException | SQLException ex)   {
                                JOptionPane.showMessageDialog(null, "erreur 1");
                                                                            }
                
                
                
                
                
                
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
            
        }
        
    }//GEN-LAST:event_btn_ajout_prodfourniActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (comb_unit_stock5.getItemCount() == 0){

        }else{

            try {
                stm = conn.obtenirconnexion().createStatement();
                String a = comb_unit_stock5.getSelectedItem().toString();
                ResultSet fc = stm.executeQuery("Select qte_actuel,unite from produit where designation= '"+a+"'");
                fc.next();
                //jLabel37.setText(fc.getString(1));
                jLabel40.setText(fc.getString(1));
                jLabel41.setText(fc.getString(2));
                jLabel43.setText(fc.getString(3));
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
//        if (txt_date_evaluation.getText().equals("")){
//        
//        
//        }else{
//        
//        String designation_produit = com_designation_produit_evaluation.getSelectedItem().toString();
//        String date = txt_date_evaluation.getText();
//        String nbr_commande = null;
//        Float qte_total = null;
//        String unite = null;
//        String prixtot = null;
//        try {
//            ResultSet fr = stm.executeQuery("Select count(*) from entre where designation_produit='"+designation_produit+"' && date like '%"+txt_date_evaluation.getText()+"%' ");
//            if (fr.next() == false){
//            
//            }else{
//            //fr.next();
//            nbr_commande = fr.getString(1);
//            txt_nbr_cmd.setText(nbr_commande);
//            }
//            
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        
//        try {
//            ResultSet fr = stm.executeQuery("Select sum(qte_ex) from sortie where designation='"+designation_produit+"' && date like '%"+txt_date_evaluation.getText()+"%'");
//            if (fr.next() == false){
//            
//            }else{
//            //fr.next();
//            qte_total = fr.getFloat(1);
//            qte_consom.setText(qte_total.toString());
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        try {
//            ResultSet fr = stm.executeQuery("Select unite from sortie where designation='"+designation_produit+"' && date like '%"+txt_date_evaluation.getText()+"%'");
//            if (fr.next() == false){
//            
//            }else{
//            //fr.next();
//            unite = fr.getString(1);
//            unite_evaluation.setText(unite);
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        
//        try {
//            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where designation_produit='"+designation_produit+"' && date like '%"+txt_date_evaluation.getText()+"%'");
//            if (fr.next() == false){
//            
//            }else{
//            //fr.next();
//            prixtot = fr.getString(1);
//            txt_prixtot_ev.setText(prixtot);
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        try {
//            
//            String total = null;
//            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where date like '%"+txt_date_evaluation.getText()+"%'");
//            if (fr.next() == false){
//            
//            }else{
//            //fr.next();
//            total = fr.getString(1);
//            txt_somm_eval.setText(total);
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        
//        }
           
                
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btn_actu_evalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actu_evalActionPerformed
//        try {
//                com_prod_evaluation.removeAllElements();
//                stm = conn.obtenirconnexion().createStatement();
//                ResultSet fr = stm.executeQuery("Select designation from produit");
//                while (fr.next()) {
//                    com_prod_evaluation.addElement(fr.getString(1));
//                }
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//            com_designation_produit_evaluation.setModel(com_prod_evaluation);
    }//GEN-LAST:event_btn_actu_evalActionPerformed

    private void btn_imprim_factur_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprim_factur_pActionPerformed
        
        int i = tbl_facture_paye.getSelectedRow();
        String designation = tbl_facture_paye.getValueAt(i, 1).toString();
        String dateAcqui = tbl_facture_paye.getValueAt(i, 3).toString();
        String datepayemn = tbl_facture_paye.getValueAt(i, 4).toString();
        String total = null;
        
        try{
                    //model_payer.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where id_facture ="+designation+"");
                    if(fr.next()) {
                    
                    total = fr.getString(1);

                    
                }else{}
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        
        
        
        
        MessageFormat header = new MessageFormat("Facture: "+designation+"\n        Total = "+total+"");
        MessageFormat footer = new MessageFormat("Acquise le: "+dateAcqui+"\n       Payée le: "+datepayemn+"\n");

        try {
            jTable1.print(JTable.PrintMode.NORMAL, header,  footer);
            
        }catch (java.awt.print.PrinterException e){
            System.err.format("Erreur d'impression: %s%n", e.getMessage());
        }
        
        
    }//GEN-LAST:event_btn_imprim_factur_pActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        int i = tbl_facture_non_p.getSelectedRow();
        String designation = tbl_facture_non_p.getValueAt(i, 1).toString();
        String dateAcqui = tbl_facture_non_p.getValueAt(i, 3).toString();
        String datepayemn = tbl_facture_non_p.getValueAt(i, 4).toString();
        String total = null;
        
        try{
                    //model_payer.setRowCount(0);
                    ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where id_facture ="+designation+"");
                    if(fr.next()) {
                    
                    total = fr.getString(1);

                    
                }else{}
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        
        
        
        
        MessageFormat header = new MessageFormat("Facture: "+designation+"\n        Total = "+total+"");
        MessageFormat footer = new MessageFormat("Acquise le: "+dateAcqui+"\n       Date limite: "+datepayemn+"\n");

        try {
            tbl_detail_non_p.print(JTable.PrintMode.NORMAL, header,  footer);
            
        }catch (java.awt.print.PrinterException e){
            System.err.format("Erreur d'impression: %s%n", e.getMessage());
        }
        
        
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (com_prod_entre.getSelectedItem() == null){
        
        
        }else{
        try {
            String e = com_prod_entre.getSelectedItem().toString();
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
            while (fr.next()) {

                txt_prixunit_ariv.setText(fr.getString(1));
                jLabel26.setText(fr.getString(2));
                jLabel29.setText(fr.getString(3));
                jLabel33.setText(fr.getString(3));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        //update_3();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_ajout_stock2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_stock2ActionPerformed
        selected_index_entre = comMouvEntreFacture.getSelectedIndex();
        
        if (comMouvEntreFacture.getSelectedItem() == null){
          JOptionPane.showMessageDialog(null, "Aucune facture ouverte!\nVeuillez créer une facture dans le menu \"Facture\".");
          
        }else{
        
        
        String id_facture = comMouvEntreFacture.getSelectedItem().toString();
        String designation_prod = com_prod_entre.getSelectedItem().toString();
        String qte_recu = txt_qteAlert_stock2.getText();
        String unite = jLabel33.getText();
        Float prixtotal;

        String date = null;
        try {
            ResultSet fr = stm.executeQuery("Select now()");
            fr.next();
            date = fr.getString(1);
        } catch (SQLException e) {
            System.err.println(e);
        }

        //        String requete = "insert into entre(id_facture,id_produit,designation_produit,qte_recu,unite,prixtotal)VALUES('"
        //        + id_facture + "','" + designation_prod + "','" + qte_recu + "','" + unite + "','" + prixtotal + "')";
        try{

            if (txt_qteAlert_stock2.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Veuillez completer tous les champs necessaires!");
            }

            else {
                //selected_index_entre = comMouvEntreFacture.getSelectedIndex();
                String designation_produit = null;
                
                        try {
                            ResultSet fr = stm.executeQuery("Select id from produit where designation = '"+com_prod_entre.getSelectedItem().toString()+"'");
                            fr.next();
                            designation_produit = fr.getString("id");
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                

                try {

                    prixtotal = parseFloat(txt_prixunit_ariv.getText()) * parseFloat(txt_qteAlert_stock2.getText());
                    String requete = "insert into entre(id_facture,designation_produit,qte_recu,unite,prixtotal,date,etat,id_produit)"
                            + "VALUES('" + id_facture + "','" + designation_prod + "','" + qte_recu + "','" + unite + "','" + prixtotal + "','" + date + "','ouvert','"+designation_produit+"')";
                    stm.executeUpdate(requete);
                    JOptionPane.showMessageDialog(null, "Produit ajouté");


                } catch (HeadlessException | SQLException ex)   {
                    JOptionPane.showMessageDialog(null, "erreur 1");
                }
                
                float j=0,k,l=0;

                try {
                    ResultSet as = stm.executeQuery("Select qte_actuel from produit where designation ='"+com_prod_entre.getSelectedItem().toString()+"'");
                    as.next();
                    j = as.getFloat(1);
                } catch (SQLException e) {
                    System.err.println(e);
                }

                try {
                    ResultSet ar = stm.executeQuery("Select qte_recu from entre where designation_produit ='"+com_prod_entre.getSelectedItem().toString()+"' order by numero desc");
                    ar.next();
                    l = ar.getFloat(1);
                } catch (SQLException e) {
                    System.err.println(e);
                }

                k = l + j;

                try {
                    stm.executeUpdate("UPDATE produit SET qte_actuel='"+k+"' where designation = '"+com_prod_entre.getSelectedItem().toString()+"'");
                    //update();

                    JOptionPane.showMessageDialog(null, "Quantité actuel du produit: "+com_prod_entre.getSelectedItem()+" = "+k+" " +jLabel33.getText()+"");
                } catch (SQLException e) {
                    System.err.println(e);
                }

                try {
                    String e = com_prod_entre.getSelectedItem().toString();
                    stm = conn.obtenirconnexion().createStatement();
                    ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                    while (fr.next()) {

                        txt_prixunit_ariv.setText(fr.getString(1));
                        jLabel26.setText(fr.getString(2));
                        jLabel29.setText(fr.getString(3));
                        jLabel33.setText(fr.getString(3));

                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
                comMouvEntreFacture.setSelectedIndex(selected_index_entre);
                afficher_entre();
                
                comMouvEntreFacture.setSelectedIndex(selected_index_entre);
            }

        }catch (HeadlessException ex)   {
            JOptionPane.showMessageDialog(null, "erreur 1");
        }
        }txt_qteAlert_stock2.setText("");
    }//GEN-LAST:event_btn_ajout_stock2ActionPerformed

    private void com_prod_entreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_prod_entreActionPerformed
        if (com_prod_entre.getItemCount() == 0){

        }else{
            try {
                String f = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+f+"'");
                while (fr.next()) {
                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
    }//GEN-LAST:event_com_prod_entreActionPerformed

    private void com_prod_entreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_prod_entreItemStateChanged

        if(com_prod_entre.getItemCount() == 0){

            jLabel26.setText("");
            jLabel29.setText("");
            jLabel33.setText("");

        }else{

            try {
                String e = com_prod_entre.getSelectedItem().toString();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select prixunit,qte_actuel,unite from produit where designation = '"+e+"'");
                while (fr.next()) {

                    txt_prixunit_ariv.setText(fr.getString(1));
                    jLabel26.setText(fr.getString(2));
                    jLabel29.setText(fr.getString(3));
                    jLabel33.setText(fr.getString(3));

                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }//GEN-LAST:event_com_prod_entreItemStateChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        inventaire h = new inventaire();
        h.setVisible(true);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnstockajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstockajoutActionPerformed
        AjoutStock a = new AjoutStock();
        a.setVisible(true);
        
        
        
    }//GEN-LAST:event_btnstockajoutActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        //afficher();
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        //afficher();
    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        afficher();
        afficher_fac_mouv();
        afficher_sortie();
        afficher_prod_fourni();
        
       
        
        if(comMouvEntreFacture.getItemCount() == 0){
            txtMouvEntreID.setText("");
            txtMouvEntreFournisseur.setText("");
            
        }else{
            
        }

        
        
        //DETAIL NON PAYE
        
        model_detail_nonp.setRowCount(0);
        tbl_detail_non_p.setModel(model_detail_nonp);
        
        model_detail.setRowCount(0);
        jTable1.setModel(model_detail);
        
        
        
//        int r = tbl_facture_non_p.getSelectedRow();
//        if(r == -1){
//           
//        }else{
//        
//        try {
//            model_detail_nonp.setRowCount(0);
//
//            int i = tbl_facture_non_p.getSelectedRow();
//            String val = tbl_facture_non_p.getValueAt(i, 0).toString();
//            //txt_recherche_fac_p.setText(val);
//            stm = connec_payer.obtenirconnexion().createStatement();
//            ResultSet fr = stm.executeQuery("Select * from entre where id_facture = '"+val+"'");
//            while (fr.next()) {
//                model_detail_nonp.addRow(new Object[]{fr.getString("designation_produit"),fr.getString("qte_recu"),
//                    fr.getString("unite"), fr.getString("prixTotal")});
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        tbl_detail_non_p.setModel(model_detail_nonp);     
//        
//        try {
//            String total;
//            int i = tbl_facture_non_p.getSelectedRow();
//            String val = tbl_facture_non_p.getValueAt(i, 0).toString();
//            ResultSet fr = stm.executeQuery("Select sum(prixTotal) from entre where id_facture = '"+val+"'");
//            fr.next();
//            total = fr.getString(1);
//            totalPrixentre.setText(total);
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//        
//        }
        //-----------------------------------------------------------
        
        
        
        
        
        
        
        txt_date_evaluation.setText("yyyy-mm-dd");
        txt_date_evaluation.setForeground(java.awt.Color.GRAY);
        
        
        jButton16.setEnabled(false);
        
        //MISE A JOUR COMBOBOX FACTURE DANS MENU MOUVEMENT DE STOCK>ENTREE
        
        

        try {
                com_entre_facture.removeAllElements();
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select designation from facture where etat2='ouvert'");
                while (fr.next()) {
                    com_entre_facture.addElement(fr.getString(1));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }comMouvEntreFacture.setModel(com_entre_facture);
            
         if(comMouvEntreFacture.getItemCount() == 0 || selected_index_entre >= comMouvEntreFacture.getItemCount()){
            
        }else{
        comMouvEntreFacture.setSelectedIndex(selected_index_entre);
        }//selected_index_entre = 0;
        
        
            
        
            
            
            
            
            
        
         try {
            model_tbl_fournisseur.setRowCount(0);
            stm = conn.obtenirconnexion().createStatement();
            ResultSet fr = stm.executeQuery("Select * from fournisseur order by designation");
            while (fr.next()) {
                model_tbl_fournisseur.addRow(new Object[]{fr.getString("id"),fr.getString("designation"), fr.getString("tel"), fr.getString("mail"),
                    fr.getString("adress")});
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        tbl_fournisseur.setModel(model_tbl_fournisseur);

        
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnStockModifProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockModifProdActionPerformed
            if (tbl_stock.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Séléctionner un produit");
                
            }else {
            ModifProduct a = new ModifProduct();
            a.setVisible(true);
            }
       
    }//GEN-LAST:event_btnStockModifProdActionPerformed

    private void comboStockCategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboStockCategoriItemStateChanged
        if (comboStockCategori.getSelectedItem() == "Tous"){
            
            afficher();
            
        }else { String var = comboStockCategori.getSelectedItem().toString();
        
        try {
                model_stock.setRowCount(0);
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from produit where categori = '"+var+"' order by designation");
                    while (fr.next())   {
                        model_stock.addRow(new Object[]{fr.getString("id"),fr.getString("designation"),fr.getString("qte_actuel"), fr.getString("qte_alert"),
                        fr.getString("unite"), fr.getString("prixunit"), fr.getString("categori")});
                                        }
            } catch (SQLException e) {System.err.println(e);}
        
        tbl_stock.setModel(model_stock);
        
            
            
            
        }
        
        
        
        
        
        
    }//GEN-LAST:event_comboStockCategoriItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AjoutFournisseur f = new AjoutFournisseur();
        f.setVisible(true);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            if (tbl_fournisseur.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Séléctionner un fournisseur");
                
            }else {
             int i = tbl_fournisseur.getSelectedRow();
            selected_fournisseur = model_tbl_fournisseur.getValueAt(i, 0).toString();
            ModifFournisseur f = new ModifFournisseur();
            f.setVisible(true);
            }        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        AjoutFacture fac = new AjoutFacture();
        fac.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
        if (tbl_facture.getSelectedRow() == -1){
            
            JOptionPane.showMessageDialog(null, "Selectionnez une facture");
            
        }else{ int val = tbl_facture.getSelectedRow();
        selected_facture = tbl_facture.getValueAt(val, 0).toString();
        ModifFacture fac = new ModifFacture();
        fac.setVisible(true);
            
            
        }
        
        
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btn_recherch_factureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recherch_factureActionPerformed
        if (txt_recherch_facture.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun resultat");
            }
        else {

            try{
                model.setRowCount(0);
                ResultSet fr = stm.executeQuery("Select * from facture where designation like '%"+txt_recherch_facture.getText()+"%'");
                while (fr.next()) {
                    model.addRow(new Object[]{fr.getString("designation"),fr.getString("fournisseur"),
                        fr.getString("dateAcqui"), fr.getString("datePayemn")});}
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());}
        tbl_facture.setModel(model);

        }
        
    }//GEN-LAST:event_btn_recherch_factureActionPerformed

    private void txt_recherch_factureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recherch_factureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recherch_factureActionPerformed

    private void comMouvEntreFactureItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comMouvEntreFactureItemStateChanged
        afficher_entre();
    }//GEN-LAST:event_comMouvEntreFactureItemStateChanged

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (tbl_entre_new.getSelectedRow() == -1){
            
        }else{ int val = tbl_entre_new.getSelectedRow();
        String num = tbl_entre_new.getValueAt(val, 0).toString();
        String produit = tbl_entre_new.getValueAt(val, 1).toString();
        String qte = tbl_entre_new.getValueAt(val, 2).toString();
        String unite = tbl_entre_new.getValueAt(val, 3).toString();
        
        
        try {
                //String b = txtModifProduitID.getText();
                if (JOptionPane.showConfirmDialog(null, "Confirmez l'annulation" , "modification",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                stm.executeUpdate("UPDATE produit SET qte_actuel= qte_actuel -'" + qte + "' WHERE designation='" + produit+"'");
                JOptionPane.showMessageDialog(null, "Succès!");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
        
        
        
        try {
                
                
            try {
                stm.executeUpdate("Delete From entre where numero = '" + num+"'");
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }
                    JOptionPane.showMessageDialog(null, qte+" "+unite+" de "+produit +" supprimé du stock!");
                    afficher_entre();
  
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        
        
            
            
            
            
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
        
        
        if(txtMouvEntreID.getText().equals("") || txtMouvEntreFournisseur.getText().equals("") || comMouvEntreFacture.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Aucune facture");
        }else if( tbl_entre_new.getRowCount() == 0){
            
            JOptionPane.showMessageDialog(null, "Facture vide!\nVeuillez la remplire.");
            
        }else{
        
        
        String fac = comMouvEntreFacture.getSelectedItem().toString();
        
        try {
            
            if (JOptionPane.showConfirmDialog(null, "Une facture fermée ne peut plus être modifiée\n"
                    + "Vous voullez vraiment fermer la facture: "+fac , "modification",
                JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    stm.executeUpdate("UPDATE facture SET etat2 = 'ferme' WHERE designation ='" +fac+"'");
                    stm.executeUpdate("UPDATE entre SET etat = 'ferme' WHERE id_facture ='" +fac+"'");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ModifFournisseur.class.getName()).log(Level.SEVERE, null, ex);
                }
            JOptionPane.showMessageDialog(null, "Succès!");
            big_update();
            afficher_entre();
        }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tbl_historiqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_historiqueMouseClicked
        btnMouvSortiAnnul.setEnabled(true);
        
    }//GEN-LAST:event_tbl_historiqueMouseClicked

    private void comInventaireCategorieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comInventaireCategorieItemStateChanged
        afficher_evaluation();
    }//GEN-LAST:event_comInventaireCategorieItemStateChanged

    private void comInventaireTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comInventaireTypeItemStateChanged
        if (comInventaireType.getSelectedItem() == "Facture"){
            
            comInventaireCategorie.setEnabled(false);
            
            txt_date_evaluation.setText("Date de payement");
            
            
            afficher_evaluation_facture();
            tbl_evaluation.setGridColor(java.awt.Color.pink);
            
        }else{
            txt_date_evaluation.setText("");
            comInventaireCategorie.setEnabled(true);
            txt_date_evaluation.setEditable(true);
            
            model_evaluation.setColumnCount(0);
            model_evaluation.setRowCount(0);
            
            model_evaluation.addColumn("ID");
            model_evaluation.addColumn("Désignation");
            model_evaluation.addColumn("Qté actuelle");
            model_evaluation.addColumn("Qté commandée");
            model_evaluation.addColumn("Prix d'achat");
            model_evaluation.addColumn("Qté consommée");
            
            
            afficher_evaluation();
            tbl_evaluation.setGridColor(java.awt.Color.pink);
            
        }
    }//GEN-LAST:event_comInventaireTypeItemStateChanged

    private void btnMouvSortiAnnulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouvSortiAnnulActionPerformed
        if (tbl_historique.getSelectedRow() == -1){
            
        }else{ int val = tbl_historique.getSelectedRow();
        String num = tbl_historique.getValueAt(val, 0).toString();
        String produit = tbl_historique.getValueAt(val, 1).toString();
        String qte = tbl_historique.getValueAt(val, 2).toString();
        String unite = tbl_historique.getValueAt(val, 3).toString();
        
        
        try {
                //String b = txtModifProduitID.getText();
                if (JOptionPane.showConfirmDialog(null, "Confirmez l'annulation" , "modification",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                stm.executeUpdate("UPDATE produit SET qte_actuel= qte_actuel +'" + qte + "' WHERE designation='" + produit+"'");
                JOptionPane.showMessageDialog(null, "Succès!");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur de modification !" + e.getMessage());
            System.err.println(e);
        }
        
        
        
        try {
                
                
            try {
                stm.executeUpdate("Delete From sortie where numero = '" + num+"'");
            } catch (SQLException ex) {
                Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
            }
                    JOptionPane.showMessageDialog(null, qte+" "+unite+" de "+produit +" réstauré au stock!");
                    afficher_entre();
                    afficher_sortie();
  
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "erreur de suppression \n" + e.getMessage());
        }
        
        
            
            
            
            
        }
        
        
        
        
        
    }//GEN-LAST:event_btnMouvSortiAnnulActionPerformed

    private void tbl_entre_newMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_entre_newMouseClicked
         jButton16.setEnabled(true);
         
    }//GEN-LAST:event_tbl_entre_newMouseClicked

    private void btn_menuPrincialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuPrincialActionPerformed
        login.menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_menuPrincialActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        login.menuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void txt_date_evaluationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_date_evaluationKeyTyped
        if (comInventaireType.getSelectedItem() == "Facture"){
            
            comInventaireCategorie.setEnabled(false);

            afficher_evaluation_facture();
            
            tbl_evaluation.setGridColor(java.awt.Color.pink);
        
        
        }else{
            
            afficher_evaluation();
            
        }
    }//GEN-LAST:event_txt_date_evaluationKeyTyped

    private void txt_date_evaluationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_date_evaluationMouseClicked
        txt_date_evaluation.setText("");
        txt_date_evaluation.setForeground(java.awt.Color.black);

    }//GEN-LAST:event_txt_date_evaluationMouseClicked

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
    private javax.swing.JPanel background_stock;
    private javax.swing.JButton btnMouvSortiAnnul;
    private javax.swing.JButton btnStockModifProd;
    private javax.swing.JButton btn_actu_eval;
    private javax.swing.JButton btn_ajout_prodfourni;
    private javax.swing.JButton btn_ajout_stock;
    private javax.swing.JButton btn_ajout_stock2;
    private javax.swing.JButton btn_ajout_stock3;
    private javax.swing.JButton btn_annul_stock;
    private javax.swing.JButton btn_imprim_factur_p;
    private javax.swing.JButton btn_menuPrincial;
    private javax.swing.JButton btn_modif_stock;
    private javax.swing.JButton btn_recherch_fac_non_p;
    private javax.swing.JButton btn_recherch_fac_p;
    private javax.swing.JButton btn_recherch_facture;
    private javax.swing.JButton btn_recherch_fournisseur;
    private javax.swing.JButton btn_recherch_prodfourni;
    private javax.swing.JButton btn_recherch_stock;
    private javax.swing.JButton btn_suppr_fac;
    private javax.swing.JButton btn_suppr_stock;
    private javax.swing.JButton btn_suppr_stock1;
    private javax.swing.JButton btnstockajout;
    private javax.swing.JComboBox comInventaireCategorie;
    private javax.swing.JComboBox comInventaireType;
    private javax.swing.JComboBox comMouvEntreFacture;
    private javax.swing.JComboBox com_prod_entre;
    private javax.swing.JComboBox comb_categ_stock;
    private javax.swing.JComboBox comb_option_recherch_fac_non_p;
    private javax.swing.JComboBox comb_option_recherch_fac_p;
    private javax.swing.JComboBox comb_unit_stock;
    private javax.swing.JComboBox comb_unit_stock5;
    private javax.swing.JComboBox comboStockCategori;
    private javax.swing.JComboBox combo_fourni_recherch;
    private javax.swing.JComboBox combo_produit_recherch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_detail_non_p;
    private javax.swing.JTable tbl_entre_new;
    private javax.swing.JTable tbl_evaluation;
    private javax.swing.JTable tbl_facture;
    private javax.swing.JTable tbl_facture_non_p;
    private javax.swing.JTable tbl_facture_paye;
    private javax.swing.JTable tbl_fournisseur;
    private javax.swing.JTable tbl_historique;
    private javax.swing.JTable tbl_recherch;
    private javax.swing.JTable tbl_stock;
    private javax.swing.JLabel totalPrixentre;
    private javax.swing.JLabel txtMouvEntreFournisseur;
    private javax.swing.JLabel txtMouvEntreID;
    private javax.swing.JTextField txt_date_evaluation;
    private javax.swing.JTextField txt_des_stock;
    private javax.swing.JLabel txt_prixunit_ariv;
    private javax.swing.JTextField txt_prixunit_prod;
    private javax.swing.JTextField txt_qteAct_stock;
    private javax.swing.JTextField txt_qteAlert_stock;
    private javax.swing.JTextField txt_qteAlert_stock2;
    private javax.swing.JTextField txt_qteAlert_stock3;
    private javax.swing.JTextField txt_recherch_facture;
    private javax.swing.JTextField txt_recherch_stock;
    private javax.swing.JTextField txt_recherch_stock1;
    private javax.swing.JTextField txt_recherche_fac_non_p;
    private javax.swing.JTextField txt_recherche_fac_p;
    private javax.swing.JLabel txt_somm_eval;
    // End of variables declaration//GEN-END:variables
}
