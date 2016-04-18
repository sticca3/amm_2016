/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

/**
 *
 * @author Salvatore
 */
public class Articolo {
 
    private String titolo;
    private double prezzo;
    private int numero;
    private String image_path;
    private String venditore;
    private String descrizione;
    
    public Articolo(String titolo,String descrizione,int numero,double prezzo,String path,String venditore)throws Exception{
        
         if(prezzo<=0||numero<0||titolo==null||path==null||venditore==null)
            throw new Exception("Errore nei dati inseriti.");
        
        this.titolo=titolo;
        this.prezzo=prezzo;
        this.numero=numero;
        this.image_path=path;
        
        if(descrizione==null||descrizione.equals("")){
            this.descrizione="Nessuna descrizione";
        }else
            this.descrizione=descrizione;
        
        
        //controllo specifico per il venditore...
        this.venditore=venditore;
    }

    public String getVenditore(){
        return venditore;
    }
    
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo)throws Exception {
        if(titolo!=null)
            this.titolo = titolo;
        else
            throw new Exception("Errore nei dati inseriti.");
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) throws Exception{
        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new Exception("Errore nei dati inseriti.");
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws Exception{
        if(numero>0)
            this.numero = numero;
        else
            throw new Exception("Errore nei dati inseriti.");
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) throws Exception{
        if(image_path!=null)
            this.image_path = image_path;
        else
            throw new Exception("Errore nei dati inseriti.");
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

}
