
package com.era.FingerCheck;

/**
 *
 * @author root
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import era.com.propertiesFile.*;

public class DbConnectivity {
  
    public static int a;
    public static Connection getConnection() {
        Connection con = null;
        
        DBInfo dbInfo = GetDBInfo.getDbInfo();
        if(dbInfo == null)
        {
            System.out.println("dbInfo object is null in DbConnectivity class");
        }
        
        String IPAdress = dbInfo.ip;  
        String PORT = dbInfo.portNo;
        String serviceName = dbInfo.serviceName;
        String userName = dbInfo.userName;
        String password = dbInfo.password; 
        String URL = "jdbc:oracle:thin:@" + IPAdress + ":" + PORT + "/" + serviceName + "";
         
        
        try {
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object  
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.11.201.55:1525/iabdb", "BIOTPL", "biotpl");
            con = DriverManager.getConnection(URL, userName, password);//database for xe      
  
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error face at Database connectivity. Error is : "+e);
        }
        return null;
    }
    
    public static  void releaseConnection(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectivity.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception face at Release Database Connectivity. Error is : "+ex);
        }
    }
    
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DbConnectivity.getConnection();
            System.out.println("con = " + con);
        } catch (Exception e) {
            System.out.println("Connection failed. Error is : "+e.getMessage());
        }finally{
            DbConnectivity.releaseConnection(con);
        }
    }
}
