/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

import java.util.ArrayList;

/**
 *Questa classe si occupa di gestire la lista degli articoli in vendita
 * @author Salvatore
 */
public class FactoryArticoli {
    
    private static FactoryArticoli factory;
    private ArrayList<Articolo> listaArticoli;
    private int counter;
    
    public static FactoryArticoli getInstance(){
        if(factory==null){
            factory=new FactoryArticoli();
        }
         
        return factory;    
    }
    
    private FactoryArticoli(){
        System.out.println("Init article list");
        listaArticoli=new ArrayList<Articolo>();
    }
    
    /**Rimuove dalla lista l'articolo con id ricevuto come parametro
     * 
     * @param id id dell'articolo da rimuovere
     */
    public void removeArticle(int id){
        Articolo articolo=getArticleById(id);
        if(articolo!=null)
        {
            listaArticoli.remove(articolo);
        }
    }
    
    /**
     * 
     * @param titolo Titolo del film
     * @param descrizione   breve descrizione dell'articolo
     * @param numero numero di copie messe in vendita
     * @param prezzo prezzo dell'articolo
     * @param path path dell'immagine della locandina
     * @param venditore Username dell'utente che ha messo in vendita questo articolo
     * @return 
     * id dell'articolo inserito se l'inserimento va a buon fine.
     * -1 se si presenta un errore,
     * -2 se l'articolo e gia presente nella lista
     */
    public int addArticle(String titolo,String descrizione,int numero,double prezzo,String path,String venditore){ 
        try{
            Articolo articolo=new Articolo(titolo,descrizione,numero,prezzo,path,venditore,counter++);
            for(Articolo art:getArticlesByVenditore(venditore)){
                if(art.equals(articolo)){
                    return -2;
                }
            }
            listaArticoli.add(articolo);    
        }catch(Exception e){
            return -1;
            
        }
        return counter-1;
    }
    
    public ArrayList<Articolo> getArticleByPrezzo(double minPrice,double maxPrice){
        ArrayList<Articolo> lista=new ArrayList<Articolo>();
        for(Articolo art:listaArticoli){
            if(art.getPrezzo()<=maxPrice && art.getPrezzo()>=minPrice){
                lista.add(art);
            }
        }
        return lista;
    }
    
     public Articolo getArticleById(int id){
         for(Articolo art:listaArticoli){
            if(art.getId()==id){
                return art;
            }
        }
        return null;
    }
    
    public ArrayList<Articolo> getArticleByTitolo(String titolo){
        ArrayList<Articolo> lista=new ArrayList<Articolo>();
        for(Articolo art:listaArticoli){
            if(art.getTitolo().equals(titolo)){
                lista.add(art);
            }
        }
        return lista;
    }
    
    public ArrayList<Articolo> getArticlesByVenditore(String userName){
        ArrayList<Articolo> lista=new ArrayList<Articolo>();
        for(Articolo art:listaArticoli){
            if(art.getVenditore().equals(userName)){
                lista.add(art);
            }
        }
        return lista; 
    }
    
    public ArrayList<Articolo> getArticlesList(){
        return listaArticoli;
    }
    
}
