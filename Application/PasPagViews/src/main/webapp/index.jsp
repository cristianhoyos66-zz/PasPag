<%-- 
    Document   : index
    Created on : 16-mar-2015, 18:59:39
    Author     : sala303
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <title>Bienvenido</title>
    </head>
    <body>
        <h1>¿Qué desea hacer?</h1>
        <a href="Personas.jsp">Gestionar personas</a><br>
        <a href="Cuentas.jsp">Gestionar cuentas</a><br>
        <a href="Bancos.jsp">Gestionar bancos</a><br>
        <a href="TipoCuentasServlet?accion=listTipoCuentas">Gestionar tipo de cuentas</a>
    </body>
</html>
