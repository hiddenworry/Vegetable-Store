<%-- 
    Document   : checkout
    Created on : Mar 5, 2022, 11:43:54 AM
    Author     : ADMIN
--%>

<%@page import="user.UserDTO"%>
<%@page import="product.ProductDTO"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:if test="${sessionScope.USER == null or (sessionScope.USER.isAdmin()==false) }">
        <c:redirect url="login.jsp"></c:redirect>

    </c:if>
    <title>Cofirm Cart Page</title>
    <style>

        .container * {
            text-align: center;
            margin-left: auto;
            margin-right: auto;
        }
        table {
            border: solid black 3px;
        }

    </style>
</head>
<body>
    <div class="container" style="padding: 5px; margin-left: auto; margin-right: auto;">
        <%  double total = 0;
            int count = 0;
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String userName = user.getFullName();
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <h2 style="display: inline-block"> Customer: </h2> <span style="margin-left: 20px;font-size: 20px"><%=userName%></span>

        <table cellpadding="5">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>

            </thead>
            <tbody>
                <tr>
                    <%
                        for (ProductDTO product : cart.getCart().values()) {
                            total = total + product.getPrice() * product.getQuantity();
                            count++;

                    %>
                    <td><%=count%></td>
                    <td><%=product.getProductID()%>  </td>
                    <td><%=product.getProductName()%></td>
                    <td><%=product.getPrice()%></td>
                    <td><%=product.getQuantity()%></td>
                    <td><%=product.getPrice() * product.getQuantity()%></td>





                </tr>

            </tbody>    


            <%          }

                    }
                }
            %>  
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>Total: </td>
                <td><%=total%></td>
            </tr>


        </table>
        <div style="margin-top: 20px" class="checkout">
            <form name="CheckOut" action="MainController" method="POST">
                <span>Shipping Address:</span> <input type="text" name="ShipAddress" required=""><br>
                <span>Phone:</span><input type="number" name="Phone" required=""><br>


                <br>

                <span>Payment after delivery</span><input required="" type="radio" name="Payment" value="PayAfterDelivery">
                <span>Pay with PayPal</span><input required="" type="radio" name="Payment" value="Paypal">


                <input type="submit" name="action" required="" value="CheckOut">
                <style>
                    .checkout input {
                        margin: 10px;


                    }

                </style>


            </form>
            <h2 style="text-align: center"><a href="MainController?action=Search&Search=">Continue Shopping</a></h2>
        </div>
    </div>
</body>
</html>
