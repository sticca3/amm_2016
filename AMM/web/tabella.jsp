<%-- 
    Document   : tabella_cliente
    Created on : 24-apr-2016, 16.19.06
    Author     : Salvatore
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="content_table" id="tabella">
    <tr>
        <th id="copertina">Copertina</th>
        <th id="nome">Nome</th>
        <th id="num_pezzi">Pezzi disponibili</th>
        <th id="prezzo">Prezzo</th>
        <th id="carrello">Carrello</th>
    </tr>

    <c:set var="classe" value="dispari"/>
    <c:forEach var="articolo" items="${articoli}">

        <tr class="${classe}">
            <td> <img src="${articolo.getImage_path()}" height="50" width="30" alt="${articolo.getTitolo()}"></td>
            <td>${articolo.getTitolo()}</td>
            <td class="col_pezzi">${articolo.getNumero()}</td>
            <td class="col_prezzi">${articolo.getPrezzo()}</td>
            <c:if test="${venditore!=true}">
                <td><a href="cliente.html?id=${articolo.getId()}">Aggiungi al carrello</a></td>
            </c:if>
            <c:if test="${venditore==true}">
                <td><a href="venditore.html?operation=update&id=${articolo.id}">Modifica</a>/<a href="venditore.html?operation=delete&id=${articolo.id}">Cancella</a></td>
            </c:if>
        </tr>
        <c:choose>
            <c:when test="${classe!='pari'}">
                <c:set var="classe" value="pari"/>
            </c:when>
            <c:when test="${classe=='pari'}">
                <c:set var="classe" value="dispari"/>
            </c:when>
        </c:choose> 
    </c:forEach> 
</table>
