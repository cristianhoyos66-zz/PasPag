<%-- 
    Document   : Cuentas
    Created on : 24-oct-2015, 19:23:10
    Author     : GALEANO
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
        <title>Cuenta</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección se podrá gestionar una cuenta.</p>
        <form role="form" method="post" action="CuentasServlet">
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <tr>
                        <td>Número de cuenta</td>
                        <td><input type="text" name="numero_cuenta" value=""></td>
                    </tr>
                    <tr>
                        <td>Banco</td>
                        <td><input type="text" name="banco" value=""></td>
                    </tr>
                    <tr>
                        <td>Tipo de cuenta</td>
                        <td><input type="text" name="tipo_cuenta" value=""></td>
                    </tr>
                    <tr>
                        <td>Válido desde</td>
                        <td><input type="text" name="valido_desde" value=""></td>
                    </tr>
                    <tr>
                        <td>Válido hasta</td>
                        <td><input type="text" name="valido_hasta" value=""></td>
                    </tr>
                </table>
                <input class="btn btn-default" type="button" value="Consultar">
                <input class="btn btn-default" type="submit" value="Guardar">
            </div>  
        </form>
    </body>
</html>
