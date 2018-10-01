package servlets;

import EJBs.DatabaseAccessEJB;
import EJBs.DatabaseUpdateEJB;
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
 * Uses parameters pass from updateGame.jsp to update selected game. 
 * There are checks to ensure the game is actually being modified and 
 * whether the new game name already exists in the database before 
 * modifying the game. Generates and dispatches response accordingly
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "ApplyGameUpdateServlet", urlPatterns = {"/applyGameUpdateServlet"})
public class ApplyGameUpdateServlet extends HttpServlet {

    private String name;
    private String status;
    private int platformID;
    private int oldGameID;
    private String platformName;
    private StringBuilder stringResponse;

    @EJB
    DatabaseUpdateEJB databaseUpdateEJB;

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

        boolean wasModified = true;
        name = request.getParameter("name");
        status = request.getParameter("status");
        platformName = request.getParameter("platformName");
        platformID = getPlatformID(platformName);
        oldGameID = Integer.parseInt(request.getParameter("oldGameID"));
        
        stringResponse = new StringBuilder();

        Game game = new Game();

        // get game being modified
        int[] oldGameIDarr = new int[1];
        oldGameIDarr[0] = oldGameID;
        game = databaseAccessEJB.selectGamesByID(oldGameIDarr).get(0);

        // If new game name is too short or too long        
        if (name.trim().length() < 3 || name.length() > 30) {
            stringResponse.append("The name must be between 3-30 characters long.\n");
            wasModified = false;
        }

        // If no changes were made to the game
        if (game.getName().equalsIgnoreCase(name) && game.getStatus().equalsIgnoreCase(status)
                && game.getPlatformID() == platformID) {
            stringResponse.append("Game was not updated as no values were modified.\n");
            wasModified = false;
        }
        
        // If new name entered and new name already exists in database
        if (!game.getName().equalsIgnoreCase(name) && databaseAccessEJB.doesGameExist(name)) {
            wasModified = false;
            stringResponse.append("The game with the name ").append(name).append(" already exists in the database.");
        }
        
        if (wasModified) {
            stringResponse.append(game.getName()).append(" was successfully modified.");
            request.setAttribute("updateResponse", stringResponse.toString());
            databaseUpdateEJB.updateGame(oldGameID, name, status, platformID);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateGameResponse.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("updateResponse", stringResponse.toString());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateGameResponse.jsp");
            dispatcher.forward(request, response);
        }

    }

    private int getPlatformID(String platformName) {
        int platformID = 0;
        switch (platformName) {
            case "PC":
                platformID = 1;
                break;
            case "Playstation 4":
                platformID = 2;
                break;
            case "Playstation 3":
                platformID = 3;
                break;
            case "Xbox One":
                platformID = 4;
                break;
            case "Xbox 360":
                platformID = 5;
                break;
            case "Nintendo DS":
                platformID = 6;
                break;
            case "Nintendo Switch":
                platformID = 7;
                break;
        }
        return platformID;
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
