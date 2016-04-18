<%-- 
    Document   : venditore
    Created on : 18-apr-2016, 14.35.07
    Author     : Salvatore
--%>

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
           <a href="descrizione.html">Descrizione sito</a>
       </nav>
       <div id="content">
           <form method="get">
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
               <input type="submit" value="Aggiungi">
           </form>
           </div>
           <div id="footer">  
            <a href="#header">Torna su</a>
        </div>
    </div>
</body>
</html>
