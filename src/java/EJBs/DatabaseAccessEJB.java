package EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateful;
import models.Game;

/**
 * Stateful EJB that allows the application to connect to the database and
 * access the records. Includes methods to get all records, check 
 * if a game exists, as well as selecting records given an ID
 * 
 * @author Mikhaela Tajonera
 */
@Stateful
public class DatabaseAccessEJB {

    private static String driverURL = "org.apache.derby.jdbc.ClientDriver";
    private static String dbURL = "jdbc:derby://localhost:1527/Games_DB;user=xht5252;password=password";
    private static Connection connection;

    public ArrayList<Game> getGames() {

        ArrayList<Game> games = new ArrayList<>();

        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT g.GAME_ID, g.NAME, g.STATUS, g.PLATFORM_ID, p.NAME, g.DATE_ADDED FROM GAME_LIST g, PLATFORM p"
                    + " WHERE g.PLATFORM_ID = p.ID ORDER BY g.GAME_ID ASC");

            Game game;

            while (resultSet.next()) {
                game = new Game();
                game.setID(resultSet.getInt(1));
                game.setName(resultSet.getString(2));
                game.setStatus(resultSet.getString(3));
                game.setPlatformID(resultSet.getInt(4));
                game.setPlatformName(resultSet.getString(5));
                game.setDateAdded(resultSet.getDate(6).toString());
                games.add(game);
            }

            statement.close();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException e) {
            System.err.println("Unable to connect to the database: " + e);
        }
        return games;
    }

    public ArrayList<Game> selectGamesByID(int[] IDs) {
        ArrayList<Game> games = new ArrayList<>();
        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            Statement statement = connection.createStatement();
            StringBuilder IDList = new StringBuilder("(");

            for (int i = 0; i < IDs.length; i++) {

                IDList.append(IDs[i]);

                if (i != IDs.length - 1) {
                    IDList.append(", ");
                }
            }

            IDList.append(")");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM GAME_LIST, PLATFORM WHERE "
                    + "PLATFORM.ID = GAME_LIST.PLATFORM_ID AND GAME_ID IN " + IDList.toString());

            Game game;

            while (resultSet.next()) {
                game = new Game();
                game.setID(resultSet.getInt(1));
                game.setName(resultSet.getString(2));
                game.setStatus(resultSet.getString(3));
                game.setPlatformID(resultSet.getInt(4));
                game.setDateAdded(resultSet.getDate(5).toString());
                game.setPlatformName(resultSet.getString(7));
                games.add(game);
            }

            statement.close();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException e) {
            System.err.println("Unable to connect to the database: " + e);
        }
        return games;
    }
    
    
    public boolean doesGameExist(String name) {
        boolean isFound = true;
        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            String lowerCaseName = name.toLowerCase();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NAME FROM GAME_LIST WHERE LOWER(NAME)='" + lowerCaseName + "'");

            // Empty 
            if (!resultSet.next()) {
                isFound = false;
            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException e) {
            System.err.println("Unable to connect to the database: " + e);
        }
        return isFound;
    }
    
    public static void main(String[] args) {
        ArrayList<Game> games = new ArrayList<>();
        
        games = queryBacklog("", "Any", 3);
        
        //System.out.println(games.get(0).getName());
    }

    public static ArrayList<Game> queryBacklog(String name, String status, int platformID) {
        ArrayList<Game> queriedGames = new ArrayList<>();
        
        
        String lowerName = name.toLowerCase();
        String nameQuery = ("");
        String statusQuery = ("");
        String platformQuery = ("");
        
        if (name.length() != 0 ) {
            nameQuery = " AND LOWER(g.NAME) LIKE '%" + lowerName + "%'";
        }
        
        if (!status.equalsIgnoreCase("ANY")) {
            statusQuery = " AND g.STATUS = '" + status + "'";
        }
        
        if (platformID != 0) {
            platformQuery = " AND g.PLATFORM_ID = " + platformID;
        }

        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            Statement statement = connection.createStatement();
            String query = "SELECT g.GAME_ID, g.NAME, g.STATUS, p.NAME, g.DATE_ADDED FROM GAME_LIST g, PLATFORM p"
                    + " WHERE g.PLATFORM_ID = p.ID" + nameQuery + statusQuery + platformQuery + " ORDER BY g.GAME_ID ASC";
            
//            query = "SELECT g.GAME_ID, g.NAME, g.STATUS, p.NAME, g.DATE_ADDED FROM GAME_LIST g, PLATFORM p "
//                    + "WHERE g.PLATFORM_ID = p.ID AND g.NAME LIKE '%Dark%'";

            ResultSet resultSet = statement.executeQuery(query);

            Game game;

            while (resultSet.next()) {
                System.out.println("smthing");
                game = new Game();
                game.setID(resultSet.getInt(1));
                game.setName(resultSet.getString(2));
                game.setStatus(resultSet.getString(3));
                game.setPlatformName(resultSet.getString(4));
                game.setDateAdded(resultSet.getDate(5).toString());
                queriedGames.add(game);
            }

            statement.close();
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException e) {
            System.err.println("Unable to connect to the database: " + e);
        }
        return queriedGames;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
