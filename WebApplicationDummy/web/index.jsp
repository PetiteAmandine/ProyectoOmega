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
        <script>
            function showRegistry() {
                var but = document.getElementById("regBut");
                but.parentNode.removeChild(but);
                var reg = "<table border='0'><tbody><tr><td>Select a username:</td>";
                reg += "<td><input type='text' name='user' /></td></tr>";
                reg += "<tr><td>Choose a password:</td>";
                reg += "<td><input type='password' name='pass' /></td></tr></tbody></table>";
                reg += "<input type='submit' value='Join!' />";
                document.getElementById("registry").innerHTML += reg;
            }
        </script>
        <h1>Welcome to DataWeb Wizard!</h1>
        <form action="registerServlet" id="registry">
            <h3>Create an account</h3>
            <%
                if (request.getParameter("rerror") != null) {
                    if (request.getParameter("rerror").equals("taken")) {
                        out.println("Please choose another username.");
                    } else {
                        out.println("Sorry, an error ocurred.");
                    }
                }
                else if (request.getParameter("succ") != null) {
                    out.println("Your account has been created, " + request.getParameter("succ"));
                }
                
            %>
            <input type ="button" id="regBut" value="Register" onclick="showRegistry()" />
        </form>
        <form action="loginServlet">
            <h3>Or sign in to your account</h3>
            <%
                if (request.getParameter("lerror") != null) {
                    out.println("Wrong username or password. Please try again.");
                }
            %>
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="user" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Sign in" />
        </form>
    </body>
</html>
