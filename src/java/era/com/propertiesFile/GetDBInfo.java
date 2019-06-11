package era.com.propertiesFile;
 
import java.io.File;
import java.io.FileInputStream; 
import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties;

public class GetDBInfo {

    public static DBInfo getDbInfo() {

        Properties prop = new Properties();
        InputStream inputStream = null;
        DBInfo dbInfo = new DBInfo();

        try {
            File file =new File("FOMDb.properties");
            System.out.println("file = " + file);
            inputStream = new FileInputStream(file);
            prop.load(inputStream);
            dbInfo.ip = prop.getProperty("ip");
            dbInfo.portNo = prop.getProperty("portNo");
            dbInfo.serviceName = prop.getProperty("serviceName");
            dbInfo.userName = prop.getProperty("userName");
            dbInfo.password = prop.getProperty("password");
            inputStream.close();
            
            System.out.println("Successfully Read Database Info data.");
            return dbInfo; 
        } catch (IOException e) {
            System.out.println("Error raise at Read Database Info Properties file.");
            return null;
        } 
    }
}
