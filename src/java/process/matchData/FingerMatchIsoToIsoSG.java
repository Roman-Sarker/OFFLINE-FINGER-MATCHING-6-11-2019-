
package process.matchData;

/**
 *
 * @author root
 */

import SecuGen.FDxSDKPro.jni.JSGFPLib;
import SecuGen.FDxSDKPro.jni.SGFDxDeviceName;
import SecuGen.FDxSDKPro.jni.SGFDxErrorCode;
import SecuGen.FDxSDKPro.jni.SGFDxSecurityLevel;
import SecuGen.FDxSDKPro.jni.SGFDxTemplateFormat; 
 

/**
 *
 * @author root
 */
public class FingerMatchIsoToIsoSG {

    public static boolean fingerPrintIndetify(byte[] fingerData, byte[][] fingerEnrollData, String cust_no) {
        
        System.out.println("Inside 'fingerPrintIndetify' method for matching Standard data.");
        boolean matchingFlag = false;
        JSGFPLib fplib = new JSGFPLib();
        
        if ((fplib != null) && (fplib.jniLoadStatus != SGFDxErrorCode.SGFDX_ERROR_JNI_DLLLOAD_FAILED)) 
        {
            long ret = fplib.Init(SGFDxDeviceName.SG_DEV_FDU05);
            if(ret != 0 ){
               // System.out.println("fplib init failed.");
               // System.out.println("fplib init returns "+ret);
                matchingFlag=false;
                fplib.Close();
                return matchingFlag;
            }  
                
            ret  = fplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794);
            if(ret != 0 ){
              //  System.out.println("SetTemplateFormat failed.");
                matchingFlag=false;
                fplib.Close();
                return matchingFlag;
            }
            
            int i = 0,dataLength = fingerEnrollData.length;
            while (i < dataLength) {
                matchingFlag = fingerprintVerify(cust_no,fplib, fingerData, fingerEnrollData[i]);
                //System.out.println("length " + fingerEnrollData[i].length);
                if (matchingFlag) {
                 //   System.out.println("Matching result: true");
                    matchingFlag = true;
                    break;
                }
                ++i;
            }
        } else {
           // System.out.println("fplib init failed.");
            matchingFlag=false;
            return matchingFlag;
        }
        fplib.Close();
        return matchingFlag;
    }

    static boolean fingerprintVerify(String cust_no , JSGFPLib fplib, byte[] varifyData, byte[] dataFromDB) {
        boolean matchingFlag = false;
        long sl = SGFDxSecurityLevel.SL_NORMAL; // Set security level as NORMAL
        
        boolean[] matched = new boolean[1];
        if(varifyData == null || dataFromDB == null){
           // System.out.println("Fingerprint data is found to "+ "be null for matching and "+ " cust_no is "+cust_no);
            return false;
        }
        else if(varifyData.length > 500 || dataFromDB.length > 500 ){
//            System.out.println("abnormal finger data is found for customer number "+cust_no);
//            System.out.println("data length from database is  "+varifyData.length);
//            System.out.println("data length from verify is "+dataFromDB.length);
            return false;
        }else{
//            System.out.println("data length from database is  "+varifyData.length);
//            System.out.println("data length from verify is "+dataFromDB.length);
        }
        
     
        
        long iError = fplib.MatchTemplate(varifyData, dataFromDB, sl, matched);

        if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
            if (matched[0]) {
                matchingFlag = true;
            } else {
                matchingFlag = false;
            }
        } else {
            //System.out.println("This is a error "+iError);
            matchingFlag = false;
        }
        
        //System.out.println("cust_no "+cust_no+" is verified. Result: "+matchingFlag);
        return matchingFlag;
    
    } 
}
