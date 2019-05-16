<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>DataWeb Wizard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>DataWeb Wizard</h1>
        <%
            HttpSession mySession = request.getSession();
            if (mySession.getAttribute("user") == null) {
                response.sendRedirect("index.jsp?logout=false");
            }
            out.println("<h3>Welcome back, " + mySession.getAttribute("user") + "!</h3>");
        %>
        <div>
            <script src="links.js"></script>
        </div>
        <%
            if (request.getParameter("succ") != null) {
                out.println("<p>Your table " + request.getParameter("succ") + " has been created"
                        + " successfully.");
            }
        %>
    </body>
</html>
