<%-- 
    Document   : form_venditore
    Created on : 24-apr-2016, 16.29.26
    Author     : Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="post" action="venditore.html">
   <label for="nomeArticolo">Nome dell’articolo:</label>
   <input type="text" name="nomeArticolo" id="nomeArticolo">
   <label for="url">Url dell 'immagine:</label>
   <input  type="url" name="url" id="url">
   <label for="descrizione">Descrizione:</label>
   <textarea rows="20" cols="40" name="descrizione" id="descrizione"></textarea>
   <label for="prezzo">Prezzo:</label>
   <input type="number" name="prezzo" id="prezzo">
   <label for="quantita">Quantita:</label>
   <input type="number" name="quantita" id="quantita">   
   <input type="reset" value="Cancella">
   <input type="submit" name="submit"value="Aggiungi">
</form>