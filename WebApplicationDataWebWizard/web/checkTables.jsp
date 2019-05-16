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
            if (mySession.getAttribute("user") == null) {
                response.sendRedirect("index.jsp?logout=false");
            }
        %>
        <script>
            function getTables() {
                var ajaxRequest;
                if (window.XMLHttpRequest) {
                    ajaxRequest = new XMLHttpRequest();
                } else {
                    ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }
                ajaxRequest.onreadystatechange = function () {
                    if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
                        var JSONObject = JSON.parse(ajaxRequest.responseText);
                        var txt = "<select id='tablesList' onchange='getTableContent(this.value, 0, document.getElementById('scrollSizes').value)'>";
                        for (var i = 0; i < JSONObject.clientes.length; i++) {
                            txt += "<option>" + JSONObject.clientes[i].nombre + "</option>";
                        }
                        txt += "</select>";
                        document.getElementById("divsito").innerHTML = txt;
                    }
                }
                ajaxRequest.open("GET", "checkTablesServlet", true /*async*/);
                ajaxRequest.send();
            }

            function getTableContent(tableName, startRow, scrollSize) {
                
            }
        </script>
        <h1>DataWeb Wizard </h1>
        <div>
            <script src="links.js"></script>
        </div>
        <h3>Table reader</h3>
        <table border="0">
            <tbody>
                <tr>
                    <td>Select a table to browse:</td>
                    <td><div id="divsito"><select id="tablesList">
                                <option value="" disabled selected>My tables</option>
                            </select></div></td>
                    <td>Show me:</td>
                    <td><select id="scrollSizes" onchange="getTableContent(document.getElementById('tablesList').value, 0, this.value)">
                            <option>5</option>
                            <option>10</option>
                            <option>15</option>
                            <option>20</option>
                        </select></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
