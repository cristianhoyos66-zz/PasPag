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
        <title>Tipos de cuenta</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá gestionar los tipos de cuenta.</p>
        <form role="form" method="post" action="TipoCuentasServlet">
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
                    <tr>
                        <td>Descripción</td>
                        <td><input type="text" name="descripcion" value='<%=request.getAttribute("descripcion")%>'></td>
                    </tr>
                </table>
                <input class="btn btn-default" type="submit" value="Guardar">
                <a href="TipoCuentasServlet?accion=limpiar" class="btn btn-default">Limpiar</a>
            </div>
            <table class = "table">
                <caption>Lista de tipos de cuenta</caption>

                <thead>
                   <tr>
                       <th>Id</th>
                      <th>Descripción</th>
                   </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tipoCuentas}" var="tipo">
                        <tr>
                            <td> <c:out value="${tipo.id}"/></td>
                            <td> <c:out value="${tipo.descripcion}"/></td>
                            <td> <a class="btn btn-default" href="TipoCuentasServlet?accion=actualizar&id=<c:out value="${tipo.id}"/>&descripcion=<c:out value="${tipo.descripcion}"/>">Modificar</a> </td>
                            <td> <a class="btn btn-default" href="TipoCuentasServlet?accion=eliminar&id=<c:out value="${tipo.id}"/>">Eliminar</a> </td>
                        </tr>
                    </c:forEach> 
                   
                </tbody>
            </table>
        </form>
    </body>
</html>
