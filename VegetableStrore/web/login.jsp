<%-- 
    Document   : login
    Created on : Jan 26, 2022, 12:52:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
        <title>login</title>
    </head>
    <body>
        <div class="body"></div>
        <div class="grad"></div>
        <div class="header">
            <div style="font-size: 1.8em; font-weight: bold">ORGANIC<span>VEGETABLE</span></div>
        </div>
        <br>
        <div class="login">
            <form action="MainController" method="POST" id="login-form">
                <input type="text" placeholder="username" name="userID"><br>
                <input type="password" placeholder="password" name="password"><br>
                <input type="submit" value="Login" name="action">

                <!--            login with google-->
                <div class="google-sign-in">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/VegetableStrore/MainController&response_type=code
                       &client_id=247126668913-fi6ogou11n56ejlfk0fuq26ublnavlv6.apps.googleusercontent.com&approval_prompt=force">
                        
                        <img alt="Google login" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
                        <span>Sign in with Google</span>
                    </a>


                </div>
                <br/>             
            </form>

            <div style="position: relative; font-size: 20px; margin-top: 15px;">
                <%
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }


                %>
                <%=error%>
            </div>


        </div>



    </body>
</html>
