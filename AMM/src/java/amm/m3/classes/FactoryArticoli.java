/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Questa classe si occupa di gestire la lista degli articoli in vendita
 * @author Salvatore
 */
public class FactoryArticoli {
    
    private String connectionString;
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
    }
    
    
    /**
     * 
     * @param titolo Titolo del film
     * @param descrizione   breve descrizione dell'articolo
     * @param numero numero di copie messe in vendita
     * @param prezzo prezzo dell'articolo
     * @param path path dell'immagine della locandina
     * @param venditore Username dell'utente che ha messo in vendita questo articolo
     * @return l'articolo modificato se l'operazione va a buon fine, null altrimenti
     */
    public Articolo updateArticle(String titolo,String descrizione,int numero,double prezzo,String path,String venditore,int id){
        Connection conn=null;
        try {
            Articolo articolo=new Articolo(titolo,descrizione,numero,prezzo,path,venditore,id);
            conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            String sql= sql="update articoli set titolo=? , prezzo=? , numero=? , image_path=? , descrizione=? where id=?";
            
            PreparedStatement statement=conn.prepareStatement(sql);
            statement=conn.prepareStatement(sql);
            statement.setString(1,titolo);
            statement.setDouble(2,prezzo);
            statement.setInt(3,numero);
            statement.setString(4,path);
            statement.setString(5,descrizione);
            statement.setInt(6,id);
            
            int result= statement.executeUpdate();
            if(result!=1)                
                return null;
            
            statement.close();
            conn.close();
            return articolo;    
        }catch (SQLException ex) {  
            ex.printStackTrace();
            return null;
        }catch(IllegalArgumentException e){
            return null;
        }      
    }
    
    /**Rimuove dalla lista l'articolo con id ricevuto come parametro
     * 
     * @param id id dell'articolo da rimuovere
     * @return true se l'articolo viene rimosso false altrimenti
     */
    public boolean removeArticle(int id){
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="delete from articoli where id=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1,id);
            
            conn.setAutoCommit(false);
            int result= statement.executeUpdate();
            if(result!=1){
                conn.rollback();
                return false;
            }else
                conn.commit();
            statement.close();
            conn.close();
            return true;
        }catch (SQLException ex) {  
            ex.printStackTrace();
            try {
                conn.rollback();
            }catch (SQLException ex1) {
                Logger.getLogger(FactoryArticoli.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
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
     * @return il nuovo articolo inserito se l'inserimento va a buon fine, null altrimenti
     * @throws amm.m3.classes.ArticoloEsistenteException
     */   
    public Articolo addArticle(String titolo,String descrizione,int numero,double prezzo,String path,String venditore)throws ArticoloEsistenteException{ 
        Connection conn=null;
       
        try {
            Articolo articolo=new Articolo(titolo,descrizione,numero,prezzo,path,venditore,0);
            conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            String sql="select * from articoli where titolo=? and venditore=?";
               
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1, titolo);
            statement.setString(2, venditore);
            ResultSet set=statement.executeQuery();
             
            if(set.next()){ 
                throw new ArticoloEsistenteException();
            }
          
            sql="insert into articoli(id,titolo,prezzo,numero,image_path,descrizione,venditore) values(default,?,?,?,?,?,?)";
            statement=conn.prepareStatement(sql);
            statement.setString(1,titolo);
            statement.setDouble(2,prezzo);
            statement.setInt(3,numero);
            statement.setString(4,path);
            statement.setString(5,descrizione);
            statement.setString(6,venditore);
            
            int result= statement.executeUpdate();
            if(result!=1)                
                return null;
            
            statement.close();
            conn.close();
            return articolo;    
        }catch (SQLException ex) {  
            ex.printStackTrace();
            
            return null;
        }catch(IllegalArgumentException e){
            return null;
        } 
    }
    
    public ArrayList<Articolo> getArticleByPrezzo(double minPrice,double maxPrice){
       ArrayList<Articolo> lista=new ArrayList<Articolo>();
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="select * from articoli where prezzo between ? and ?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setDouble(1,minPrice);
            statement.setDouble(2,maxPrice);
            ResultSet result= statement.executeQuery();
            lista=getListFromResultSet(result);
            statement.close();
            conn.close();
            return lista;
        } catch (SQLException ex) {
            return null;
        } 
    }
    
     public Articolo getArticleById(int id){
        Articolo articolo=null;
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="select * from articoli where id=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1,id);
            
            ResultSet result= statement.executeQuery();
            if(result.next()){
                articolo=new Articolo(result.getString("titolo"),result.getString("descrizione"),
                        result.getInt("numero"),result.getDouble("prezzo"),result.getString("image_path"),result.getString("venditore"),result.getInt("id")); 
                if(result.next()){
                    return null;
                }
            }
            
            statement.close();
            conn.close();
            return articolo;
        } catch (SQLException ex) {
            return null;
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public ArrayList<Articolo> getArticleByTitolo(String titolo){
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="select * from articoli where titolo=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1,titolo);
            ResultSet result= statement.executeQuery();
            ArrayList<Articolo> out=getListFromResultSet(result);
            statement.close();
            conn.close();
            return out;
        } catch (SQLException ex) {
            return null;
        } 
    }
    
    public ArrayList<Articolo> getArticlesByVenditore(String userName){
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="select * from articoli where venditore=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1,userName);
            ResultSet result= statement.executeQuery();
            ArrayList<Articolo> out=getListFromResultSet(result);
            statement.close();
            conn.close();
            return out ;
        } catch (SQLException ex) {
            return null;
        }    
    }
    
    public ArrayList<Articolo> getArticlesList(){
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql="select * from articoli";
            Statement statement=conn.createStatement();
            ResultSet result= statement.executeQuery(sql);          
            ArrayList<Articolo> out=getListFromResultSet(result);
            statement.close();
            conn.close();
            return out;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } 
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
            return this.connectionString;
    } 
    
    /*restituisce una lista conente i dati restituiti da una query nella tabella articoli*/
    private ArrayList<Articolo> getListFromResultSet(ResultSet result){
        if(result==null)
            return null;
        try {
            ArrayList<Articolo> lista=new ArrayList<Articolo>();
            while(result.next()){
                lista.add(new Articolo(result.getString("titolo"),result.getString("descrizione"),
                        result.getInt("numero"),result.getDouble("prezzo"),result.getString("image_path"),result.getString("venditore"),result.getInt("id")));   
            }

            return lista;
        } catch (SQLException ex) {
            return null;
        }catch(IllegalArgumentException e){
            return null;
        }
    
    }
  
}
