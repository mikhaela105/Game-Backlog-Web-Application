package servlets;

import EJBs.DatabaseAccessEJB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.GameList;

/**
 * Uses DatabaseAccessEJB to retrieve all the games from the database, 
 * the GameList bean is then used to store that data to be sent to the
 * gameBacklog.jsp
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "GameBacklogServlet", urlPatterns = {"/gameBacklogServlet"})
public class GameBacklogServlet extends HttpServlet {
    
    @EJB
    private DatabaseAccessEJB databaseAccessEJB;
    
    @Override
    public void init() throws ServletException{
    }
    
    public GameBacklogServlet(){
        super();
    }

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
        
        GameList gameList = new GameList();
        gameList.setGameList(databaseAccessEJB.getGames());
        
        HttpSession session = request.getSession();
        session.setAttribute("games", gameList);
        
        // Pass control to responding page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gameBacklog.jsp");
        dispatcher.forward(request, response);
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
