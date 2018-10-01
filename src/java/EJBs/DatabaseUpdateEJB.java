package EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;
import models.Game;

/**
 * Stateless EJB that allows the modification of the database. Includes
 * methods to add, remove, and update records.
 * 
 * @author Mikhaela Tajonera
 */
@Stateless
public class DatabaseUpdateEJB {

    private static String driverURL = "org.apache.derby.jdbc.ClientDriver";
    private static String dbURL = "jdbc:derby://localhost:1527/Games_DB;user=xht5252;password=password";
    static Connection connection;

    public DatabaseUpdateEJB() {
    }

    public void addGame(Game game) {

        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            String insertSQL = "INSERT INTO GAME_LIST (name, status, platform_ID) VALUES (?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertSQL);
            ps.setString(1, game.getName());
            ps.setString(2, game.getStatus());
            ps.setInt(3, game.getPlatformID());
            ps.executeUpdate();

            ps.close();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT g.GAME_ID, g.DATE_ADDED, p.NAME FROM GAME_LIST g, PLATFORM p"
                    + " WHERE g.PLATFORM_ID = p.ID");

            resultSet.next();
            if (game.getID() == 0) {
                game.setID(resultSet.getInt("GAME_ID"));
            }

            if (game.getDateAdded() == null) {
                game.setDateAdded(resultSet.getDate("DATE_ADDED").toString());
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGames(int[] gameIDs) {

        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            String deleteSQL;
            Statement statement = connection.createStatement();
            StringBuilder IDList = new StringBuilder("(");

            for (int i = 0; i < gameIDs.length; i++) {

                IDList.append(gameIDs[i]);

                if (i != gameIDs.length - 1) {
                    IDList.append(", ");
                }
            }

            IDList.append(")");

            deleteSQL = "DELETE FROM GAME_LIST WHERE GAME_ID IN " + IDList.toString();
            statement.executeUpdate(deleteSQL);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGame(int oldGameID, String newName, String newStatus, int newPlatformID) {

        try {
            // Connect to database
            Class.forName(driverURL).newInstance();
            connection = DriverManager.getConnection(dbURL);

            String updateSQL;
            updateSQL = "UPDATE GAME_LIST SET NAME ='" + newName + "', STATUS = '" + newStatus
                    + "' , PLATFORM_ID =" + newPlatformID + " WHERE GAME_ID=" + oldGameID;
            Statement statement = connection.createStatement();

            statement.executeUpdate(updateSQL);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e) {
            e.printStackTrace();
        }

    }
}
