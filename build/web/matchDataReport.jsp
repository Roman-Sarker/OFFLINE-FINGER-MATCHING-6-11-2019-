<%-- 
    Document   : matchDataReport
    Created on : Dec 10, 2018, 12:10:50 PM
    Author     : root
--%>

<%@page import="com.era.sQuery.BeanClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
    </head>
    <body>
        <h1 align="center"><u>Report on matched finger data</u></h1>
        <%
            int count = 0;
        %>
        
        <%
            List<BeanClass> pdts = (List<BeanClass>) request.getAttribute("myList");
            if(pdts!=null)
            {
        %>
        <table border="1px" style="width: 100%; text-align: center;">
         
                    <th>Customer No</th>
                    <th>Agent No</th>
                    <th>Customer Finger No</th>
                    <th>Agent Finger No</th>
                    <th>Standard</th>
                    
        <%
                for(BeanClass bn: pdts)
                {
        %>        
                   <tr>
                    <td><%=bn.getCustNo()%></td>
                    <td><%=bn.getAgentNo()%></td>
                    <td><%=bn.getCustFpNo()%></td>
                    <td><%=bn.getAgentFpNo()%></td>
                    <td><%=bn.getStandard()%></td>
                    
                   </tr>
        <%  count = count +1;      }
            }
        
        %>
       <label>Total Match: </label><%= count%>
    </body>
</html>
