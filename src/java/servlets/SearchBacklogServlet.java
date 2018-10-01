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
import models.GameList;

/**
 * Uses the input parameters from searchBacklog.jsp to acquire 
 * query information which are then passed into the DatabaseAccessEJB 
 * function queryBacklog()
 * 
 * @author Mikhaela Tajonera
 */
@WebServlet(name = "SearhBacklogServlet",  urlPatterns = {"/searchBacklogServlet"})
public class SearchBacklogServlet extends HttpServlet {

    @EJB
    DatabaseAccessEJB databaseAccessEJB;

    @Override
    public void init() throws ServletException {
    }

    public SearchBacklogServlet() {
        super();
    }
    
    private String name;
    private String status;
    private int platformID;
    private String platformName;

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
            case "Any":
                platformID = 0;
                break;
        }     
        
        GameList gameList = new GameList();
        gameList.setGameList(databaseAccessEJB.queryBacklog(name, status, platformID));

        request.setAttribute("games", gameList);

        // Pass control to responding page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchBacklog.jsp");
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
