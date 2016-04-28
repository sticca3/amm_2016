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
public class Conto {
    
    private double saldo;
    
    public Conto(){
        saldo=0;
    }
    
    public Conto(double saldo){
        if(saldo>=0)
            this.saldo=saldo;
        else
             saldo=0;
    }
    
    public double getSaldo(){
        return ((double)Math.round(saldo*100))/100.0;//restituisce il saldo approssimato a 2 decimali
    }
    
    /**
     * Incrementa il credito dell'importo specificato.Se il credito finale risultsse negativo,
     * l'operazione non viene eseguita
     * @param importo importo da sommare al credito
     * @return true se la transazione è stata effettuta, false altrimenti
     */
    public boolean addMoney(double importo){
        if(controllaOperazione(importo)){
            saldo+=importo;
            return true;
        } 
        return false;
    }
    
    /**
     * Controlla se il credito puo essere incrementato dell'importo specificato
     * @param importo importo da sommare al credito
     * @return true se la transazione puo essere effetuata, false altrimenti
     */
    public boolean controllaOperazione(double importo){
        if(this.saldo+importo<0){
            return false;
        }
        return true;
    }
    
}
