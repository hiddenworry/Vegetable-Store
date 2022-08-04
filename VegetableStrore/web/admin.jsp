<%-- 
    Document   : admin.jsp
    Created on : Feb 19, 2022, 10:09:09 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="product.ProductError"%>
<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>

    

        <style>
            td, tr, th {
                text-align: center;
                border: solid 1px;
                padding: 1px;

            }


        </style>
    </head>
    <body>

        <h1>Hello ${sessionScope.USER .getFullName()} </h1>

        <form action="MainController" method="POST">
            <input type="text" name="Search" value="${param.Search}">
            <input type="submit" name="action" value="Search">   
        </form>
        <a href="MainController?action=LogOut">LogOut</a>    
        <button><a href="MainController?action=GetCategory">Insert new Product</a></button>
        <% int count = 1;
            List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
            if (productList != null) {
                if (!productList.isEmpty()) {


        %>
        <table style="margin-top: 20px">
            <thead>
                <tr>
                    <th>Row</th>
                    <th>productID</th>
                    <th>productName</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>categoryID</th>
                    <th>importDate</th>
                    <th>usingDate</th>
                    <th>Image</th>
                    <td>Update</td>
                    <th>Delete</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
         

                <%  String status = "";
                    String color = "";
                    ProductError expiredError = new ProductError();
                    for (ProductDTO product : productList) {
                        // check ngay het han cua san pham
                        String importDate = product.getImportDate();
                        String usingDate = product.getUsingDate();
                        if (!expiredError.isValidDateError(importDate, usingDate)) {
                            status = "Expired";
                            color = "red";

                        } else {
                            status = "Usable";
                            color = "green";
                        }
                %>
                <tr>
                       <form action="MainController" method="POST">
                    <td>
                        <input type="hidden" name="position" value="<%=count%>">

                        <%=count++%>

                    </td>

                    <td> <%=product.getProductID()%>
                        <input type="hidden" name="productID" value="<%=product.getProductID()%>">

                    </td>
                    <td> 
                        <input type="text" value="<%=product.getProductName()%>" name="productName"> 
                        <input type="hidden" name="Search"value="${param.Search}">
                    </td>
                    <td> <input type="number" value="<%=product.getPrice()%>" name="price" step="any"></td>
                    <td> <input type="number" value="<%=product.getQuantity()%>" name="quantity"></td>
                    <td> <%=product.getCategoryID()%></td>
                    <td> <input type="text" value="<%=product.getImportDate()%>" name="importDate"></td>
                    <td> <input type="text" value="<%=product.getUsingDate()%>" name="usingDate"></td>
                    <td> <a href="<%=product.getImageLink()%>" style="text-align: center">  View</a><input name="imagePath" type="text" style="margin-left: 20px"value="<%= product.getImageLink()%>"></td>
                    <td>
                        <input type="submit" name="action" value="Update">
                    </td>

            </form>


            <td>
                <form action="MainController" method="POST">
                    <input type="hidden" name="productID" value="<%=product.getProductID()%>" >
                    <input type="hidden" name="Search"value="${param.Search}">
                    <input type="submit" name="action" value="Delete">
                </form>
            </td>

            <td style="color: <%=color%>"><%=status%></td>

        </tr>

    </tbody>

    <%

                }
            }
        }
    %>


</table>

<br>
<span style="color: red">${requestScope.ERROR.getLine()}</span>
<span style="color: red">${requestScope.ERROR.getProductNameError()}</span>
<span style="color: red">${requestScope.ERROR.getProductPrice()}</span>
<span style="color: red">${requestScope.ERROR.getProductQuantityError()}</span>
<span style="color: red">${requestScope.ERROR.getProductImportDateError()}</span>
<span style="color: red">${requestScope.ERROR.getProductUsingDateError()}</span>
<span style="color: red">${requestScope.ERROR.getInvalidDateError()}</span>
<span style="color: red">${requestScope.ERROR.getImageError()}</span>
</body>
</html>
