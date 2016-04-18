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
public class Utente {
    
    private String userName,password;
    public Conto saldo;
    
    public Utente(String userName ,String password,Conto saldo)throws Exception{
        if(userName==null || password==null ||saldo==null){
            throw new Exception("Errore nei dati inseriti");
        }
        
        this.userName=userName;
        this.password=password;
        this.saldo=saldo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)throws Exception {
        if(userName!=null)
            this.userName = userName;
        else
            throw new Exception("Errore");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception{
        if(password!=null)
            this.password = password;
        else
            throw new Exception("Errore");
    }
}


