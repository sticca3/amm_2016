<%-- 
    Document   : login
    Created on : 17-apr-2016, 10.16.31
    Author     : Salvatore
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"/>
<body>
    <div id="container">
        <div id="header"><h1>Autenticazione</h1></div>
        <nav class="side">
            <h2>Navigazione</h2>
            <a href="cliente.html" >Area cliente</a>
            <a href="descrizione.html">Descrizione sito</a>
            <a href="venditore.html">Area venditore</a>
        </nav>
        <div id="content">
            <c:if test="${errore!=null}">
                <h2>${errore}</h2>
            </c:if>
            
            <form method="post" action="login.html" >
                <label for="UserName">UserName:</label>
                <input type="text" name="userName" id="userName">
                <label for="Pwd">Password:</label>
                <input type="password" name="pwd" id="pwd">
                <input type="reset" value="Cancella">
                <input type="submit" name="submit" value="Invia">
            </form>
        </div>
        <div id="footer">  
            <a href="#header">Torna su</a>
        </div>
    </div>  
</body>
</html>