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
                reg += "<td><input type='text' name='user' required/></td></tr>";
                reg += "<tr><td>Choose a password:</td>";
                reg += "<td><input type='password' name='pass' required/></td></tr></tbody></table>";
                reg += "<input type='submit' value='Join!' />";
                document.getElementById("registry").innerHTML += reg;
            }
        </script>
        <h1>Welcome to DataWeb Wizard!</h1>
        <%
            if (request.getParameter("logout") != null){
                if (request.getParameter("logout").equals("true")) {
                    HttpSession mySession = request.getSession();
                    mySession.invalidate();
                    out.println("<h4>You have been logged out!</h4>");
                }
                else
                    out.println("<h4>Please log in to DataWeb Wizard.</h4>");
            }
        %>
        <form action="registerServlet" id="registry">
            <h3>Create an account</h3>
            <%
                if (request.getParameter("rerror") != null) {
                    if (request.getParameter("rerror").equals("taken")) {
                        out.println("Please choose another username.<br>");
                    } else {
                        out.println("Sorry, an error ocurred.<br>");
                    }
                }
                else if (request.getParameter("succ") != null) {
                    out.println("Your account has been created, " + request.getParameter("succ") + "<br>");
                }
                
            %>
            <input type ="button" id="regBut" value="Register" onclick="showRegistry()" />
        </form>
        <form action="loginServlet">
            <h3>Or sign in to your account</h3>
            <%
                if (request.getParameter("lerror") != null) {
                    out.println("Wrong username or password. Please try again.<br>");
                }
            %>
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" id="user" name="user" value="" required /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="pass" value="" required /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Sign in"/>
        </form>
    </body>
</html>
