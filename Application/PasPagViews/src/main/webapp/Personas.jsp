<%-- 
    Document   : Personas
    Created on : 24-oct-2015, 19:22:46
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
        <title>Personas</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá gestionar los usuarios de la pasarela de pagos</p>
        <form role="form" method="post" action="PersonaNaturalServlet">
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <tr>
                        <td>Documento</td>
                        <td><input type="text" name="documento_persona" value="<%=request.getAttribute("documento_persona")%>"></td>
                    </tr>
                    <tr>
                        <td>Tipo documento</td>
                        <td>                    
                            <select name="tipo_seleccionado" value="<%=request.getAttribute("tipo_seleccionado")%>">
                                <c:forEach items="${tipodoc}" var="tipo">
                                    <option value="${tipo}">${tipo}</option>
                                </c:forEach> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Nombre y apellidos</td>
                        <td><input type="text" name="nombre_persona" value="<%=request.getAttribute("nombre_persona")%>"></td>
                    </tr>
                    <tr>
                        <td>Teléfono de contacto</td>
                        <td><input type="text" name="contacto_persona" value="<%=request.getAttribute("contacto_persona")%>"></td>
                    </tr>
                    <tr>
                        <td>Correo electrónico</td>
                        <td><input type="text" name="correo_persona" value="<%=request.getAttribute("correo_persona")%>"></td>
                    </tr>
                    <tr>
                        <td>Dirección</td>
                        <td><input type="text" name="direccion_persona" value="<%=request.getAttribute("direccion_persona")%>"></td>
                    </tr>
                    <tr>
                        <td>Usuario</td>
                        <td><input type="text" name="usuario" value="<%=request.getAttribute("usuario")%>"></td>
                    </tr>
                    <tr>
                        <td>Contraseña</td>
                        <td><input type="text" name="contrasena" value="<%=request.getAttribute("contrasena")%>"></td>
                    </tr>
                    <tr>
                        <td>Pais de nacimiento</td>
                        <td><input type="text" name="pais_nacimiento" value="<%=request.getAttribute("pais_nacimiento")%>"></td>
                    </tr>
                    <tr>
                        <td>Ciudad de nacimiento</td>
                        <td><input type="text" name="ciudad_nacimiento" value="<%=request.getAttribute("ciudad_nacimiento")%>"></td>
                    </tr>
                    <tr>
                        <td>Fecha de nacimiento</td>
                        <td><input type="text" name="fecha_nacimiento" value="<%=request.getAttribute("fecha_nacimiento")%>"></td>
                    </tr>
                </table>
                <input class="btn btn-default" type="submit" value="Guardar">
                <a href="PersonaNaturalServlet?accion=limpiar" class="btn btn-default">Limpiar</a>
            </div>  
            <table class = "table">
                <caption>Lista de personas</caption>

                <thead>
                    <tr>
                        <th>Documento</th>
                        <th>Tipo de documento</th>
                        <th>Nombre</th>
                        <th>Contacto</th>
                        <th>Correo</th>
                        <th>Dirección</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaPersonas}" var="persona">
                        <tr>
                            <td> <c:out value="${persona.personaPK.documento}"/></td>
                            <td> <c:out value="${persona.personaPK.tipoDocumento}"/></td>
                            <td> <c:out value="${persona.nombre}"/></td>
                            <td> <c:out value="${persona.contacto}"/></td>
                            <td> <c:out value="${persona.correo}"/></td>
                            <td> <c:out value="${persona.direccion}"/></td>
                            <td> <c:out value="${persona.usuario}"/></td>
                            <td> <a class="btn btn-default" href="PersonaNaturalServlet?accion=set_data&documento=<c:out value="${persona.personaPK.documento}"/>&tipo_documento=<c:out value="${persona.personaPK.tipoDocumento}"/>">Modificar</a> </td>
                            <td> <a class="btn btn-default" href="PersonaNaturalServlet?accion=eliminar&id=<c:out value="${tipo.id}"/>">Eliminar</a> </td>
                        </tr>
                    </c:forEach> 

                </tbody>
            </table>
        </form>
    </body>
</html>
