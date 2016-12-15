<%-- 
    Document   : index
    Created on : 06-dic-2016, 18:42:22
    Author     : Angie_
--%>
<%@page import="entities.Angyusers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="indexStyle.css">
        <script src="funcionEvents.js"></script>
        <title>JSP Page</title>
    </head>
    <body>

        <h1>*-- ◘ ¡ Hello Users ! ◙ --*</h1>

        <form method="POST" action="${pageContext.request.contextPath}/ServletLogin" >    

            <lebel> --> Usuario: </lebel>
            <input name="usuario" type="text" id="usuario" placeholder="Ingresa Usuario" autofocus="" required=""><br>
            <br>
            <lebel> --> Contraseña: </lebel>
            <input name="contras" type="password" id="contras" placeholder="Ingresa Password" required=""><br>
            <br>
            <input type="submit" value="Enviar">
            <input type="button" style="margin-left: 20px" onclick="ventanaRegist()" value="Registrarse" >
        </form> <br>
        <br>
        <div id="formregist" style="display:none;">
            <h2>*-- ◘ ¡ Registrate ! ◙ --*</h2>
            <div style="text-align: center;margin-top: 2%;">
                <form id="reg" action="/JPAweb/registro" method="POST">
                    <label> - Usuario: </label><input type="text"  id="usuario" required/><br>
                    <label> - Contraseña: </label><input type="password" id="contras" title="Minimo 1 caracter, 1 letra mayuscula, minuscula y 1 numero."required/><br>
                    <label> - Email: </label><input type="email" name="email" required/><br><br>
                    <input type="submit" value="Enviar">
                </form>
            </div>
        </div>

    </body>
</html>