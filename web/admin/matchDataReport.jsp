<!DOCTYPE HTML>

<%@page import="com.era.sQuery.BeanClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.List"%>
<html>

    <head>
        <title>textured_industrial - examples</title>
        <meta name="description" content="website description" />
        <meta name="keywords" content="website keywords, website keywords" />
        <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
        <link rel="stylesheet" type="text/css" href="style/style.css" title="style" />
    </head>

    <body>
        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html">OFFLINE<span style="color:red">FINGER MATCHING</span></a></h1>

                    </div>
                </div>
               
            </div>
            <div id="site_content">
                <div class="sidebar">
                    <!-- insert your report code here -->
                    <h2 align="center"><u>Report on matched finger data</u></h2>
                            <%
                                int count = 0;
                            %>

                    <%
                        List<BeanClass> pdts = (List<BeanClass>) request.getAttribute("myList");
                        if (pdts != null) {
                            
                    %>
                    <label>Total Match : </label><%= pdts.size()%>
                    <table align="center" border="1px" style="width: 65%; text-align: center;">

                        <th>Customer No</th>
                        <th>Agent No</th>
                        <th>Customer Finger No</th>
                        <th>Agent Finger No</th>
                        <th>Standard</th>

                        <%
                            for (BeanClass bn : pdts) {
                        %>        
                        <tr>
                            <td><%=bn.getCustNo()%></td>
                            <td><%=bn.getAgentNo()%></td>
                            <td><%=bn.getCustFpNo()%></td>
                            <td><%=bn.getAgentFpNo()%></td>
                            <td><%=bn.getStandard()%></td>

                        </tr>
                        <%  count = count + 1;
                                }
                            }

                        %>
                        
                        <!--End main process-->


                </div>
            </div>

        </div>
    </body>
</html>
