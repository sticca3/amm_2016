<%-- 
    Document   : venditore
    Created on : 18-apr-2016, 14.35.07
    Author     : Salvatore
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"/>
<body>
    <div id="container">
        <div id="header"><h1>Area venditore</h1></div>
        <nav class="side">
            <h2>Navigazione</h2>
           <a href="login.html" >Login</a>
           <a href="descrizione.jsp">Descrizione sito</a>
       </nav>
       <div id="content">
           <!--Sceglie il contenuto da mostrare in base al parametro passato dalla servlet-->
           <c:choose >
               <c:when test="${page=='aggiunta'}">
                   <h3>L'articolo e stato inserito corretamente.</h3>
                   <jsp:include page="dettagli_articolo.jsp"/>
                   <a href="venditore.html">Torna al form di inserimento</a>
               </c:when>
               <c:when test="${page=='accesso_negato'}">
                   <jsp:include page="accesso_negato.jsp"/>
               </c:when>
               <c:when test="${page=='errore'}">
                   <h3>${message}</h3>
                   <a href="venditore.html">Torna al form di inserimento</a>
               </c:when>
               <c:when test="${page==null}">
                   <jsp:include page="form_venditore.jsp"/> 
               </c:when>
           </c:choose>
       </div>
       <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
