<%-- 
    Document   : listar_publicaciones
    Created on : 22/03/2015, 03:59:03 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP LISTAR PUBLICACIONES</title>
    </head>
    <body>
        <h1>LISTADO PUBLICACIONES</h1>
        <a href="publicaciones.jsp?accion=crear">Guardar</a>
        <table border="1">
            <thead>
            <th>Identificacion publicacion</th>
            <th>Titulo publicacion</th>
            <th>Categoria publicacion</th>
            <th>Contenido publicacion</th>
            
            </thead>
            <tbody>
                <c:forEach items="${publicaciones}" var="publicacion">
                    <tr>
                        <td><c:out value="${publicacion.getId_publicacion()}"/></td>
                        <td><c:out value="${publicacion.getTitulo_publicacion()}"/></td>
                        <td><c:out value="${publicacion.getCategoria_publicacion()}"/></td>
                        <td><c:out value="${publicacion.getContenido_publicacion()}"/></td>
                       
                        <td><a href="./servlet_publicaciones?accion=editar&id_publicacion=<c:out value='${publicacion.getId_publicacion()}'/>">Actualizar</a></td>
                        <td><a href="./servlet_publicaciones?accion=borrar&id_publicacion=<c:out value='${publicacion.getId_publicacion()}'/>">Borrar</a></td>
                      </tr> 
                </c:forEach>
            </tbody>
        </table>
        <a href="servlet_categorias?accion=listarcategorias">Categorias</a>
        <a href="index.jsp">Principal</a>
    </body>
</html>
