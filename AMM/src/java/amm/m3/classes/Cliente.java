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
public class Cliente extends Utente{
    
    public Cliente(String userName, String password, Conto saldo) throws Exception {
        super(userName, password, saldo);
    }
    
}
