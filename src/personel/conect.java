/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personel;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rango
 */
public class conect {
    
    Connection con;
    public conect(){
    try{
    Class.forName("com.mysql.jdbc.Driver");
        
    }catch(ClassNotFoundException e){
        System.err.println(e);
    }
    try{
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/personnel","root","");
    }catch(SQLException e){System.err.println(e);}
    }
    Connection obtenirconnexion(){return con;}
    
}