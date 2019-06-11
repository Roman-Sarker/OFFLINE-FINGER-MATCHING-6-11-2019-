package era.com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
                
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("username :"+username);
        System.out.println("password :"+password);
        
        String redirectURL = "admin/";
        if(!username.equals(null) || !username.isEmpty()){
        
            response.sendRedirect(redirectURL + "home.jsp");
            System.out.println("not empty.");
        }
        else{
            response.sendRedirect(redirectURL + "index.jsp");
            System.out.println("empty.");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //String email = request.getParameter("email");
        //String pass = request.getParameter("password");
        
        PrintWriter out = response.getWriter();
                
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("username :"+username);
        System.out.println("password :"+password);
        
        String redirectURL = "admin/";
        //if(!username.equals(null) || !username.isEmpty()){
        if(username != "" && password != ""){
            if(username.equals("era@gmail.com") && password.equals("rmn")){
                response.sendRedirect(redirectURL + "home.jsp");
                System.out.println("Username and Password found.");
            }
            else{
                response.sendRedirect(redirectURL + "index.jsp");
                System.out.println("Invalid username and password.");
            }
            }
        else{
            response.sendRedirect(redirectURL + "index.jsp");    
            System.out.println("Username and password are empty.");
            }
        
        //PrintWriter out = response.getWriter();
        //out.println("Email : "+ email);
        //out.println("Pass : "+ pass);
        
        
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


  
}
