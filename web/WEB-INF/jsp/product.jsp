<%-- 
    Document   : product
    Created on : Dec 15, 2017, 10:46:58 AM
    Author     : user
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="jumbotron">
            <h1>Hello ${user}</h1>
            <a href="${pageContext.request.contextPath}/welcome/logout" class="btn btn-primary" style="margin-right: 25px; float: right;"/>Logout</a>
        <a href="#"><img src="<c:url value="/resources/image/cart.png"/>" style="width: 40px; height: 40px; margin-right: 50px; margin-top: -4px; float: right;"/><b style=" font-size: 25px; margin-right: -55px; float: right;">${cart}</b></a>
    </div>
    <div style="margin-left: 20px;"><h2>Produk Di Toko Kami</h2></div>
    <div style="margin-left: 20px; margin-right: 20px;">
        <c:forEach var="p" items="${products}">
            <div class="col-sm-3">
                <table class="table table-bordered">
                    <tr>
                        <th><p style="margin: auto;">${p.productName}</p></th>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/products/${p.id}"><img src="<c:url value="/resources/image/${p.picture}"/>" style="margin: auto;"/></a></td>
                    </tr>
                    <tr>
                        <td><b style="float: right;">Rp. ${p.productPrice}</b></td>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/order/${p.id}" class="btn btn-default ico" style="margin-left: 70px;">add to cart&nbsp;</a></td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
</body>
</html>
