package servlets;

import EJBs.DatabaseAccessEJB;
import EJBs.DatabaseUpdateEJB;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Game;
import models.GameList;

/**
 * Uses both EJBs and acquires the Game IDs of the games to be 
 * removed from the respective JSPs. Check in place to ensure that 
 * user has chosen at least one game to removed. List of games 
 * removed are sent to removeGameResponse.jsp
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "RemoveGameServlet", urlPatterns = {"/removeGameServlet"})
public class RemoveGameServlet extends HttpServlet {

    private ArrayList<Game> gameArray;

    @EJB
    DatabaseAccessEJB databaseAccessEJB;

    @EJB
    DatabaseUpdateEJB databaseUpdateEJB;

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
        String[] checkboxValues = null;
        checkboxValues = request.getParameterValues("gameID");
        String pageName = request.getParameter("pageName");
        
        if (checkboxValues == null) {
            boolean isAnyGameSelected = false;
            request.setAttribute("removeCheck", isAnyGameSelected);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageName);
            dispatcher.forward(request, response);

        } else {
            int IDCount = checkboxValues.length;

            gameArray = new ArrayList<>();
            GameList gameList = new GameList();

            // Put IDs into an int array for processing
            if (IDCount != 0) {
                int[] gameIDs = new int[IDCount];

                for (int i = 0; i < IDCount; i++) {
                    gameIDs[i] = Integer.parseInt(checkboxValues[i]);
                }

                gameArray = databaseAccessEJB.selectGamesByID(gameIDs);
                gameList.setGameList(gameArray);
                databaseUpdateEJB.removeGames(gameIDs);

                request.setAttribute("removeList", gameList);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/removeGameResponse.jsp");
                dispatcher.forward(request, response);

            }

        }

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
