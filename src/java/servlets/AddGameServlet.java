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
import models.NewGame;

/**
 * Uses both EJBs to add a game into the database using the 
 * parameters acquired from the addGame.html. 
 * Has checks to ensure game name is unique and long enough.
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "AddGameServlet", urlPatterns = {"/AddGameServlet"})
public class AddGameServlet extends HttpServlet {

    private String newGameID = "newGame";
    private String name;
    private String status;
    private String platformName;

    @EJB
    private DatabaseUpdateEJB databaseUpdateEJB;
    
    @EJB
    private DatabaseAccessEJB databaseAccessEJB;

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

        name = request.getParameter("name");
        status = request.getParameter("status");
        platformName = request.getParameter("platformName");
       
        Game game = new Game();

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


        game.setName(name);
        game.setStatus(status);
        game.setPlatformID(platformID);

        NewGame newGame = new NewGame(game);

        // Assume game names have at least 3 letters
        if (name.trim().length() < 3 || name.length() > 30) {
            newGame.setIsAdded(false);
            // Set to 1 if name is too short
            newGame.setErrorNumber(1);
        }
        
         // If game with same name already exists
        if (databaseAccessEJB.doesGameExist(game.getName())) {
            newGame.setIsAdded(false);
            // Set to 2 if game already exists
            newGame.setErrorNumber(2);
        }

        if (newGame.getErrorNumber() == 0) {
            newGame.setIsAdded(true);
            databaseUpdateEJB.addGame(game);
        }

        request.setAttribute(newGameID, newGame);
        request.setAttribute("platformName", platformName);

        // Pass control to responding page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addGameResponse.jsp");
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
