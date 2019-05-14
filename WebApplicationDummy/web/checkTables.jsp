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
        <%
            HttpSession mySession = request.getSession();
            if (mySession.getAttribute("user") == null)
                response.sendRedirect("index.jsp?logout=false");
        %>
        <script>
            
        </script>
        <h1>DataWeb Wizard </h1>
        <div>
            <script src="links.js"></script>
        </div>
        <h3>Table reader</h3>
        Select a table to browse: <select name="tablesList">
            <option>Default</option>
        </select>
        <input type="button" value="Go" />
    </body>
</html>
