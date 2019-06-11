
package era.com.propertiesFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roman
 */
@WebServlet(name = "WriteDatabasePropertiesFile", urlPatterns = {"/WriteDatabasePropertiesFile"})
public class WriteDatabasePropertiesFile extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WriteDatabasePropertiesFile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WriteDatabasePropertiesFile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ip = request.getParameter("ip");
        String portNo = request.getParameter("portNo");
        String serviceName = request.getParameter("serviceName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String redirectURL = "admin/";

        Properties prop = new Properties();
        try {
            File file = new File("FOMDb.properties");
            OutputStream outputStream = new FileOutputStream(file);
            prop.setProperty("ip", ip);
            prop.setProperty("portNo", portNo);
            prop.setProperty("serviceName", serviceName);
            prop.setProperty("userName", userName);
            prop.setProperty("password", password);
            prop.store(outputStream, null);
            outputStream.close();
            
            System.out.println("Successfully write Database info.");
            System.out.println("IP : "+ ip);
            System.out.println("Port No : "+ portNo);
            System.out.println("Service Name : "+ serviceName);
            System.out.println("User Name : "+ userName);
            System.out.println("Password : "+ password);
            response.sendRedirect(redirectURL + "DatabaseInfo.jsp");

        } catch (IOException ioe) {
            response.sendRedirect(redirectURL + "DatabaseInfo.jsp"); 
            System.out.println("Error at write properties file. Error is : "+ ioe.getMessage());
            System.out.println("Error at write properties file. Error is : "+ ioe);
            ioe.printStackTrace();
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
