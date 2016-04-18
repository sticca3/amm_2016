/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

import java.util.ArrayList;

/**
 *
 * @author Salvatore
 */
public class FactoryArticoli {
    
    private static FactoryArticoli factory;
    private ArrayList<Articolo> listaArticoli;
    
    public static FactoryArticoli getInsantce()throws Exception{
        if(factory==null){
            factory=new FactoryArticoli();
        }
         
        return factory;    
    }
    
    private FactoryArticoli()throws Exception{
        createList();
    }
    
    private void createList() throws Exception{
   /*     listaArticoli=new ArrayList<Articolo>();
        Articolo tmp=new Articolo("Inside out",5,19.9,"images/inside_out.jpg");
        listaArticoli.add(tmp);
        tmp=new Articolo("Maze runner",2,21,"images/maze_runner.jpg");
        listaArticoli.add(tmp);
        tmp=new Articolo("Tales of halloween",12,15.99,"images/halloween.jpg");
        listaArticoli.add(tmp);
        tmp=new Articolo("Crimson pick",1,19.9,"images/crimson_pick.jpg");
        listaArticoli.add(tmp);
        tmp=new Articolo("Snoopy and friends",15,12,"images/snoopy.jpg");
        listaArticoli.add(tmp);
    */}
    
    public ArrayList<Articolo> getArticleByPrice(double minPrice,double maxPrice){
        ArrayList<Articolo> lista=new ArrayList<Articolo>();
        for(Articolo art:listaArticoli){
            if(art.getPrezzo()<=maxPrice && art.getPrezzo()>=minPrice){
                lista.add(art);
            }
        }
        return lista;
    }
    
    public Articolo getArticleByTitle(String titolo){
         for(Articolo art:listaArticoli){
            if(art.getTitolo().equals(titolo)){
                return art;
            }
        }
        return null;
    }
    
    public ArrayList<Articolo> getArticlesBySeller(String userName){
        
        return listaArticoli;
    }
    
    public ArrayList<Articolo> getArticlesList(){
        return listaArticoli;
    }
    
}
