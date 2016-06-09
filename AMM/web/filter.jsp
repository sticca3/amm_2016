<%-- 
    Document   : filter
    Created on : 1-giu-2016, 17.25.53
    Author     : Salvatore
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="articolo" items="${articoli}">
        <json:object>
            <json:property name="id" value="${articolo.getId()}"/>
            <json:property name="titolo" value="${articolo.getTitolo()}"/>
            <json:property name="prezzo" value="${articolo.getPrezzo()}"/>
            <json:property name="numero" value="${articolo.getNumero()}"/>
            <json:property name="descrizione" value="${articolo.getDescrizione()}"/>
            <json:property name="copertina" value="${articolo.getImage_path()}"/>
            <json:property name="venditore" value="${articolo.getVenditore()}"/>
        </json:object>
    </c:forEach>
</json:array>

