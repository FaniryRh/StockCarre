/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcarre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Stockcarre {

    
    
    
    public static void main(String[] args) {
        try { 
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
}
        
       login p = new login();
       p.setVisible(true);
       
        conect conn = new conect();
        Statement stm;
        ResultSet Rs;
        
      
       
       
       
       
       
       Thread thread = new Thread();
       int i = 1;
       while(i == 1){
           for(int t = 5;t >= 0;t--){
               
               
               
               try {
                   thread.sleep(1000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(appli.class.getName()).log(Level.SEVERE, null, ex);
               }
               
                try {
                stm = conn.obtenirconnexion().createStatement();
                ResultSet fr = stm.executeQuery("Select * from produit where qte_actuel <= qte_alert order by designation");
                if (fr.next()){
                
                if(t == 0){
           notif a = new notif();
           a.setVisible(true);
                    try {
                       thread.sleep(10000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Stockcarre.class.getName()).log(Level.SEVERE, null, ex);
                   }
           a.setVisible(false);
               
           }else{}
                
                
                }else{}
            } catch (SQLException e) {System.err.println(e);}
               
           
           
           }
       
       }
    }
    
}
