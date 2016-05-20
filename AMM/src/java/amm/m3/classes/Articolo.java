/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

/**
 *Classe usata per rappresentare gli articoli all'interno dell'applicazione
 * @author Salvatore
 */
public class Articolo {
 
    private String titolo;
    private double prezzo;
    private int numero;
    private String image_path;
    private String venditore;
    private String descrizione;
    private int id;
    
    public Articolo(String titolo,String descrizione,int numero,double prezzo,String path,String venditore,int id)throws IllegalArgumentException{
        
        if(prezzo<=0||numero<0||titolo==null||titolo.equals("")||path==null||venditore==null||id<0)
            throw new IllegalArgumentException("Errore nei dati inseriti.");
        
        
        this.titolo=titolo;
        this.prezzo=prezzo;
        this.numero=numero;
        
        if(path.equals(""))
            this.image_path="images/no_image.jpg";
        else
            this.image_path=path;
        
        this.id = id;
        
        if(descrizione==null||descrizione.equals("")){
            this.descrizione="Nessuna descrizione";
        }else
            this.descrizione=descrizione;
         
        this.venditore=venditore;
    }

    public String getVenditore(){
        return venditore;
    }
    
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo)throws IllegalArgumentException {
        if(titolo!=null)
            this.titolo = titolo;
        else
            throw new IllegalArgumentException("Errore nei dati inseriti.");
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) throws IllegalArgumentException{
        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Errore nei dati inseriti.");
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws IllegalArgumentException{
        if(numero>0)
            this.numero = numero;
        else
            throw new IllegalArgumentException("Errore nei dati inseriti.");
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) throws IllegalArgumentException{
        if(image_path!=null)
            this.image_path = image_path;
        else
            throw new IllegalArgumentException("Errore nei dati inseriti.");
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        if(descrizione==null||descrizione.equals("")){
            this.descrizione="Nessuna descrizione";
        }else
             this.descrizione=descrizione;      
    }
     
    public int getId() {
        return id;
    }

    /**
     * Controlla se l'oggeto passato come parametro rappresenta lo stesso articolo rappresentato da questo oggetto.
     * Due articoli sono considerati uguali se hanno uguale: Titolo,descrizione,prezzo e se sono stati messi in vendita dallo stesso utente
     * @param object
     * @return true se i due oggetti rappresentano lo stesso articolo, false altrimeti
     */
    
    @Override 
    public boolean equals(Object object){
        if(object==null||this.getClass()!=object.getClass()){
            return false;
        }
        Articolo articolo=(Articolo)object;
        if(articolo.getTitolo().equals(titolo)&&articolo.getVenditore().equals(venditore)&&articolo.getPrezzo()==prezzo&&articolo.getDescrizione().equals(descrizione))
        {
            return true;
        }
        return false;
    }
    

}
