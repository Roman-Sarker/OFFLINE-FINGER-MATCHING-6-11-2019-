package com.era.AllAgentNo;

import com.era.AllCustNo.*;
import com.era.FingerCheck.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import process.matchData.Bean;
import process.matchData.FingerMatchIsoToIsoFTR;
import process.matchData.FingerMatchIsoToIsoSG;
import process.matchData.GetFingerData;
import process.matchData.MatchFingerInsert;

public class AllAgentNo {

    public static HashMap<Integer, List<Integer>> receiveAgentCusNo(String pStatus) {  //List<String> // HashMap<Integer, Integer>// HashMap<Integer, TreeSet<Integer>>
        AllCustNo allCustNo = new AllCustNo();
        Connection con = null;
        HashMap<Integer, List<Integer>> mapAgentCustNo = new HashMap<>();
        
        List<Integer> listIntCust = allCustNo.custNo(pStatus);  // Receive all Customer No as String
        try {
            
            con = DbConnectivity.getConnection();
            System.out.println("---Total (" + pStatus + ") Customer : " + listIntCust.size());
           // System.out.println("Retrieve agent+ARO cust no of "+listIntCust.size()+" Customer.");
           int matchCount = 0;
            int UnMatchCount = 0;
            int custProcessCount = 0;
            
            int count = 0;
            for (int c = 0; c < listIntCust.size(); c++) { // Loop for change Customer
                List<Integer> listIntAgent = new ArrayList<Integer>();

                CallableStatement cs = con.prepareCall("begin BIOTPL.GET_CUSTOMER_LIST2.GET_AGENT_CUST_NO2(?,?,?); end;");
                cs.setInt(1, listIntCust.get(c)); // Cust No  //cs.setString(1, listStrCust.get(c));
                cs.setString(2, pStatus);         // Standard
                cs.registerOutParameter(3, java.sql.Types.ARRAY, "CUS_NO_LIST"); // Agent No of that cust no
                cs.execute();

                Array arrAgentNo = cs.getArray(3);
                Map map = con.getTypeMap();
                map.put("CUSTOMER_NO_LIST", Class.forName("com.era.FingerCheck.TestArr"));
                Object[] values = (Object[]) arrAgentNo.getArray();

                //List<String> values = (List<String>) arrAgentNo.getArray();
                for (int k = 0; k < values.length; k++) { // Convert to Integer type by TestArr class
                    TestArr a = (TestArr) values[k];
                    listIntAgent.add(a.attrOne);
                }
                
                ///-----Start retrieve customer finger data
                // mapAgentCustNo.put(listIntCust.get(c), listIntAgent); // Arrays.asList(listIntAgent)
                    
                
                if (listIntAgent.size() >= 1) {         // This condition check agent are available or not for specific customer
                    
                    HashMap<String, byte[]> custAvaFPmap = new HashMap<String, byte[]>(); // -- Customer finger in Map
                    System.out.println("===Start Matching for Customer No : " + listIntCust.get(c) + ", And his total agent & ARO is : " + listIntAgent.size() + "===");

                    String cQuery = "SELECT LINDEX,LTHUMB,RINDEX,RTHUMB,LMIDDLE,LRING,LLITTLE,"
                            + "RMIDDLE,RRING,RLITTLE from  Biotpl.FP_ENROLL where  CUST_NO =" + listIntCust.get(c); // getKey() is for Key value of Map. (Simple value is : 258534)
                    PreparedStatement pstmt = con.prepareStatement(cQuery);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        byte[] LINDEX = getByteDataFromBlob(rs.getBlob("LINDEX"));
                        byte[] LTHUMB = getByteDataFromBlob(rs.getBlob("LTHUMB"));
                        byte[] RINDEX = getByteDataFromBlob(rs.getBlob("RINDEX"));
                        byte[] RTHUMB = getByteDataFromBlob(rs.getBlob("RTHUMB"));

                        byte[] LMIDDLE = getByteDataFromBlob(rs.getBlob("LMIDDLE"));
                        byte[] LRING = getByteDataFromBlob(rs.getBlob("LRING"));
                        byte[] LLITTLE = getByteDataFromBlob(rs.getBlob("LLITTLE"));

                        byte[] RMIDDLE = getByteDataFromBlob(rs.getBlob("RMIDDLE"));
                        byte[] RRING = getByteDataFromBlob(rs.getBlob("RRING"));
                        byte[] RLITTLE = getByteDataFromBlob(rs.getBlob("RLITTLE"));

                        if (RLITTLE != null) {
                            custAvaFPmap.put("RLITTLE", RLITTLE);
                        }
                        if (LLITTLE != null) {
                            custAvaFPmap.put("LLITTLE", LLITTLE);
                        }
                        if (LRING != null) {
                            custAvaFPmap.put("LRING", LRING);
                        }
                        if (LMIDDLE != null) {
                            custAvaFPmap.put("LMIDDLE", LMIDDLE);
                        }
                        if (RTHUMB != null) {
                            custAvaFPmap.put("RTHUMB", RTHUMB);
                        }
                        if (RINDEX != null) {
                            custAvaFPmap.put("RINDEX", RINDEX);
                        }
                        if (LTHUMB != null) {
                            custAvaFPmap.put("LTHUMB", LTHUMB);
                        }
                        if (LINDEX != null) {
                            custAvaFPmap.put("LINDEX", LINDEX);
                        }
                        if (RRING != null) {
                            custAvaFPmap.put("RRING", RRING);
                        }
                        if (RMIDDLE != null) {
                            custAvaFPmap.put("RMIDDLE", RMIDDLE);
                        }

                        System.out.println("Total Finger of this customer("+listIntCust.get(c)+") is :" + custAvaFPmap.size());
//                        for(int r = 0; r<CustListData.size(); r++){
//                            System.out.println(CustListData.get(r)); // System.out.println("buffer : "+Base64.getEncoder().encodeToString(AllData.get(0)));
//                        }
//                        CustListData = new ArrayList<byte[]>() {};
                    }

                    // Agent User finger data --------------------------------------------------------------
                    //System.out.println("------ Agent of " + entry.getKey() + " ------");
                    for (int a = 0; a < listIntAgent.size(); a++) { // This loop change next Agent of specific Customer
                        HashMap<String, byte[]> agentAvaFPmap = new HashMap<String, byte[]>(); // -- Agent finger in Map

                        //String aQuery = "SELECT DISTINCT CUST_NO, RLITTLE, LLITTLE, LRING, LMIDDLE, RTHUMB, RINDEX, LTHUMB, LINDEX, RRING, RMIDDLE, STANDARD  from FP_ENROLL where CUST_NO =  " + entry.getValue().get(c); 
                        String aQuery = "SELECT CUST_NO, RLITTLE, LLITTLE, LRING, LMIDDLE, RTHUMB, RINDEX, LTHUMB, LINDEX, RRING, RMIDDLE, STANDARD  from FP_ENROLL where CUST_NO = "+ listIntAgent.get(a); //  337003
                        PreparedStatement pstmt1 = con.prepareStatement(aQuery);
                        ResultSet rs1 = pstmt1.executeQuery();

                        while (rs1.next()) {
                            byte[] aRthumb = getByteDataFromBlob(rs1.getBlob("RTHUMB"));
                            byte[] aRindex = getByteDataFromBlob(rs1.getBlob("RINDEX"));
                            byte[] aMiddle = getByteDataFromBlob(rs1.getBlob("RMIDDLE"));
                            byte[] aRring = getByteDataFromBlob(rs1.getBlob("RRING"));
                            byte[] aRlittle = getByteDataFromBlob(rs1.getBlob("RLITTLE"));
                            byte[] aLthumb = getByteDataFromBlob(rs1.getBlob("LTHUMB"));
                            byte[] aLindex = getByteDataFromBlob(rs1.getBlob("LINDEX"));
                            byte[] aLmiddle = getByteDataFromBlob(rs1.getBlob("LMIDDLE"));
                            byte[] aLring = getByteDataFromBlob(rs1.getBlob("LRING"));
                            byte[] aLlittle = getByteDataFromBlob(rs1.getBlob("LLITTLE"));

                            //System.out.println(entry.getValue().get(c)+" - "+ cRthumb);
                            if (aRlittle != null) {
                                agentAvaFPmap.put("RLITTLE", aRlittle);
                            }
                            if (aLlittle != null) {
                                agentAvaFPmap.put("LLITTLE", aLlittle);
                            }
                            if (aLring != null) {
                                agentAvaFPmap.put("LRING", aLring);
                            }
                            if (aLmiddle != null) {
                                agentAvaFPmap.put("LMIDDLE", aLmiddle);
                            }
                            if (aRthumb != null) {
                                agentAvaFPmap.put("RTHUMB", aRthumb);
                            }
                            if (aRindex != null) {
                                agentAvaFPmap.put("RINDEX", aRindex);
                            }
                            if (aLthumb != null) {
                                agentAvaFPmap.put("LTHUMB", aLthumb);
                            }
                            if (aLindex != null) {
                                agentAvaFPmap.put("LINDEX", aLindex);
                            }
                            if (aRring != null) {
                                agentAvaFPmap.put("RRING", aRring);
                            }
                            if (aMiddle != null) {
                                agentAvaFPmap.put("RMIDDLE", aMiddle);
                            }

                           System.out.println("Total Finger of this customer("+listIntAgent.get(a)+") is :" + agentAvaFPmap.size());
                        }
                        //------ Check Customer and Agent Finger data . Is all data comes correctly or not?

                        int customerId = listIntCust.get(c);
                        int AgentId = listIntAgent.get(a);
//                                   System.out.println("Cust Id :"+customerId);
//                                   System.out.println("Agent Id :"+AgentId);

                        //--------------------------------------------------------------------------
                        //---------------------------- Start matching (Matching-Zone)--------------------------
//                                for (int c = 0; c < custAvaFP.size(); c++) {
//                                    for (int a = 0; a < agentAvaFP.size(); a++) {
                        System.out.println("Customer total finger's : " + custAvaFPmap.size());
                        System.out.println("Agent total finger's : " + agentAvaFPmap.size());

                        for (Map.Entry<String, byte[]> custEntry : custAvaFPmap.entrySet()) {   //Loop for change customer finger
                            for (Map.Entry<String, byte[]> agentEntry : agentAvaFPmap.entrySet()) { // Loop for change agent finger
//                                        System.out.println(agentAvaFP.get(a)); // System.out.println("buffer : "+Base64.getEncoder().encodeToString(AllData.get(0)));
//                                        System.out.println(custAvaFP.get(c));

                                byte[] bCustFP = null;
                                bCustFP = custEntry.getValue();
                                byte[] bAgentFP = null;
                                bAgentFP = agentEntry.getValue();
                                String CustFpName = custEntry.getKey();
                                String agentFpName = agentEntry.getKey();
//                                        System.out.println("CustFPNo = "+ c +"| AgentFpNo = "+ a);
                                System.out.println("Cust_id: " + customerId + ". Cust Finger: " + CustFpName + " || Agent_Id: " + AgentId + ". Agent Finger: " + agentFpName);

                                
                                //*** Call Matching function (This Secugen matching Engine)
                                //byte[][] data = new byte[1][];
                                //data[0] = bCustFP;
                                //FingerMatchIsoToIsoSG fingerMatchIsoToAnsi = new FingerMatchIsoToIsoSG();
                                //boolean matchingFlag = fingerMatchIsoToAnsi.fingerPrintIndetify(bAgentFP, data, Integer.toString(customerId));
                                //*** Call Matching function (This Futronic matching Engine)
                                FingerMatchIsoToIsoFTR fingerMatchIsoToIsoFTR = new FingerMatchIsoToIsoFTR();
                                boolean matchingFlag = fingerMatchIsoToIsoFTR.FingerprintVerify(bCustFP, bAgentFP);
                                if (matchingFlag) {
//                                            
                                    System.out.println("***** Finger Matched. Customer(" + customerId + " - " + CustFpName + ") Vs Agent(" + AgentId + " - " + agentFpName + ") *****");
                                    MatchFingerInsert.insertFinger(customerId, AgentId, agentFpName, CustFpName, pStatus);
                                    //System.out.println(entry.getKey()+" - " + entry.getValue().get(c)+" ("+ custFpNum +" - "+agentFpNum+")");
                                    matchCount = matchCount + 1;

                                } else {
                                    UnMatchCount = UnMatchCount + 1;
                                    //System.out.println("matchingFlag = " + matchingFlag);
                                    System.out.println("## Finger does not Matched for Customer(" + customerId + " - " + CustFpName + ") Vs Agent(" + AgentId + " - " + agentFpName + ") ##");
                                }
                                 
                            }
                            System.out.println("...");
                        }
                        //--------------------------------------------End of matchig zone

                        System.out.println("Another Agent. Agent Number : "+ a);
                        agentAvaFPmap.clear();
                        pstmt1.close();
                        rs1.close();

                    }// End of 2nd for loop
                    pstmt.close();
                    rs.close();
                    custAvaFPmap.clear();
                } else {
                    System.out.println("No agent is found for Customer :" + listIntCust.get(c));
                }
                
                
                ///-----End retrieve customer finger data
                System.out.println("--Another Customer. Customer Number :"+ c);
                mapAgentCustNo.put(listIntCust.get(c), listIntAgent); // Arrays.asList(listIntAgent)
                count++;
                listIntAgent.clear();
            }
            System.out.println("Successfully Retrieve agent+ARO no against customer no = " + count);
        } catch (Exception ex) {
            System.out.println("Exception raised at Retrieve Agent no in 'receiveCusNo' class. Error is : "+ex);
            ex.printStackTrace();
        } finally {
            DbConnectivity.releaseConnection(con);
        }
        //System.out.println("---Total ("+pStatus+") Customer : "+mapAgentCustNo.size());
        return mapAgentCustNo;
    }
    
    //--------
    
     static byte[] getByteDataFromBlob(Blob blob) {
        if (blob != null) {
            try {
                return blob.getBytes(1, (int) blob.length());
            } catch (SQLException ex) {
                Logger.getLogger(GetFingerData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    // Main method for testing this class   
    public static void main(String[] args) {

        HashMap<Integer, List<Integer>> mapAgentCustNo = receiveAgentCusNo("S");
        System.out.println("Customer and Agents (HashMap) :"+ mapAgentCustNo);

          for (Map.Entry<Integer, List<Integer>> entry : mapAgentCustNo.entrySet())
            {
                if(entry.getValue().size()>=1){
//                    System.out.println("Agent user size is :"+ entry.getValue().size());
//                    System.out.println(entry.getKey() + ":" + entry.getValue());
                    System.out.println("Key : "+entry.getKey());
                    for(int i=0; i<entry.getValue().size(); i++){
                        System.out.println(entry.getValue().get(i));
                    }
                 }
            }
          
    }
}
//258534