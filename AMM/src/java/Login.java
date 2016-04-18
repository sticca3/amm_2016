/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amm.m3.classes.Articolo;
import amm.m3.classes.Cliente;
import amm.m3.classes.FactoryUtenti;
import amm.m3.classes.Utente;
import amm.m3.classes.Venditore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author Salvatore
 */
@WebServlet(urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            HttpSession session=request.getSession();
            
            if(session==null){
                String username=(String)request.getParameter("userName");
                String password=(String)request.getParameter("pwd");
                
                //Controlla se  parametri sono stati inviati
                if(request.getParameter("submit")!=null && username!=null && password!=null )
                { 
                    Utente utente=null;
                    try {
                        //cerca le credenziali inserite nella lista degli utenti
                        utente=FactoryUtenti.getInstance().autenticazione(username, password);                        
                    } catch (Exception ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(utente!=null){
                        //se l'utente esiste, inizializza la sessione e richiama la pagina corrispondente al tipo di utente
                        session=request.getSession(true);
                        session.setAttribute("utente", utente);
                        redirect(request,response,utente);
                    }else{
                        //se l'utente non esiste ritona al form di login
                        request.setAttribute("errore","Autenticazione fallita.");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }  
                }else{
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }else{
                Utente utente=(Utente)session.getAttribute("utente");
                redirect(request,response,utente);
            }  
    }

    private void redirect(HttpServletRequest request,HttpServletResponse response,Utente utente) throws ServletException, IOException{
        if(utente instanceof Cliente)
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
        else
            request.getRequestDispatcher("venditore.jsp").forward(request, response);  
    
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
