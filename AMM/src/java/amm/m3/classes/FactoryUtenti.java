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
public class FactoryUtenti {
    
    private static FactoryUtenti factory;
    private ArrayList<Utente> listaUtenti;
    
    
    public static FactoryUtenti getInstance()throws Exception{
        if(factory==null){
            factory=new FactoryUtenti();
        }
        return factory;
    }
    
    private FactoryUtenti()throws Exception{
        initList();
    }
    
    private void initList() throws Exception{
        listaUtenti=new ArrayList<Utente>();
        //Cliente cliente=new Cliente();
        
        Venditore venditori=new Venditore("mario_rossi","0",new Conto(50),0);
        listaUtenti.add(venditori);
        venditori=new Venditore("francesco_neri","1",new Conto(125.60),40);
        listaUtenti.add(venditori);
        venditori=new Venditore("giovanni84","4",new Conto(0),12);
        listaUtenti.add(venditori);
        
        
        
    }

    public Utente autenticazione(String Username,String password){
        for(Utente u: listaUtenti){
            if(u.getUserName().equals(Username)&&u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<Utente> getListaUtenti(){
        return listaUtenti;
    }
    
    public ArrayList<Venditore> getVenditori(){
        ArrayList<Venditore> venditori=new ArrayList<Venditore>();
        
        for(Utente v: listaUtenti){
            if(v instanceof Venditore)
                venditori.add((Venditore)v);
        }
        return venditori;
    }
    
    public ArrayList<Cliente> getClienti(){
        ArrayList<Cliente> clienti=new ArrayList<Cliente>();
        
        for(Utente c: listaUtenti){
            if(c instanceof Cliente)
                clienti.add((Cliente)c);
        }
        return clienti;
    }  
}
