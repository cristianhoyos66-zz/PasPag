<%-- 
    Document   : Cuentas
    Created on : 24-oct-2015, 19:23:10
    Author     : GALEANO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Listar transacciones</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá ver las transacciones hechas hasta el momento</p>
        <table class = "table">
            <caption>Lista de transacciones</caption>

            <thead>
               <tr>
                  <th>Id</th>
                  <th>Monto</th>
                  <th>Tipo de transacción</th>
                  <th>Estado</th>
                  <th>Cuenta de orígen<th>
                  <th>Cuenta de destino</th>
                  <th>Descripción</th>
                  <th>Fecha de transacción</th>
               </tr>
            </thead>
            <tbody>
                <c:forEach items="${transacciones}" var="transaccion">
                    <tr>
                        <td> <c:out value="${transaccion.id}"/></td>
                        <td> <c:out value="${transaccion.monto}"/></td>
                        <td> <c:out value="${transaccion.tipo}"/></td>
                        <td> <c:out value="${transaccion.estado}"/></td>
                        <td> <c:out value="${transaccion.cuenta_origen}"/></td>
                        <td> <c:out value="${transaccion.cuenta_destino}"/></td>
                        <td> <c:out value="${transaccion.descripcion}"/></td>
                        <td> <c:out value="${transaccion.fecha_transaccion}"/></td>
                        <td> <a class="btn btn-default" href="TipoCuentasServlet?accion=actualizar&id=<c:out value="${tipo.id}"/>&descripcion=<c:out value="${tipo.descripcion}"/>">Modificar</a> </td>
                        <td> <a class="btn btn-default" href="TipoCuentasServlet?accion=eliminar&id=<c:out value="${tipo.id}"/>">Eliminar</a> </td>
                    </tr>
                </c:forEach> 

            </tbody>
        </table>
    </body>
</html>
