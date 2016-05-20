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
 *
 * @author Salvatore
 */
public class FactoryUtenti {
    
    
    private String connectionString;
    private static FactoryUtenti factory;
    public static final int TYPE_CLIENTE=0;
    public static final int TYPE_VENDITORE=1;
    
    public static FactoryUtenti getInstance(){
        if(factory==null){
            factory=new FactoryUtenti();
        }
        return factory;
    }
    
    private FactoryUtenti(){
    }
    
    /**
     * permette di modificare le informazioni relative a un utente.
     * 
     * @param utente l'utente da modificare
     * @return  true se l'operaione va a buon fine false altrimenti
     */
    public boolean updateUtente(Utente utente){
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            conn.setAutoCommit(false);
            String sql="update Utenti set saldo=?, password=?, tipo=?,feedback=?  where username=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setDouble(1,utente.getSaldo());
            statement.setString(2,utente.getPassword());
                                    
            if(utente instanceof UtenteCliente){
                statement.setString(3,"C");
                statement.setNull(4,java.sql.Types.INTEGER);
            
            }else{
                statement.setString(3,"V");
                statement.setDouble(4,((UtenteVenditore)utente).getFeedback());
            }
            
            statement.setString(5,utente.getUserName());
            int updateUtente=statement.executeUpdate();
            if(updateUtente!=1)
                return false;
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    /**
     * Crea un nuovo utente con le informazioni ricevute come parametro.
     * Se è gia presente un utente con username uguale, il nuovo utente non viene creato
     * 
     * @param userName UserName del nuovo utente
     * @param password  Paassword del nuovo utente 
     * @param type  Tipo di utente(Cliente o Venditore)
     * @return true se il nuovo utente viene creato, false altrimenti
     */
    public boolean  addUtente(String Username,String password,int type){
        try{
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            String sql;
            sql="insert into Utenti(username,password,saldo,tipo,feedback) values(?,?,?,?,?);";
            PreparedStatement statement=conn.prepareStatement(sql);
            
            statement.setString(1, Username);
            statement.setString(2, password);
            statement.setDouble(3, 0);
            
            statement.setInt(1,0);
            if(type==TYPE_CLIENTE){
                statement.setString(4, "C");
                statement.setNull(5, java.sql.Types.INTEGER);
            }
            else{
                statement.setString(4, "V");
                statement.setInt(5,0);
            }
              
            int affected_row=statement.executeUpdate(sql);
            
            if(affected_row!=1)
                return false;  
            
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
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            String sql="select * from Utenti where username=? and password=?";
            
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1, Username);
            statement.setString(2, password);

            ResultSet result=statement.executeQuery();
           
            if(result.next()){
                if(result.getString("tipo").equals("C")){
                    u=new UtenteCliente(result.getString("username"),result.getString("password"),result.getDouble("saldo"));
                }else{
                    u=new UtenteVenditore(result.getString("username"),result.getString("password"),result.getDouble("saldo"),result.getInt("feedback"));
                }     
                if(result.next()){
                    return null;
                }
            }
            statement.close();
            conn.close();
            return u;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }       
    }
    /**
     * permette l'acquisto di un articolo a un cliente. Se si verifica un errore durante l'acquisto 
     * il database viene riportato alla situazione iniziale e la transazione fallisce
     * 
     * @param id id dell'articolo in vendita
     * @param Username username dell'utente che richiede la transazione
     * @return true se la transazione va a buon fine, false altrimenti
     */   
    public boolean transazione(int id,String Username){
        //Controllo che i parametri siano corretti e che il cliente abbi un credito sufficente, in caso contrario annullo l'operazione
        Utente cliente=getUtenteByUserName(Username);
        
        Articolo articolo=FactoryArticoli.getInstance().getArticleById(id);
        if(articolo==null||cliente==null||!(cliente instanceof UtenteCliente))
            return false;
        
        Utente venditore=getUtenteByUserName(articolo.getVenditore());
        if(venditore==null||!(venditore instanceof UtenteVenditore)){
            return false;
        }
        
        double prezzo=articolo.getPrezzo();
        if(cliente.getSaldo()<prezzo){
            return false;
        }
        
        Connection conn=null;
        try {  
            conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            conn.setAutoCommit(false);
            String sql="";
            PreparedStatement statement;
            //Controllo la quantita di articoli in vendita.
            //se e maggiore di uno la riduce altrimenti elimina l'articolo dalla lista
            if(articolo.getNumero()==1){
                sql="delete from Articoli where id=?";
                statement=conn.prepareStatement(sql);
                statement.setInt(1,id);
            }else{
                sql="update Articoli set numero=? where id=?";
                statement=conn.prepareStatement(sql);
                statement.setInt(1,articolo.getNumero()-1);
                statement.setInt(2,id);
            }
            int updateArticolo=statement.executeUpdate();
            
            //riduce il credito del cliente 
            sql="update Utenti set saldo=? where username=?";
            statement=conn.prepareStatement(sql);
            statement.setDouble(1,cliente.getSaldo()-prezzo);
            statement.setString(2,cliente.getUserName());
            int updateCliente=statement.executeUpdate();
            
            //incrementa il credito del venditore
            sql="update Utenti set saldo=? where username=?";
            statement=conn.prepareStatement(sql);
            statement.setDouble(1,venditore.getSaldo()+prezzo);
            statement.setString(2,venditore.getUserName());
            int updateVenditore=statement.executeUpdate();
              
            //se una delle query precedenti non restituisce i risultati aspettati, si riporta il database alla condizione iniziale
            if(updateCliente!=1||updateArticolo!=1||updateVenditore!=1){
                conn.rollback();
                return false;
            }
            
            conn.commit();
            statement.close();
            conn.close();         
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }        
    }
    
