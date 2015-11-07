<%-- 
    Document   : Bancos
    Created on : 24-oct-2015, 19:23:19
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
        <title>Banco</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <p>En esta sección usted podra gestionar su banco.</p>
        <form>
            <div class="table-responsive">
                <table  class="table table-bordered">
                    <tr>
                        <td>NIT</td>
                        <td><input type="text" name="nit_banco" value=""></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre_banco" value=""></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="correo_banco" value=""></td>
                    </tr>
                </table>
                <input class="btn btn-default" type="submit" value="Consultar">
                <input class="btn btn-default" type="submit" value="Guardar">
            </div>  
        </form>
    </body>
</html>
