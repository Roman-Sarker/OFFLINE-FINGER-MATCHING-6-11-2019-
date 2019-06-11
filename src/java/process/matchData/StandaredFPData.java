package process.matchData;

import com.era.FingerCheck.DbConnectivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static process.matchData.GetFingerData.getByteDataFromBlob;
import static com.era.AllAgentNo.AllAgentNo.receiveAgentCusNo;

/**
 *
 * @author root
 */
public class StandaredFPData {

    public static void methodIsoFP() throws SQLException, ClassNotFoundException {

        String vStatus = "S";
        // Receive all agent no of specific customer from 'AllAgentNo' class which inherit here.
        receiveAgentCusNo(vStatus);
        
        
    }
    // Main() method for testing purpose   
//    public static void main(String[] args) {
//        StandaredFPData.methodIsoFP();
//    }
//    
}
