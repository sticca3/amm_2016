<%-- 
    Document   : header
    Created on : 17-apr-2016, 10.15.38
    Author     : Salvatore
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${titolo==null}">
    <c:set var="titolo" value="Home"/>
</c:if>

<head>
    <title>${titolo}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="AMM, HTML">
    <meta name="description" content="MileStone 1 AMM 2016,Descrizione">
    <meta name="author" content="Ticca Salvatore">
    <link rel="stylesheet" href="style.css" type="text/css" >
    <script type="text/javascript" src="js/filtro.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
</head>

