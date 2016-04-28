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
    
    public UtenteVenditore(String userName, String password, Conto saldo,double feedback) throws Exception {
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
        if(feedback>=0&&feedback<=100)
            this.feedback=feedback;
    }
    
    /**Cerca l'articolo con l'id passato come parametro nella lista degli articoli, e
     * incrementa il saldo del venditore nel caso in cui la vendita vada a buon fine.
     * Se il numero di copie dell'articolo venduto raggiunge lo 0, viene rimosso dalla lista.
     * 
     * @param id id dell'articolo da vendere
     * @return true se l'articolo e stato venduto, false se l'articolo non viene trovato o si verifica qualche errore
     */
    public boolean vendiArticolo(int id){
        FactoryArticoli factory=FactoryArticoli.getInstance();
        Articolo articolo=factory.getArticleById(id);
        
        if(articolo!=null){
            try{
                if(articolo.getNumero()<2){
                    rimuoviArticolo(id);
                }else
                    articolo.setNumero(articolo.getNumero()-1);
            }catch(Exception e){
                return false;
            }
            saldo.addMoney(articolo.getPrezzo());
            return true;
        }
        return false;
    }
    
    /**
     * Rimuove dalla lista degli articoli quello con l'id ricevuto come parametro
     * @param id id dell'articolo da rimuovere
     */
    public void rimuoviArticolo(int id){
        FactoryArticoli.getInstance().removeArticle(id);
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
    public int addArticolo(String titolo,String descrizione,int numero,double prezzo,String path){
        return FactoryArticoli.getInstance().addArticle(titolo, descrizione, numero, prezzo, path, getUserName());  
    }
    
    
}
