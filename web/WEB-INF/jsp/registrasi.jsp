<%-- 
    Document   : registrasi
    Created on : Dec 14, 2017, 5:16:38 PM
    Author     : user
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrasi</title>
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
        <script>
            var x = '${data}';
            if(x.length > 0){
                alert('${data}');
            }</script>
    </head>
    <body>
        <div class="container">
            <form:form action="${pageContext.request.contextPath}/register/save" modelAttribute="registerBean" method="POST" >
                <table class="table" style="margin: auto; width: 350px;">
                    <tr>
                        <th colspan="2">
                            <h3>Form Registrasi</h3>
                        </th>
                    </tr>
                    <tr>
                        <td><form:label path="nama">Nama</form:label></td>
                        <td><form:input path="nama" style="width:100%;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="jenisKelamin">Jenis Kelamin</form:label></td>
                        <td><form:radiobutton path="jenisKelamin" value="Laki-laki"/> Laki-Laki &nbsp;
                            <form:radiobutton path="jenisKelamin" value="Perempuan"/> Perempuan</td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email</form:label></td>
                        <td><form:input path="email" style="width:100%;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="tanggalLahir">Tanggal Lahir</form:label></td>
                        <td><form:input type="date" path="tanggalLahir" style="width:100%;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="password">Password</form:label></td>
                        <td><form:password path="password" style="width:100%;" class="pass"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="rePassword">Konfirmasi Password</form:label></td>
                        <td><form:password path="rePassword" style="width:100%;" class="repass"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><a href="${pageContext.request.contextPath}/welcome" class="btn btn-primary" style="margin-left:185px"/>Kembali</a>
                            <form:button name="submitButton" value="Submit" class="btn btn-primary" style="float:right;">Daftar</form:button></td>
                        </tr>
                    </table>
            </form:form>
        </div>
    </body>
</html>
