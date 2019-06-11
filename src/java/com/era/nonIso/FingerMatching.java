package com.era.nonIso;

import com.futronic.SDKHelper.FtrIdentifyRecord;
import com.futronic.SDKHelper.FtrIdentifyResult;
import com.futronic.SDKHelper.FutronicException;
import com.futronic.SDKHelper.FutronicIdentification;
import com.futronic.SDKHelper.FutronicSdkBase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FingerMatching {
    
    private FutronicSdkBase m_Operation;
    private String m_DbDir;
    private Object m_OperationObj;
    
    boolean matchFingerData(FingerData fingerData1 , FingerData fingerData2){  
        System.out.println("Inside 'matchFingerData' method for matching Old data.");
        try{
            m_Operation = new FutronicIdentification(); 
             ((FutronicIdentification) m_Operation).setFARN(245);   
             ((FutronicIdentification) m_Operation).setBaseTemplate(fingerData1.getM_Template()); 
            
            FtrIdentifyRecord[] rgRecords = new FtrIdentifyRecord[1]; 
            rgRecords[0] = fingerData2.getFtrIdentifyRecord() ; 
            
            FtrIdentifyResult result = new FtrIdentifyResult();          
            int nResult =  ((FutronicIdentification) m_Operation).Identification(rgRecords, result);

            int i = 0;
            if (nResult == FutronicSdkBase.RETCODE_OK) {
                System.out.println("result.m_Index = " + result.m_Index);
                if (result.m_Index != -1) {
                    m_Operation = null;
                    return true;
                } else {
                    m_Operation = null;
                    return false; 
                } 
            }
            
        } catch (FutronicException ex) {
                Logger.getLogger(FingerMatching.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    
}
