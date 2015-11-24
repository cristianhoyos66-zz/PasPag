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
        <title>Cuentas</title>
    </head>
    <body>
        <p><a href="index.jsp">Regresar al menú</a></p>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá gestionar las cuentas de la pasarela de pagos.</p>
        <form role="form" method="post" action="CuentasServlet">
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <input type="hidden" name="conditional" value="<%=request.getAttribute("conditional")%>">
                    <tr>
                        <td>Número de cuenta</td>
                        <td><input type="text" name="numero_cuenta" value="<%=request.getAttribute("numero_cuenta")%>"></td>
                    </tr>
                    <tr>
                        <td>Saldo</td>
                        <td><input type="text" name="saldo" value="<%=request.getAttribute("saldo")%>"></td>
                    </tr>
                    <tr>
                        <td>Válido desde</td>
                        <td><input type="text" name="valido_desde" value="<%=request.getAttribute("valido_desde")%>"></td>
                    </tr>
                    <tr>
                        <td>Válido hasta</td>
                        <td><input type="text" name="valido_hasta" value="<%=request.getAttribute("valido_hasta")%>"></td>
                    </tr>
                    <tr>
                        <td>Tipo de cuenta</td>
                        <td>
                            <select name="tipo_cuenta_seleccionada" value="<%=request.getAttribute("tipo_cuenta_seleccionada")%>">
                                <option value="${empty tipo_cuenta_seleccionada ? null : tipo_cuenta_seleccionada.id}" selected>${empty tipo_cuenta_seleccionada ? null : tipo_cuenta_seleccionada.descripcion}</option>
                                <c:forEach items="${tipo_cuentas}" var="tipo">
                                    <c:if test="${tipo != tipo_cuenta_seleccionada}">
                                        <option value="${tipo.id}">${tipo.descripcion}</option>
                                    </c:if>
                                </c:forEach> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Empresa</td>
                        <td>
                            <select name="empresa_seleccionada" value="<%=request.getAttribute("empresa_seleccionada")%>">
                                <option value="${empty empresa_seleccionada ? null : empresa_seleccionada.personaPK}" selected>${empty empresa_seleccionada ? null : empresa_seleccionada.nombre}</option>
                                <c:forEach items="${empresas}" var="empresa">
                                    <c:if test="${empresa != empresa_seleccionada}">
                                        <option value="${empresa.personaPK}">${empresa.nombre}</option>
                                    </c:if>
                                </c:forEach> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Persona</td>
                        <td>
                            <select name="persona_seleccionada" value="<%=request.getAttribute("persona_seleccionada")%>">
                                <option value="${empty persona_seleccionada ? null : persona_seleccionada.personaPK}" selected>${empty persona_seleccionada ? null : persona_seleccionada.nombre}</option>
                                <c:forEach items="${personas}" var="persona">
                                    <c:if test="${persona != persona_seleccionada}">
                                        <option value="${persona.personaPK}">${persona.nombre}</option>
                                    </c:if>
                                </c:forEach> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Entidad bancaria</td>
                        <td>
                            <select name="entidad_seleccionada" value="<%=request.getAttribute("entidad_seleccionada")%>">
                                <option value="${empty entidad_seleccionada ? null : entidad_seleccionada.personaPK}" selected>${empty entidad_seleccionada ? null : entidad_seleccionada.nombre}</option>
                                <c:forEach items="${entidades_bancarias}" var="entidad">
                                    <c:if test="${entidad != entidad_seleccionada}">
                                        <option value="${entidad.personaPK}">${entidad.nombre}</option>
                                    </c:if>
                                </c:forEach> 
                            </select>
                        </td>
                    </tr>
                </table>
                <input class="btn btn-default" type="submit" value="Guardar">
                <a href="CuentasServlet?accion=limpiarRedireccionar" class="btn btn-default">Limpiar</a>
            </div> 
            <table class = "table">
                <caption>Lista de personas</caption>

                <thead>
                    <tr>
                        <th>Número de cuenta</th>
                        <th>Saldo</th>
                        <th>Válido desde</th>
                        <th>Válido hasta</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaCuentas}" var="cuenta">
                        <tr>
                            <td> <c:out value="${cuenta.numero_cuenta}"/></td>
                            <td> <c:out value="${cuenta.saldo}"/></td>
                            <td> <c:out value="${cuenta.valido_desde}"/></td>
                            <td> <c:out value="${cuenta.valido_hasta}"/></td>
                            <td> <a class="btn btn-default" href="CuentasServlet?accion=set_data&conditional=<c:out value="1"/>&numero_cuenta=<c:out value="${cuenta.numero_cuenta}"/>">Modificar</a> </td>
                            <td> <a class="btn btn-default" href="CuentasServlet?accion=eliminar&numero_cuenta=<c:out value="${cuenta.numero_cuenta}"/>">Eliminar</a> </td>
                        </tr>
                    </c:forEach> 

                </tbody>
            </table>                   
        </form>
    </body>
</html>
