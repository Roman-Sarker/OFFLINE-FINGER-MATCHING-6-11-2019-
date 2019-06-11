<%-- 
    Document   : index
    Created on : May 14, 2019, 10:33:42 AM
    Author     : roman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String redirectURL = "admin/index.jsp";
            response.sendRedirect(redirectURL);
        %>
    </body>
</html>
