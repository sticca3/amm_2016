/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

/**
 *Classe usata per rappresentare i clienti all'interno dell'applicazione
 * @author Salvatore
 */
public class UtenteCliente extends Utente{
    
    public UtenteCliente(String userName, String password, double saldo) throws IllegalArgumentException {
        super(userName, password, saldo);
    }
    
    
    /**Ricerca l'articolo con id passato come parametro nella lista degli articoli e verifica se il credito è sufficente per procedere all'acquisto.
     * se la verifica viene superata si decrementa il credito del cliente e si procede all'acquisto.
     * 
     * @param id id dell'articolo da acquistare
     * @return true se l'aquisto va a buon fine, false se si verifica qualche errore o il credito del cliente non è sufficente per procedere all'acquisto
     */
    public boolean CompraArticolo(int id){
        return FactoryUtenti.getInstance().transazione(id,this.getUserName());
    }
}
