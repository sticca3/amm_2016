/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

/**
 *Classe usata per rappresentare i venditori all'interno dell'applicazione
 * @author Salvatore
 */
public class UtenteVenditore extends Utente {
    
    private double feedback;
    
    public UtenteVenditore(String userName, String password, double saldo,double feedback) throws IllegalArgumentException {
        super(userName, password, saldo);
        
        if(feedback>=0&&feedback<=100)
            this.feedback=feedback;
        else
            this.feedback=0;
    }
    
    public double getFeedback(){
        return feedback;
    }
    
    public void setFeedback(double feedback){
        double old=this.feedback;
        if(feedback>=0&&feedback<=100){
            this.feedback=feedback;
            if(!FactoryUtenti.getInstance().updateUtente(this))
                this.feedback=old;     
        }
    }
    
   
    
    /**
     * Rimuove dalla lista degli articoli quello con l'id ricevuto come parametro
     * @param id id dell'articolo da rimuovere
     */
    public boolean rimuoviArticolo(int id){
        return FactoryArticoli.getInstance().removeArticle(id);
    }
    
    /**
     * Aggiunge alla lista degli articoli un nuovo articolo con i dati ricevuti come parametro
     * @param titolo Titolo del film
     * @param descrizione   breve descrizione dell'articolo
     * @param numero numero di copie messe in vendita
     * @param prezzo prezzo dell'articolo
     * @param path path dell'immagine della locandina
     * @return id dell'articolo inserito
     */
    public Articolo addArticolo(String titolo,String descrizione,int numero,double prezzo,String path) throws ArticoloEsistenteException{
        return FactoryArticoli.getInstance().addArticle(titolo, descrizione, numero, prezzo, path, getUserName());     
    }
    
    public Articolo updateArticolo(String titolo,String descrizione,int numero,double prezzo,String path,int id){
        return FactoryArticoli.getInstance().updateArticle(titolo, descrizione, numero, prezzo, path, getUserName(),id);     
    }
    
}
