<%-- 
    Document   : viewcart
    Created on : Mar 4, 2022, 7:59:50 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="product.ProductDTO"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewCart Page</title>
        <link href="css/viewcart.css" rel="stylesheet" type="text/css"/>
        
        <c:if test="${sessionScope.USER == null or (sessionScope.USER.isAdmin()==true) }">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
       
</head>
<body>
    <div class="container">
        <%  double total = 0;
            int count = 0;
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <table border="1" cellpadding="1" style="text-align: center">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ProductID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Edit</th>
                    <th>Remove</th>

                </tr>
            </thead>
            <tbody>

                <%
                    for (ProductDTO product : cart.getCart().values()) {
                        total = total + product.getPrice() * product.getQuantity();
                        count++;

                %>
                <tr>
            <form method="POST" action="MainController">
                <td><%=count%></td>
                <td>
                    <%=product.getProductID()%>
                    <input type="hidden" name="productID" value="<%=product.getProductID()%>"/>
                </td>
                <td><%=product.getProductName()%></td>
                <td><%=product.getPrice()%></td>
                <td><input type="number" min="1" value="<%=product.getQuantity()%>" name="quantity"></td>
                <td><%=product.getPrice() * product.getQuantity()%></td>
                <td>

                    <input type="submit" value="EditCart" name="action">                                           
                </td>
                <td>
                    <input type="submit" value="RemoveCart" name="action">                                           
                </td>
            </form>
            </tr>

            <%          }

                    }
                }
            %>  


            </tbody>
        </table>

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (cart == null || message != null || cart.getCart().isEmpty()) {
                message = "Your Cart is empty";


        %>
        <div>
            <h1 style="text-align: center"><%=message%></h1>
            <h2 style="text-align: center"><a href="MainController?action=Search&Search=">Continue Shopping</a></h2>
        </div>
        <% } else {%>
        <div style="margin-top: 20px">
            <h3>Total: <%=total%> USD</h3>
            <form method="POST" action="MainController">

                <input style="background-color: activeborder" class="button-10" type="submit" value="ConfirmCart" name="action">
                <a href="MainController?action=Search&Search=">Back To Shopping</a>
            </form>
        </div>

        <%}%>
    </div>
</body>
</html>
