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
public class Venditore extends Utente {
    
    private double feedback;
    
    public Venditore(String userName, String password, Conto saldo,double feedback) throws Exception {
        super(userName, password, saldo);
        
        if(feedback>=0)
            this.feedback=feedback;
        else
            this.feedback=0;
    }
    
    public void addArticle(Articolo articolo){
        
    }
}
