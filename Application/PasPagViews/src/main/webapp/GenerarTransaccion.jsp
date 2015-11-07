<%-- 
    Document   : Cuentas
    Created on : 24-oct-2015, 19:23:10
    Author     : GALEANO
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Generar transacción</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá generar una transacción</p>
        <form role="form" method="post" action="TipoCuentasServlet">
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
                    <tr>
                        <td>Monto</td>
                        <td><input type="text" name="monto" value='${request.getAttribute("monto") ? request.getAttribute("monto") : ""}'></td>
                    </tr>
                    <tr>
                        <td>Tipo de transacción</td>
                        <td><input type="text" name="tipo_transaccion" value='${request.getAttribute("tipo_transaccion") ? request.getAttribute("tipo_transaccion") : ""}'></td>
                    </tr>
                    <tr>
                        <td>Estado</td>
                        <td>${request.getAttribute("estado") ? request.getAttribute("estado") : "Pendiente"}</td>
                    </tr>
                    <tr>
                        <td>Cuenta de orígen</td>
                        <td><input type="text" name="cuenta_origen" value='${request.getAttribute("cuenta_origen") ? request.getAttribute("cuenta_origen") : ""}'></td>
                    </tr>
                    <tr>
                        <td>Cuenta de destino</td>
                        <td><input type="text" name="cuenta_destino" value='${request.getAttribute("cuenta_destino") ? request.getAttribute("cuenta_destino") : ""}'</td>
                    </tr>
                    <tr>
                        <td>Descripción</td>
                        <td><input type="text" name="descripcion" value='${request.getAttribute("descripcion") ? request.getAttribute("descripcion") : ""}'/></td>
                    </tr>
                    <tr>
                        <td>Fecha de transacción</td>
                        <td><%
                                Date dNow = new Date( );
                                SimpleDateFormat ft = 
                                new SimpleDateFormat ("E dd-MM-yyyy 'a las' hh:mm:ss a");
                                out.print( "<h2 align=\"center\">" + ft.format(dNow) + "</h2>");
                            %>
                        </td>
                    </tr>
                </table>
                <input class="btn btn-default" type="submit" value="Guardar">
                <a href="TipoCuentasServlet?accion=limpiar" class="btn btn-default">Limpiar</a>
            </div>
        </form>
    </body>
</html>
