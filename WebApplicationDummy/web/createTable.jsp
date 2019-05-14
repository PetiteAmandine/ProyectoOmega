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
        <script>count = 1;</script>
        <script>
            function addField() {
                count++;
                var divN = "div" + count;
                var fieldN = "field" + count;
                var typeN = "type" + count;
                var field = "<div id = '" + divN + "'>";
                field += "Field name: <input type='text' name='" + fieldN + "' placeholder='" + fieldN + "'onkeyup='verify(this)' required/>";
                field += " Type: <select name='" + typeN + "'>";
                field += "<option>Varchar</option>";
                field += "<option>Integer</option>";
                field += "<option>Double</option>";
                field += "</select> Primary key: <input type='radio' name='key' value= '" + fieldN + "'/><br>";
                document.getElementById("fieldsDiv").innerHTML += field;
            }

            function removeField() {
                if (count > 1) {
                    var divN = "div" + count;
                    count--;
                    var deadDiv = document.getElementById(divN);
                    deadDiv.parentNode.removeChild(deadDiv);
                }
                else {
                    alert("The table must have at least one field.");
                }
            }

            function verify(element) {
                var x = element.value;
                if (x == "" || x.indexOf(" ") >= 0)
                    alert("Invalid name.");
            }
        </script>
        <h1>DataWeb Wizard</h1>
        <div>
            <script src="links.js"></script>
        </div>
        <h3>Table creator</h3>
        <form name = "bigForm" autocomplete="off" action='createTableServlet'>
            Table name: <input type="text" id="tableN" placeholder="myTable" onkeyup="verify(this)" required/><br><br>
            <div id="fieldsDiv">
                <div id="div1">
                    Field name: <input type="text" name="field1" placeholder="field1" onkeyup="verify(this)" required/>
                    Type: <select name="type1">
                        <option>Varchar</option>
                        <option>Integer</option>
                        <option>Double</option>
                    </select>
                    Primary key: <input type="radio" name="key" value= "field1" checked="checked"/><br>
                </div>
            </div>
            <br><input type="button" value="Add field" onclick="addField()"/>
            <input type="button" value="Remove field" onclick="removeField()"/>
            <br><br><input type="submit" value="Ready!" /><br>
        </form>
    </body>
</html>
