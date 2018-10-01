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
import models.Game;

/**
 * Takes parameters from searchBacklog.jsp to display the game being 
 * updated in updateGame.jsp
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "UpdateGameServlet",  urlPatterns = {"/updateGameServlet"})
public class UpdateGameServlet extends HttpServlet {

    private int[] gameID = new int[1];

    @EJB
    DatabaseAccessEJB databaseAccessEJB;

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

        gameID[0] = Integer.parseInt(request.getParameter("editGameID"));

        Game game = new Game();

        game = databaseAccessEJB.selectGamesByID(gameID).get(0);
        game.setPlatformName(getPlatformName(game.getPlatformID()));
              
        request.setAttribute("game", game);

        // Pass control to responding page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateGame.jsp");
        dispatcher.forward(request, response);
    }
    
    private String getPlatformName(int platformID) {
        String platformName ="";
        
        switch (platformID) {
            case 1:
                platformName = "PC";
                break;
            case 2:
                platformName = "Playstation 4";
                break;
            case 3:
                platformName = "Playstation 3";
                break;
            case 4:
                platformName = "Xbox One";
                break;
            case 5:
                platformName = "Xbox 360";
                break;
            case 6:
                platformName = "Nintendo DS";
                break;
            case 7:
                platformName = "Nintendo Switch";
                break;
        }     
        return platformName;
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
