
package com.era.AllCustNo;

/**
 *
 * @author roman
 */

import java.sql.*;
import java.util.*;
import com.era.FingerCheck.*;

public class AllCustNo {
    
    public static List<Integer> custNo(String pStatus ){
        // vTotalCus = 0;
        Connection con = null;
        //String[] arr = new String[]{};
        //ArrayList arrList = new ArrayList();
        List<Integer> listStrCust = new ArrayList<Integer>();
        //List<String> listStrCust = new ArrayList<String>();
         
        try{
            con = DbConnectivity.getConnection();
            CallableStatement cs = con.prepareCall("begin BIOTPL.GET_CUSTOMER_LIST.GET_CUST_CUST_NO(?,?); end;");
            cs.setString(1, pStatus);
            cs.registerOutParameter(2, java.sql.Types.ARRAY, "CUS_NO_LIST");
            cs.execute();
         
            Array arrCusNo = cs.getArray(2);
            
            Map map = con.getTypeMap();
            map.put("CUSTOMER_NO_LIST", Class.forName("com.era.FingerCheck.TestArr"));
           
            Object[] values = (Object[]) arrCusNo.getArray(); /// Customer No. here.
           
           for (int i = 0; i < values.length; i++) {
                TestArr a = (TestArr) values[i];
                listStrCust.add(a.attrOne); 
            }    
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Error is:"+ ex);
            System.out.println("Exception raised at Retrieve Customer No. Error is : "+ ex);
        }
        finally
        {
            DbConnectivity.releaseConnection(con);
        }
         System.out.println("---Total ("+pStatus+") Customer's : "+listStrCust.size());
     return listStrCust;       
    }
    
// main function for testing this class.    
     public static void main(String[] args) {
        List<Integer> listStrCust = AllCustNo.custNo("S");
        System.out.println(listStrCust);        //Old 231, 120, 131, 1559, 42083, 44232, 114114
    }
    
}
//258534