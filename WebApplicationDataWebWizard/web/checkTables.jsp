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
    <body onload="getTables()">
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
                        var txt = "<select id='tablesList' onchange='freeToGo()'>";
                        txt += "<option value='' disabled selected>My tables</option>";
                        for (var i = 0; i < JSONObject.tables.length; i++) {
                            txt += "<option>" + JSONObject.tables[i].nombre + "</option>";
                        }
                        txt += "</select>";
                        document.getElementById("divsito").innerHTML = txt;
                    }
                };
                ajaxRequest.open("GET", "checkTablesServlet", true /*async*/);
                ajaxRequest.send();
            }

            function freeToGo() {
                document.getElementById("goBut").disabled = false;
                document.getElementById("tTable").value = document.getElementById("tablesList").value;
            }

            function getTableContent() {
                document.getElementById("divAdds").innerHTML = "<br>";
                var node = document.createElement("DIV");
                node.innerHTML = "<input type='button' id='addnew' value='Add new values' onclick='addTableContent()'/>";
                document.getElementById("divAdds").appendChild(node);
                document.getElementById("delBut").hidden = false;
                document.getElementById("edBut").hidden = false;
                var ajaxRequest;
                if (window.XMLHttpRequest) {
                    ajaxRequest = new XMLHttpRequest();
                } else {
                    ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }
                ajaxRequest.onreadystatechange = function () {
                    if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
                        fat = JSON.parse(ajaxRequest.responseText);
                        results = fat.rows;
                        keys = fat.keys;
                        types = fat.types;
                        var txt;
                        document.getElementById("pCount").value = keys.length;
                        var txt = "<table border='0'><thead><tr>";
                        for (var j = 0; j < keys.length; j++) {
                            txt += "<th>" + keys[j] + "</th>";
                        }
                        txt += "</tr></thead><tbody>";
                        for (var i = 0; i < results.length; i++) {
                            txt += "<tr>";
                            for (var j = 0; j < keys.length; j++) {
                                var k = keys[j];
                                var rownameij = "rowname" + i + j;
                                var rowtypeij = "rowtype" + i + j;
                                var rowij = "row" + i + j;
                                var node = document.createElement("DIV");
                                node.innerHTML = "<input type='hidden' name='" + rownameij + "' value='" + k + "' />";
                                document.getElementById("hiddensDiv").appendChild(node);
                                var input = "<input type='text' name='" + rowij + "' value='" + results[i][j] + "'/>";
                                var node2 = document.createElement("DIV");
                                node2.innerHTML = "<input type='hidden' name='" + rowtypeij + "' value='" + types[j] + "'/>";
                                document.getElementById("divAdds").appendChild(node2);
                                document.getElementById("hiddensDiv").appendChild(node2);
                                
                                txt += "<td>" + input + "</td>";

                            }
                            var checkid = "check" + i;
                            txt += "<td><input type='checkbox' name='" + checkid + "' value='ON' /></td>";
                            txt += "</tr>";
                        }
                        txt += "</tbody></table><br>";
                        document.getElementById("resultsDiv").innerHTML = txt;
                        document.getElementById("cCount").value = results.length;
                    }
                };
                ajaxRequest.open("GET", "queryTableServlet?table=" + document.getElementById("tablesList").value, true /*async*/);
                ajaxRequest.send();
            }

            function addTableContent() {
                var but = document.getElementById("addnew");
                but.parentNode.removeChild(but);
                var count = document.getElementById("pCount").value;
                var field = "<div>";
                for (var i = 0; i < count; i++) {
                    field += "<input type='text' name='txtB" + i + "' value='' />  ";
                }
                field += "</div>";
                miCount.value = count;
                miTabla.value = document.getElementById("tTable").value;
                var node1 = document.createElement("DIV");
                node1.innerHTML = field;
                document.getElementById("divAdds").appendChild(node1);
                var node2 = document.createElement("DIV");
                document.getElementById("divAdds").appendChild(node2);
                node2.innerHTML = "<br><input type='submit' id='crt' name='crt' value='Insert'/>";
            }
        </script>
        <h1>DataWeb Wizard </h1>
        <div>
            <script src="links.js"></script>
        </div>
        <%
            if (request.getParameter("succ") != null) {
                out.println("<p>Your table " + request.getParameter("succ") + " has been updated.<p>");
            }
            if (request.getParameter("fail") != null) {
                out.println("<p>There was a problem updating your table " + request.getParameter("fail") + ".</p>");
            }
        %>
        <h3>Table reader</h3>
        <table border="0">
            <tbody>
                <tr>
                    <td>Select a table to browse:</td>
                    <td><div id="divsito"><select id="tablesList">
                            </select></div></td>
                    <td>Show me:</td>
                    <td><select id="scrollSizes">
                            <option>2</option>
                            <option>3</option>
                            <option>5</option>
                            <option>10</option>
                        </select></td>
                        <td><input type="button" id="goBut" value="Go!" onclick="getTableContent()"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><br>
                        <input type="button" id="leftmost" value="<<"/>
                        <input type="button" id="left" value="<"  />
                        <input type="button" id="right" value=">"/>
                        <input type="button" id="rightmost" value=">>"/>
                    </td>
                </tr>
            </tbody>
        </table>
        <form name="resultsForm" action="updateServlet"><br>
            <div id="resultsDiv"></div>
            <div id="hiddensDiv">
                <input type="hidden" id="cCount" name="cCount" value="0" />
                <input type="hidden" id="pCount" name="pCount" value="0" />
                <input type="hidden" id="tTable" name="tTable" value="" />
                <input type="submit" id="delBut" name="delBut" value="Delete selected" hidden />
                <input type="submit" id="edBut" name="edBut" value="Edit selected" hidden/>
            </div>
        </form>
        <form name="addDB" action="updateServlet" method="POST">
            <div id="divAdds"></div>
                <input type="hidden" id="miCount" name="miCount" value="0" />
                <input type="hidden" id="miTabla" name="miTabla" value="0" />
        </form>
    </body>
</html>

