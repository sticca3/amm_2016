<%-- 
    Document   : cliente.jsp
    Created on : 18-apr-2016, 14.34.43
    Author     : Salvatore
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"/>
<body>
    <div id="container">
        <div id="header"><h1>Area cliente</h1></div>
        <nav class="side">
            <h2>Navigazione</h2>
            <a href="login.html" >Login</a>
            <a href="descrizione.jsp">Descrizione sito</a>
        </nav>
        <div id="content">
            <c:choose>
               <c:when test="${page==null}">
                    <div id="filter_box">
                       <label for="txt_filter" class="filtro">Filtra:</label>
                       <input onkeyup="filtra()" type="text" id="txt_filtro" class="filtro">
                    </div>
                    <jsp:include page="tabella.jsp"/> 
               </c:when>
               <c:when test="${page=='acquisto'}">
                   <h3>Dettagli articolo</h3>
                   <jsp:include page="dettagli_articolo.jsp"/>
                   <a href="cliente.html?id=${articolo.getId()}&acquista=true"><button value="Acquista">Acquista</button></a>
               </c:when>
               <c:when test="${page=='accesso_negato'}">
                   <jsp:include page="accesso_negato.jsp"/>
               </c:when>
               <c:when test="${page=='errore'}">
                   <h3>${message}</h3>
                   <a href="cliente.html">Torna alla lista degli articoli</a>
               </c:when>      
           </c:choose>
            
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
