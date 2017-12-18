<%-- 
    Document   : welcome
    Created on : Dec 14, 2017, 11:33:07 AM
    Author     : user
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indocyber OL Shop</title>
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
        <script>
            var x = '${data}';
            if(x.length > 0){
                alert('${data}');
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1 align="center">${msg}</h1>
            </div>
            <div class="card">
                <div class="card-header">
                    <h2 style="margin: auto; width: 300px; height: 50px;">Login Form</h2>
                </div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/welcome/product" modelAttribute="loginBean" method="POST" >
                        <table class="table table-bordered" style="margin: auto; width: 300px;">
                            <tr>
                                <td><form:label path="email">Email</form:label></td>
                                <td><form:input path="email" style="width:100%"/></td>
                            </tr>
                            <tr>
                                <td><form:label path="password">Password</form:label></td>
                                <td class="btn-"><form:password path="password" style="width:100%"/></td>
                            </tr>
                            <tr><td colspan="2"><a href="${pageContext.request.contextPath}/register" class="btn btn-primary" style="margin-left:140px"/>Register</a></a><form:button name="submitButton" value="Submit" class="btn btn-primary" style="float:right;">Login</form:button></td></tr>
                        </table>  
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
