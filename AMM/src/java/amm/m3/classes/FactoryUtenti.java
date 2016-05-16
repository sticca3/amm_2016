/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Salvatore
 */
public class FactoryUtenti {
    
    
    private String connectionString;
    private static FactoryUtenti factory;
    private ArrayList<Utente> listaUtenti;
    public static final int TYPE_CLIENTE=0;
    public static final int TYPE_VENDITORE=1;
    
    public static FactoryUtenti getInstance()throws Exception{
        if(factory==null){
            factory=new FactoryUtenti();
        }
        return factory;
    }
    
    private FactoryUtenti()throws Exception{
        System.out.println("Init user list");
       // initList();
    }
    /*
    private void initList() throws Exception{
        listaUtenti=new ArrayList<Utente>();
        FactoryArticoli articoli=FactoryArticoli.getInstance();
        
        UtenteCliente cliente=new UtenteCliente("giovanni_verdi","2",new Conto(125));
        listaUtenti.add(cliente);
        
        UtenteVenditore venditore=new UtenteVenditore("mario_rossi","0",new Conto(50),0);
        articoli.addArticle("Inside out","Animazione",15,19.9,"images/inside_out.jpg",venditore.getUserName());
        articoli.addArticle("Maze runner","Azione",2,21,"images/maze_runner.jpg",venditore.getUserName());
        articoli.addArticle("Tales of Halloween","Horror",12,15.9,"images/hallowen.jpg",venditore.getUserName());
        listaUtenti.add(venditore);
        
        venditore=new UtenteVenditore("francesco_neri","1",new Conto(125.60),40);
        articoli.addArticle("Crimson pick","Thriller",1,19.9,"images/crimson_pick.jpg",venditore.getUserName());
        articoli.addArticle("Snoopy and friends","Animazione",15,12,"images/snoopy.jpg",venditore.getUserName());
        listaUtenti.add(venditore);
        
        venditore=new UtenteVenditore("giovanni84","4",new Conto(0),12);
        articoli.addArticle("Snoopy and friends","Animazione",5,17.5,"images/snoopy.jpg",venditore.getUserName());
        articoli.addArticle("Maze runner","Azione",21,18.99,"images/maze_runner.jpg",venditore.getUserName());
        listaUtenti.add(venditore);
        
        cliente=new UtenteCliente("mario04","3",new Conto(162));
        listaUtenti.add(cliente);
        
        
        for(Articolo a:articoli.getArticlesList()){
            System.out.println("articolo "+a);
        }
    }
*/
    
    /**
     * Crea un nuovo utente con le informazioni ricevute come parametro.
     * Se è gia presente un utente con username uguale, il nuovo utente non viene creato
     * 
     * @param userName UserName del nuovo utente
     * @param password  Paassword del nuovo utente 
     * @param type  Tipo di utente(Cliente o Venditore)
     * @return true se il nuovo utente viene creato, false altrimenti
     */
    public boolean  addUtente(String userName,String password,int type){
        if(getUtenteByUserName(userName)!=null||(type!=TYPE_CLIENTE&&type!=TYPE_VENDITORE)){
            return false;
        }
        
        try{
            Utente utente=null;
            if(type==TYPE_CLIENTE)
                utente=new UtenteCliente(userName,password,new Conto(0));
            else
                utente=new UtenteVenditore(userName,password,new Conto(0),0);
            
            listaUtenti.add(utente);      
        }catch (Exception e){
            return false;
        }
        return true;
    }
    
    /**Controlla se esiste un utente con username e password uguali a quelle ricevute come parametro.
     * 
     * @param Username Username dell'utente 
     * @param password  Password dell'utente 
     * @return Utente utente se l'autenticaione va a buon fine, null altrimenti
     */
    public Utente autenticazione(String Username,String password){
        Utente u=null;
        System.out.println("autenticazione");
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            Statement statement=conn.createStatement();
            String sql="select * from Utenti where username="+Username+" and password="+password;
            ResultSet result=statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.println(result.getString("username")+result.getString("password"));
                if(result.next()){
                    System.out.println("Errore");
                    System.out.println(result.getString("username")+result.getString("password"));
                    return null;
                }
                u=new Utente(result.getString("username"),result.getString("password"),new Conto(result.getDouble("saldo")));
            }
           
            conn.close();
             return u;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }catch(Exception e){
            return null;
        }
        
        
        
       /* for(Utente u: listaUtenti){
            if(u.getUserName().equals(Username)&&u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    */    
    }
    
    public ArrayList<Utente> getListaUtenti(){
        return listaUtenti;
    }
    
    public ArrayList<UtenteVenditore> getVenditori(){
        ArrayList<UtenteVenditore> venditori=new ArrayList<UtenteVenditore>();
        
        for(Utente v: listaUtenti){
            if(v instanceof UtenteVenditore)
                venditori.add((UtenteVenditore)v);
        }
        return venditori;
    }
    
    public ArrayList<UtenteCliente> getClienti(){
        ArrayList<UtenteCliente> clienti=new ArrayList<UtenteCliente>();
        
        for(Utente c: listaUtenti){
            if(c instanceof UtenteCliente)
                clienti.add((UtenteCliente)c);
        }
        return clienti;
    }  

    public Utente getUtenteByUserName(String userName){
        for(Utente u: listaUtenti){
            if(u.getUserName().equals(userName))
               return u;
        }
        return null;
    }

    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    } 
    



}