    public ArrayList<Utente> getListaUtenti(){
        String sql="select * from Utenti";
        ArrayList<Utente> listaUtenti=executeQuery(sql);
        return listaUtenti;
    }
    
    public ArrayList<UtenteVenditore> getVenditori(){
       String sql="select * from Utenti where tipo='V'";
       
        ArrayList<Utente> utenti=executeQuery(sql);
        ArrayList<UtenteVenditore> venditori=new ArrayList<UtenteVenditore>();
        if(utenti!=null){
            for(Utente u:utenti){
                venditori.add((UtenteVenditore)u);  
            } 
        }
        return venditori;      
    }
    
    public ArrayList<UtenteCliente> getClienti(){
        String sql="select * from Utenti where tipo='C'";
       
        ArrayList<Utente> utenti=executeQuery(sql);
        ArrayList<UtenteCliente> clienti=new ArrayList<UtenteCliente>();
        if(utenti!=null){
            for(Utente u:utenti){
                clienti.add((UtenteCliente)u);  
            } 
        }
        return clienti;      
    }  

    public Utente getUtenteByUserName(String Username){
        Utente u=null;
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            String sql="select * from Utenti where username=?";
            
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1, Username);
  
            ResultSet result=statement.executeQuery();
             if(result.next()){
                if(result.getString("tipo").equals("C")){
                    u=new UtenteCliente(result.getString("username"),result.getString("password"),result.getDouble("saldo"));
                }else{
                    u=new UtenteVenditore(result.getString("username"),result.getString("password"),result.getDouble("saldo"),result.getInt("feedback"));
                }
                 
                if(result.next()){
                    return null;
                }
            }
            statement.close();
            conn.close();
            return u;  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
            return this.connectionString;
    } 
    
    private ArrayList<Utente> executeQuery(String query){
        if(query==null)
            return null;
        try {
            Connection conn=DriverManager.getConnection(connectionString,"sticca","amm2016");
            
            Statement statement=conn.createStatement();
            ResultSet result= statement.executeQuery(query);

            ArrayList<Utente> listaUtenti=new ArrayList<Utente>();
            while(result.next()){
                if(result.getString("tipo").equals("C")){
                    listaUtenti.add(new UtenteCliente(result.getString("username"),result.getString("password"),result.getDouble("saldo")));
                }else{
                    listaUtenti.add(new UtenteVenditore(result.getString("username"),result.getString("password"),result.getDouble("saldo"),result.getInt("feedback")));
                }
            }
            statement.close();
            conn.close();
            return listaUtenti;

        }catch (IllegalArgumentException ex) {
            return null;      
        } catch (SQLException ex) {
           return null;
        }
    }

  
}
