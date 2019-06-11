<!DOCTYPE HTML>
<html>

<head>
  <title>Database Information</title>
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
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li><a href="home.jsp">Home</a></li>
          <li><a href="/FingerOfflineMatch/FingerCheck">Offline Match</a></li>
          <li><a href="DatabaseInfo.jsp">Database Information</a></li>
          <li class="selected"><a href="contact.html">Sign Out</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
      <div class="sidebar">
        <!-- insert your sidebar items here -->
        
      </div>
      <div id="content">
        <!-- insert the page content here -->
        <h1>Set Database Information Here</h1>
        <p>Below is an example of how a contact form might look with this template:</p>
        <%
            String contextPath = request.getContextPath();
        %>
        <form action="<%=contextPath%>/WriteDatabasePropertiesFile" role="form" method="post">
          <div class="form_settings">
            <p><span>IP</span><input class="contact" type="text" name="ip" value="" /></p>
            <p><span>Port</span><input class="contact" type="text" name="portNo" value="" /></p>
            <p><span>Service Name</span><input class="contact" type="text" name="serviceName" value="" /></p>
            <p><span>User</span><input class="contact" type="text" name="userName" value="" /></p>
            <p><span>Password</span><input class="contact" type="password" name="password" value="" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="contact_submitted" value="submit" /></p>
          </div>
        </form>
        <p><br /><br />NOTE: A contact form such as this would require some way of emailing the input to an email address.</p>
      </div>
    </div>
    <div id="footer">
      Copyright &copy; textured_industrial | <a href="http://validator.w3.org/check?uri=referer">HTML5</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> | <a href="http://www.html5webtemplates.co.uk">design from HTML5webtemplates.co.uk</a>
    </div>
  </div>
</body>
</html>
