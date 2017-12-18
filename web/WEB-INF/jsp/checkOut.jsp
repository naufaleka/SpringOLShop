<%-- 
    Document   : checkOut
    Created on : Dec 18, 2017, 12:01:26 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 class="jumbotron">Hello ${user}</h1>
        <table class="table table-striped">
            <tr>
                <th>Product ID</th>
                <th>Product</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
            </tr>
            <c:forEach var="p" items="${produk}">
                <tr>
                    <td>${p.idBarang}</td>
                    <td><img src="<c:url value="/resources/image/${p.image}"/>" style="margin: auto; width: 150px; height: 150px;"/></td>
                    <td>${p.productName}</td>
                    <td>${p.price}</td>
                    <td>${p.jumlahBeli}</td>
                    <td><b>${p.jumlahBeli * p.price}</b></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <a href="${pageContext.request.contextPath}/products" class="btn btn-default" style="float: right;">Kembali Belanja</a>
                </td>
            </tr>
        </table>
    </body>
</html>
