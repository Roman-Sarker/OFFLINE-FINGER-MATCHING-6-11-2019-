
package process.matchData;

import com.futronictech.AnsiSDKLib;
import java.io.IOException;

/**
 *
 * @author roman
 */
public class FingerMatchIsoToIsoFTR {
    public Boolean FingerprintVerify( byte[] dataFromUser, byte[] dataFromDB) throws IOException {
        float[] matchResult = new float[1];
        AnsiSDKLib ansi_lib = new AnsiSDKLib();
        boolean matchingFlag = false;
        //Modal modal = new Modal();
        //float matchingScore = getMatchingScore(ansi_lib);
        float mMatchScoreHighMedium = AnsiSDKLib.FTR_ANSISDK_MATCH_SCORE_HIGH_MEDIUM;
        //MatchFlagWithScore matchFlagWithScore = new MatchFlagWithScore();
        

 /*     dataFromUser[10] = 0x00;
        dataFromUser[11] = 0x4D;
        dataFromUser[12] = 0x00;
        dataFromUser[13] = 0x03;
        dataFromUser[14] = 0x00;
        dataFromUser[15] = 0x00; */

        if (dataFromUser == null || dataFromDB == null) {
            System.out.println("null data found");
            matchingFlag = false;
           // return matchingFlag;
        } else if (dataFromUser.length > 500 || dataFromDB.length > 500) {
            System.out.println("abnormal data found");
            System.out.println("dataFromUser length is " + dataFromUser.length);
            System.out.println("dataFromDB length is " + dataFromDB.length);
            matchingFlag = false;
           // return matchingFlag;
        }
        //System.out.println("matchResult[0] = " + matchResult[0]);
        // Matching Here
        System.out.println("Start Matching");
        
        if (ansi_lib.MatchTemplates(dataFromDB, dataFromUser, matchResult)) {
            if(matchResult[0] > mMatchScoreHighMedium)
            {
              matchingFlag = true;
            }else{
                matchingFlag = false;
            }
             
        } else {
            int lastError = ansi_lib.GetErrorCode();
            //System.out.println("***----Error Code : "+lastError+". Error message : "+ansi_lib.GetErrorMessage());    
            String error = String.format("***----Error Code : "+lastError+". Error message : "+ansi_lib.GetErrorMessage());
            System.out.println(error);
            if (lastError == AnsiSDKLib.FTR_ERROR_EMPTY_FRAME
                    || lastError == AnsiSDKLib.FTR_ERROR_NO_FRAME
                    || lastError == AnsiSDKLib.FTR_ERROR_MOVABLE_FINGER) {
            error = String.format("Matching failed. Error: %s.", ansi_lib.GetErrorMessage());
            System.out.println(error);
                
                matchingFlag = false;
            } else {
                error = String.format("Verification failed. Error is : "+ ansi_lib.GetErrorMessage());
                matchingFlag = false;
            }
        }
        ansi_lib.CloseDevice();
        ansi_lib = null;
        return matchingFlag;
    }
    
}
