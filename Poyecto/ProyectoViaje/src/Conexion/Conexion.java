package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    Connection con;
    Statement st;
    ResultSet rs;
    
    public Connection ConexionLogin (){
        try{
            
            String driver= "net.ucanaccess.jdbc.UcanaccessDriver";
            String url = "jdbc:ucanaccess://C:\\Users\\sebas\\OneDrive\\Documents\\Login.accdb";
            Class.forName(driver);
            con= DriverManager.getConnection(url);  
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos"+ex);
            System.exit(0);
        }
     
        return con;
    }
   
        public Connection ConexionDB (){
        try{
            
            String driver= "net.ucanaccess.jdbc.UcanaccessDriver";
            String url = "jdbc:ucanaccess://C:\\Users\\sebas\\OneDrive\\Documents\\Agencia.accdb";
            Class.forName(driver);
            con= DriverManager.getConnection(url);   
            JOptionPane.showMessageDialog(null, "conectar a la base de datos");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos"+ex);
            System.exit(0);
        }
     
        return con;
    }
}


 
    
