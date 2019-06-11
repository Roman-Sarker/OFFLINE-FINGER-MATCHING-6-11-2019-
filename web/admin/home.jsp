<!DOCTYPE HTML>
<html>

    <head>
        <title>Offline Finger Matching</title>
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
                        <h1><a href="index.html">OFFLINE<span style="color: red">FINGER MATCHING</span></a></h1>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu">
                        <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
                        <li class="selected"><a href="home.jsp">Home</a></li>
                        <li><a href="/FingerOfflineMatch/FingerCheck">Offline Match</a></li>
                        <li><a href="DatabaseInfo.jsp">Database Information</a></li>
                        <li><a href="index.jsp">Sign Out</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">
                <div class="sidebar">
                    <!-- insert your sidebar items here -->


                </div>
                <div id="content">
                    <!-- insert the page content here -->
                    <h1>Implementation of Finger Print Offline Matching project</h1>
                    <p>Given bellow some of step to deploy Finger Print Offline Matching (FOM). Please follow that step by step correctly and run the project.</p>
                    <p>1.	Host this ‘FingerOfflineMatch.war’  file in your (Bank-Asia) UAT(User Acceptance Test) environment as like as matching server. Where old finger print (*.tml) data are available. </p>

                    <p style="color: red; ">UAT environment would be as like as Agent Banking matching server configuration.</p>
                        <p>2.	Restart the tomcat server.</p>
                        <p>3.	Run the application.</p>
                        <p>4.	Login by only press the login button (No need to user name and password right now).</p>
                        <p>5.	Set the Database information by clicking ‘Database Information’ from Home Menu.</p>
                        <p>6.	Finally click on ‘OFFLINE MATCH’ menu for see the report of which finger data is matched with another person.</p>
                    
                </div>
            </div>
            <div id="footer">
                Copyright &copy; mdroman601@gmail.com | <a href="http://validator.w3.org/check?uri=referer">Era InfoTech Ltd.</a>
            </div>
        </div>
    </body>
</html>
