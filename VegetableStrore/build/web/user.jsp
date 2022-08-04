<%-- 
    Document   : Home
    Created on : Jan 26, 2022, 9:40:15 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        Bootrap link-->

      
        <c:if test="${sessionScope.USER == null or (sessionScope.USER.isAdmin()==true) }">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
       
       


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/homestyles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Organic Store</title>

    </head>
    <body>

        <div class="container-fluid">

            <div class="Shopping-icon">
                <i class="fa fa-shopping-cart" style="font-size:50px"></i>
                <a id="view-cart-btn" href="viewcart.jsp">View Cart</a>

            </div>

            <div class="nav-bar container-fluid row">

                <div class="logo col-md-2">
                    <a href=""> ORGANIC </a>
                </div>
                <div class="search col-md-7">

                    <form action="MainController">
                        <input class="input-search" type="text" value="${Search}"name="Search" placeholder="Nhập sản phẩm bạn muốn mua"/>
                        <input type="submit" name="action" value="Search"/>
                    </form>
                </div>
                <div class="login col-md-2"> 


                    <a href="####"> ${sessionScope.USER.getFullName()}</a>
                </div>
                <div class="sign-up col-md-1">
                    <a href="MainController?action=LogOut">Log Out</a>
                </div>

            </div>

            <!-- Main -->
            <div class="container shopping-cart">
                <div class="row">
                    <c:forEach var="product" items="${requestScope.PRODUCT_LIST}">

                        <div class="card" style="width: 20rem;">
                            <img class="card-img-top" src="${product.imageLink}" alt="Card image">
                            <div class="card-block">
                                <form method="POST" action="MainController">
                                    <h4 class="card-title">${product.productName}</h4>
                                    <p class="card-text">Price: ${product.price}</p>
                                    <input hidden="" value="${product.productID}" type="text" name="productID">
                                    <input hidden="" value="1" type="number" name="quantity">
                                    <input  type="submit" name="action" value="AddToCart" class="add-to-cart btn btn-primary">
                                    <input type="text"  hidden="" name="Search" value="${Search}">
                                </form>
                            </div>
                        </div>
                    </c:forEach>




                    <div style="color: graytext">${requestScope.ERROR}</div>
                </div>
            </div>
        </div> 

    </style>

</div>


<!--        Bootrap link-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
