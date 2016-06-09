/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amm.m3.classes.Articolo;
import amm.m3.classes.ArticoloEsistenteException;
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
                
                Articolo articolo=null;
                int flag=0;
                try{
                    double prezzo=Double.parseDouble(request.getParameter("prezzo"));
                    int numero=Integer.parseInt(request.getParameter("quantita"));
                    //controlla se l'operazione da effettuare e una insert o una update
                    if(request.getParameter("update")!=null){
                        int id=Integer.parseInt(request.getParameter("update"));
                        articolo=venditore.updateArticolo(titolo,descrizione,numero,prezzo,path,id);
                    }else{
                        articolo=venditore.addArticolo(titolo, descrizione, numero, prezzo, path);
                    } 
                }catch(NumberFormatException e){
                    flag=0;
                }catch(ArticoloEsistenteException ae){
                    flag=-1;
                }
                
                /*
                    se l'operazione e eseguita correttamente visualizza i dettagli dell'articolo inserito.
                    altrimeti visualizza un messaggio di errore
                */
                if(articolo!=null){
                    request.setAttribute("articolo",articolo);
                    request.setAttribute("page","aggiunta");
                }else
                {
                    if(flag==0){
                        request.setAttribute("message", "Errore nell'inserimento dell'oggetto");
                    }else{
                        request.setAttribute("message", "L'articolo è gia presente nella lista");
                    }
                    request.setAttribute("page", "errore");
                } 
            }else{
                //controlla il tipo di operaione richiesta e visualizza la pagina corrispondente
                try{   
                    switch(request.getParameter("operation")){
                    case "update": 
                        try{
                            int id=Integer.parseInt(request.getParameter("id"));
                            request.setAttribute("page","form");
                            request.setAttribute("articolo",FactoryArticoli.getInstance().getArticleById(id));
                        }catch(NumberFormatException e){
                            request.setAttribute("page", "errore");
                            request.setAttribute("message","Errore nel caricamento della pagina");
                        } 
                        break;
                    case "delete":
                         try{
                            int id=Integer.parseInt(request.getParameter("id"));
                            if(venditore.rimuoviArticolo(id)){
                                request.setAttribute("venditore","true");
                                request.setAttribute("articoli",FactoryArticoli.getInstance().getArticlesByVenditore(venditore.getUserName()));
                            }else{
                                request.setAttribute("message", "Errore l'oggetto non e stato eliminato");
                                request.setAttribute("page", "errore");
                            }
                         }catch(NumberFormatException e){
                            request.setAttribute("page", "errore");
                            request.setAttribute("message","Si e verificato un errore.");
                        } 
                        break;
                    case "insert":
                        request.setAttribute("page","form");
                        break;
                    default:
                        request.setAttribute("venditore","true");
                        request.setAttribute("articoli",FactoryArticoli.getInstance().getArticlesByVenditore(venditore.getUserName()));
                        break;
                    }
                }catch(NullPointerException e){
                    request.setAttribute("venditore","true");
                    request.setAttribute("articoli",FactoryArticoli.getInstance().getArticlesByVenditore(venditore.getUserName()));
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
                }catch(IllegalArgumentException e){
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
