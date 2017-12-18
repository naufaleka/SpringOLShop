<%-- 
    Document   : DetailProduk
    Created on : Dec 17, 2017, 7:02:31 PM
    Author     : user
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Product</title>
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="jumbotron" style="height: 100px;">
            <h1 style="margin-top: -35px;">${user}</h1>
            <div style="margin-right: 25px; float: right; margin-top: -35px;">
                <a href="${pageContext.request.contextPath}/welcome/logout" class="btn btn-primary" style="margin-right: 25px; float: right;"/>Logout</a>
                <a href="#"><img src="<c:url value="/resources/image/cart.png"/>" style="width: 40px; height: 40px; margin-right: 50px; margin-top: -4px; float: right;"/><b style=" font-size: 25px; margin-right: -55px; float: right;">0</b></a>
            </div>
        </div>
        <div style="width:750px; margin:auto;">
            <table class="table table-bordered">
                <tr>
                    <th colspan="2"><h1 style="margin: auto;">${produk.productName}</h1></th>
                </tr>
                <tr>
                    <td><img src="<c:url value="/resources/image/${produk.picture}"/>" width="100%" height="100%"/></td>
                    <td style="width: 450px;"><b style="font-size: 18px;">Deskripsi : </b><br/>${produk.description}</td>
                </tr>
                <tr>
                    <td colspan="2"><b style="float: right;">Rp. ${produk.productPrice}</b></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="${pageContext.request.contextPath}/products" class="btn btn-default"/>Kembali</a><a href="${pageContext.request.contextPath}/${produk.id}" class="btn btn-default ico" style="float: right;">add to cart&nbsp;</a></td>
                </tr>
            </table>
        </div>
    </body>
</html>
