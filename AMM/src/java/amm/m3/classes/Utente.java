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
    public double saldo;
    
    public Utente(String userName ,String password,double saldo)throws IllegalArgumentException{
        if(userName==null || password==null ||saldo<0){
            throw new IllegalArgumentException("Errore nei dati inseriti");
        }
        
        this.userName=userName;
        this.password=password;
        this.saldo=saldo;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
     /**
     * Incrementa il credito dell'importo specificato.Se il credito finale risultsse negativo,
     * l'operazione non viene eseguita
     * @param importo importo da sommare al credito
     * @return true se la transazione è stata effettuta, false altrimenti
     */
    public boolean addMoney(double importo){
        if(saldo+importo>=0){
            saldo+=importo;
            if(FactoryUtenti.getInstance().updateUtente(this))
                return true;
            else
                saldo-=importo;
        } 
        return false;
    }
    
     public double getSaldo(){
        return ((double)Math.round(saldo*100))/100.0;//restituisce il saldo approssimato a 2 decimali
    }

    public void setPassword(String password) throws IllegalArgumentException{
        String old=this.password;
        if(password!=null)
            this.password = password;
            if(!FactoryUtenti.getInstance().updateUtente(this))
                this.password=old;   
        else
            throw new IllegalArgumentException("Errore");
    }
}


