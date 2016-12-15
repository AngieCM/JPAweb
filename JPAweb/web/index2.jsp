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

        <h1>*-- ◘ ¡ Registrarse ! ◙ --*</h1>

        <form method="POST" action="${pageContext.request.contextPath}/ServletRegistro" >    

            <lebel> --> Usuario: </lebel>
            <input name="usuario" type="text" id="usuario" placeholder="Ingresa Usuario" autofocus="" required=""><br>
            <br>
            <lebel> --> Contraseña: </lebel>
            <input name="contras" type="password" id="contras" placeholder="Ingresa Password" required=""><br>
            <br>
            <input type="submit" value="Entrar">
        </form>

    </body>
</html>