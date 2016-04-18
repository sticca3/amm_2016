<%-- 
    Document   : cliente.jsp
    Created on : 18-apr-2016, 14.34.43
    Author     : Salvatore
--%>

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
            <a href="descrizione.html">Descrizione sito</a>
        </nav>
        <div id="content">
            <table class="content_table">
                <tr>
                    <th id="copertina">Copertina</th>
                    <th id="nome">Nome</th>
                    <th id="num_pezzi">Pezzi disponibili</th>
                    <th id="prezzo">Prezzo</th>
                    <th id="carrello">Carrello</th>
                </tr>
                <tr>
                    <td><img src="images/inside_out.jpg" height="50" width="30" alt="Locandina Inside out"></td>
                    <td>Inside out</td>
                    <td class="col_pezzi">5</td>
                    <td class="col_prezzi">19.90€</td>
                    <td><a href="cliente.html" >Aggiungi al carrello</a></td>
                </tr>
                <tr class="pari">
                    <td><img src="images/maze_runner.jpg" height="50" width="30" alt="Locandina Maze runner"></td>
                    <td>Maze runner</td>
                    <td class="col_pezzi">2</td>
                    <td class="col_prezzi">21.00€</td>
                    <td><a href="cliente.html">Aggiungi al carrello</a></td>
                </tr>
                <tr>
                    <td><img src="images/hallowen.jpg" height="50" width="30" alt="Locandina Tales of halloween"></td>
                    <td>Tales of Halloween</td>
                    <td class="col_pezzi">12</td>
                    <td class="col_prezzi">15.99€</td>
                    <td><a href="cliente.html">Aggiungi al carrello</a></td>
                </tr>
                <tr class="pari">
                    <td><img src="images/crimson_pick.jpg" height="50" width="30" alt="Locandina Crimson pick"></td>
                    <td>Crimson pick</td>
                    <td class="col_pezzi">1</td>
                    <td class="col_prezzi">19.90€</td>
                    <td><a href="cliente.html">Aggiungi al carrello</a></td>
                </tr>
                <tr>
                    <td><img src="images/snoopy.jpg" height="50" width="30" alt="Locandina Snoopy and friends"></td>
                    <td>Snoopy and friends</td>
                    <td class="col_pezzi">15</td>
                    <td class="col_prezzi">12.00€</td>
                    <td><a href="cliente.html">Aggiungi al carrello</a></td>
                </tr>
            </table>
        </div>
        <div id="footer">          
            <a href="#header">Torna su</a>
        </div>
    </div>
</body>
</html>
