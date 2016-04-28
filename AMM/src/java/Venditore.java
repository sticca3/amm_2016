/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amm.m3.classes.FactoryArticoli;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import amm.m3.classes.FactoryUtenti;
import amm.m3.classes.Utente;
import amm.m3.classes.UtenteVenditore;
/**
 *
 * @author Salvatore
 */
@WebServlet(urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session =request.getSession(false);
        UtenteVenditore venditore=checkUser(session);
       
        /*
            controlla se l'utente ha accesso a questa pagina e in caso contario visualizza un errore.
        */
        if(venditore!=null){
            /*Controlla se sono stati inseriti i dati necessari all'inserimento di un articolo,in caso contrario visualizza il form di inserimento*/
            if(request.getParameter("submit")!=null){    
                String titolo=request.getParameter("nomeArticolo");
                String path=request.getParameter("url");
                String descrizione=request.getParameter("descrizione");
                int id;
                try{
                    double prezzo=Double.parseDouble(request.getParameter("prezzo"));
                    int numero=Integer.parseInt(request.getParameter("quantita"));
                    id=venditore.addArticolo(titolo, descrizione, numero, prezzo, path);
                }catch(NumberFormatException e){
                    id=-1;
                }
                
                /*
                    se i dati inseriti sono corretti visualizza idettagli dell'articolo inserito.
                    altrimeti visualizza un messaggio di errore
                */
                if(id>=0){
                    request.setAttribute("articolo",FactoryArticoli.getInstance().getArticleById(id));
                    request.setAttribute("page","aggiunta");
                }else
                {
                    if(id==-1){
                        request.setAttribute("message", "Errore nell'inserimento dell'oggetto");
                    }else{
                        request.setAttribute("message", "L'articolo è gia presente nella lista");
                    }
                    request.setAttribute("page", "errore");
                } 
            }
        }else{
            request.setAttribute("page", "accesso_negato");
        }
        
        request.setAttribute("titolo","Area venditore");
        request.getRequestDispatcher("venditore.jsp").forward(request, response);      
    }
    
    /**
     * Controlla la variabile di sessione per cercare i dati dell'utente.
     * 
     * @param session variabile di sessione
     * @return UtenteVenditore venditore se l'utente ha eseguito il login ed è un venditore, 
     * null se la sessione non è stata creata o l'utente non è un venditore
     */
    
    private UtenteVenditore checkUser(HttpSession session){
        
        if(session!=null)
        {
            String userName=(String)session.getAttribute("utente");
            if(userName!=null){
                try{
                    Utente utente=FactoryUtenti.getInstance().getUtenteByUserName(userName);
                    if(utente!=null && utente instanceof UtenteVenditore)
                       return (UtenteVenditore)utente;   
                }catch(Exception e){
                     return null;
                } 
            }
        }
        return null;
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
