/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amm.m3.classes.Articolo;
import amm.m3.classes.FactoryArticoli;
import amm.m3.classes.FactoryUtenti;
import amm.m3.classes.Utente;
import amm.m3.classes.UtenteCliente;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

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
        
        HttpSession session=request.getSession(false);
        UtenteCliente cliente=checkUser(session);
        /*
            controlla se l'utente ha accesso a questa pagina e in caso contario visualizza un errore.
        */
        if(cliente!=null){
            /*
                Controlla quali parametri sono stati specificati per selezionare la pagina da visualizzare 
                Se non si specifica alcun parmetro viene visualizzata la lista delgi articoli
            */
            if(request.getParameter("id")!=null){ 
                String buy=request.getParameter("acquista");
                try{
                    int id=Integer.parseInt(request.getParameter("id"));
                    Articolo art=FactoryArticoli.getInstance().getArticleById(id);
                    
                    if(buy!=null){
                        /*
                            se il parametro acquista viene imostato a "true", esegue l'acquisto
                            se viene impostato a "done",visualiza un mesaggio di conferma dell'avvenuto acquisto
                            altrimenti visualizza un messaggio di errore
                        */
                        if(buy.equals("true")){
                            if(cliente.CompraArticolo(id)){
                                response.sendRedirect("cliente.html?id="+id+"&acquista=done");
                                return;
                            }
                            else{
                                request.setAttribute("page", "errore"); 
                                request.setAttribute("message","Il credito e insufficente.Impossibile procedere all'acquisto");
                            }
                        }else{
                            if(buy.equals("done")){
                                request.setAttribute("page", "errore"); 
                                request.setAttribute("message","Articolo acquistato.Credito residuo: "+cliente.saldo.getSaldo()+"€");
                            }else{
                                request.setAttribute("page", "errore"); 
                                request.setAttribute("message","Errore.Impossibile procedere all'aquisto");
                            }
                       }
                    }else{
                        /*
                            Se il paretro acquista non viene specificato, controlla l'id specificato.
                            se l'id appartiene a un articolo esistente, visualizza i dettagli dell'articolo.
                            altrimenti visualizza un messaggio di errore.
                        */
                        if(art==null){
                            request.setAttribute("page", "errore"); 
                            request.setAttribute("message","Articolo non trovato");
                        }else{
                            request.setAttribute("page", "acquisto");
                            request.setAttribute("articolo", FactoryArticoli.getInstance().getArticleById(id));
                        }
                    }
                }catch(NumberFormatException e){
                    request.setAttribute("articoli", FactoryArticoli.getInstance().getArticlesList());  
                }
            }else
                request.setAttribute("articoli", FactoryArticoli.getInstance().getArticlesList());
        }else{
            request.setAttribute("page", "accesso_negato");
        }
        
        request.setAttribute("titolo","Area cliente");
        request.getRequestDispatcher("cliente.jsp").forward(request, response);
    }
    
    /**
     * Controlla la variabile di sessione per cercare i dati dell'utente.
     * 
     * @param HttpSession variabile di sessione
     * @return UtenteCliente cliente se l'utente ha eseguito il login ed è un cliente, 
     * null se la sessione non è stata creata o l'utente è un venditore
     */
    
    private UtenteCliente checkUser(HttpSession session){
        
        if(session!=null)
        {
            String userName=(String)session.getAttribute("utente");
            if(userName!=null){
                try{
                    Utente utente=FactoryUtenti.getInstance().getUtenteByUserName(userName);
                    if(utente!=null && utente instanceof UtenteCliente)
                       return (UtenteCliente)utente;   
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
