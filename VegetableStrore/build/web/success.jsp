<%-- 
    Document   : success
    Created on : Mar 6, 2022, 12:40:15 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Success Page</title>
</head>
<body style="text-align: center">
     <c:if test="${sessionScope.USER == null or (sessionScope.USER.isAdmin()) }">
        <c:redirect url="login.jsp"></c:redirect>

    </c:if>
    <h1>Order Successfully!!!</h1>
    <h1>The email were send to your email address in database, please check your email!!!</h1>
    
    <a href="MainController?action=Search&Search=">Back To Shopping</a>
</body>
</html>
