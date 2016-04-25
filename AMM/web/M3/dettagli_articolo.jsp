<%-- 
    Document   : dettagli_articolo
    Created on : 24-apr-2016, 12.31.56
    Author     : Salvatore
--%>


    
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<ul class="dettagli">
    <li> <h4>Titolo:</h4>${articolo.getTitolo()}</li>
    <li>  <h4>Prezzo:</h4> ${articolo.getPrezzo()}€</li>
    <li>  <h4>Numero:</h4> ${articolo.getNumero()}</li>
    <li>  <h4>Descrizione:</h4> ${articolo.getDescrizione()}</li>
    <li>  <h4>Locandina:</h4> <img height="50" width="30" src="${articolo.getImage_path()}"></li>
    <li>  <h4>Id:</h4> ${articolo.getId()}</li>
    <li>  <h4> Venditore:</h4> ${articolo.getVenditore()}</li>
</ul>      
   
        