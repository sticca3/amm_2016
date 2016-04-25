<%-- 
    Document   : descrizione
    Created on : 18-apr-2016, 14.35.35
    Author     : Salvatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"/>
<body>
    <div id="container">
        <div id="header"><h1>Dvd e Bluray online shop</h1></div>
        <nav class="side">
            <h2>Navigazione</h2>
            <a href="login.html">Login</a>
            <ul>
                <li><a href='#descrizione'>Descrizione del sito</a></li>
                <li><a href='#funz_clienti'>Funzionalita per il cliente</a></li>
                <li><a href='#funz_venditori'>Funzionalita per il venditore</a></li>
            </ul>
        </nav>

        <article id="content">
            <section>
                <h2 id='descrizione'>Descrizione sito</h2>
                <p> Il sito permette l'acquiso e la vendita di dvd e bluray di film e serie tv, 
                utilizzando una speiale moneta valida solo all'interno del sito </p>
            </section>
            <section>
                <h3 id='funz_clienti'>Funzionalita per il cliente</h3>
                <p>I clienti potranno sfogliare la lista dei Film e dele serie tv per scegliere quali acquistare.<br>
                Sara possibile fltrare o ordinare gli articoli in base a diverse informazioni, 
                ad esempio il genere o il cast,per trovare cio che piu interessa.<br>
                Si avra inoltre la possibilita di controllare il proprio credito residuo e ricaricarlo.  </p>
            </section>
            <section>    
                <h3 id='funz_venditori'>Funzionalita per il venditore</h3>
                <p>Il venditore avra la possibilita di tenere sotto controllo il suo saldo,
                    scegliere quali prodotti mettere in vendita, in quale quantita e a che prezzo.<br>
                Potra inoltre aliminare gli articoli che non vuole piu vendere.</p>
            </section>        
        </article>
        <jsp:include page="footer.jsp"/>
    </div> 
</body>
</html>