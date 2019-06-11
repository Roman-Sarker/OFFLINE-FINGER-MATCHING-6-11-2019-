/**
 * 
 * *********************************Unused class
 */
package com.era.sQuery;

import com.era.FingerCheck.DbConnectivity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportOnMatchFinger {
    public static void retriveMatchfinger(){
        Connection con = DbConnectivity.getConnection();
        try{
            
            String sql = "SELECT AGENT_CUST_NO, CUSTOMER_CUST_NO, CUSTOMER_FINGER, AGENT_FINGER, STANDARD FROM FINGER_MATCH_REPORT";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            List <BeanClass> myList = new ArrayList<>();
            while(rs.next())
            {
                BeanClass beanVar= new BeanClass();
                
                beanVar.setCustNo(rs.getInt("CUSTOMER_CUST_NO"));
                beanVar.setAgentNo(rs.getInt("AGENT_CUST_NO"));
                beanVar.setCustFpNo(rs.getString("CUSTOMER_FINGER"));
                beanVar.setAgentFpNo(rs.getString("AGENT_FINGER"));
                beanVar.setStandard("STANDARD");
              
                myList.add(beanVar);
            }
 
        
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally{
            DbConnectivity.releaseConnection(con);
        }
    }
}
