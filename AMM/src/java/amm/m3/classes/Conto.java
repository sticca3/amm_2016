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
        return saldo;
    }
    
    
    public boolean addMoney(double importo){
        if(this.saldo+importo<0){
            return false;
        }
        
        return true;
    }
    
    
}
