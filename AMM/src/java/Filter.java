/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/filter.json"})
public class Filter extends HttpServlet {

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
        
        
        if(checkUser(request.getSession(false))){
            String pattern=request.getParameter("q");
            if(pattern==null){
                response.sendError(403);
            }else{
                request.setAttribute("articoli", FactoryArticoli.getInstance().getFiltredArticles(pattern));
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                request.getRequestDispatcher("filter.jsp").forward(request, response);
            }
        
        }else
            response.sendError(403);
        
        
    }

    private boolean checkUser(HttpSession session){
        
        if(session!=null)
        {
            String userName=(String)session.getAttribute("utente");
            if(userName!=null){
                try{
                    Utente utente=FactoryUtenti.getInstance().getUtenteByUserName(userName);
                    if(utente!=null && utente instanceof UtenteCliente)
                       return true;   
                }catch(IllegalArgumentException e){
                     return false;
                } 
            }
        }
        return false;
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
