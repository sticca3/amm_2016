<%-- 
    Document   : form_venditore
    Created on : 24-apr-2016, 16.29.26
    Author     : Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="venditore.html">
   <label for="nomeArticolo" >Nome dell’articolo:</label>
   <input type="text" name="nomeArticolo" id="nomeArticolo" value="${articolo.getTitolo()}">
   <label for="url">Url dell 'immagine:</label>
   <input  type="text" name="url" id="url" value="${articolo.getImage_path()}">
   <label for="descrizione">Descrizione:</label>
   <textarea rows="20" cols="40" name="descrizione" id="descrizione">${articolo.getDescrizione()}</textarea>
   <label for="prezzo">Prezzo:</label>
   <input type="number" name="prezzo" id="prezzo" value="${articolo.getPrezzo()}">
   <label for="quantita">Quantita:</label>
   <input type="number" name="quantita" id="quantita" value="${articolo.getNumero()}">   
   <c:if test="${articolo!=null}">
       <input type="hidden" name="update" value="${articolo.getId()}">
   </c:if>
   <input type="reset" value="Cancella">
   <input type="submit" name="submit"value="Conferma">
</form>