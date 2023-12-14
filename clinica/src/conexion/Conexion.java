/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ale_l
 */
public class Conexion {
    public String db = "clinica";
    public String url = "jdbc:mysql://localhost/" +db+"?zeroDateTimeBehavior=convertToNull";
    public String user = "root";
    public String pass = "";

    Connection link = null;
    
public Connection Conectar()
    {
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            link =DriverManager.getConnection(url, user, pass);
            

        } catch(Exception e){
            link = null;
            JOptionPane.showMessageDialog(null, "Conexion fallida");
            
        }
        return link;
    }

    public Connection getConnection() {
        return link;
    }
}
