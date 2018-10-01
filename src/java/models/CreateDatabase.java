package models;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contains functions and a main that creates the
 * database tables as well as a function to populate
 * the tables
 * 
 * @author Mikhaela Tajonera
 */
public class CreateDatabase {

    private static String driverURL = "org.apache.derby.jdbc.ClientDriver";
    private static String dbURL = "jdbc:derby://localhost:1527/Games_DB;user=xht5252;password=password;create=true;";
    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName(driverURL).newInstance();
        connection = DriverManager.getConnection(dbURL);

        //dropTables();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(null, null, "GAME_LIST", null);
        
        if (!rs.next()){
            createTables();
            populateTables();    
        }
        
        connection.close();
    }

    public static void createTables() throws SQLException {

        Statement statement = connection.createStatement();
        String createGameList = "CREATE TABLE GAME_LIST (GAME_ID INT NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "NAME VARCHAR(30), "
                + "STATUS VARCHAR(20), "
                + "PLATFORM_ID INT, "
                + "DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        statement.executeUpdate(createGameList);

        String createPlatformTable = "CREATE TABLE PLATFORM (ID INT PRIMARY KEY, "
                + "NAME VARCHAR(30))";
        statement.executeUpdate(createPlatformTable);

        statement.close();
    }

    public static void populateTables() throws SQLException {

        Statement statement = connection.createStatement();

        String populateGameList = "INSERT INTO GAME_LIST VALUES"
                + "(default, 'This War of Mine', 'Completed', 1, default),"
                + "(default, 'Payday 2', 'Started', 2, default),"
                + "(default, 'Dark Souls', 'Not Started', 5, default),"
                + "(default, 'Tales from the Borderlands', 'Completed', 1, default),"
                + "(default, 'Tomb Raider', 'Completed', 3, default),"
                + "(default, 'Rise of the Tomb Raider', 'Not Started', 4, default),"
                + "(default, 'Legend of Zelda', 'Not Started', 7, default),"
                + "(default, 'Stardew Valley', 'Started', 1, default),"
                + "(default, 'Call of Duty', 'Not Started', 5, default),"
                + "(default, 'Pokemon: Soul Silver', 'Completed', 6, default),"
                + "(default, 'Monster Hunter', 'Not Started', 3, default)";

        statement.executeUpdate(populateGameList);

        String populatePlatforms = "INSERT INTO PLATFORM VALUES"
                + "(1, 'PC'),"
                + "(2, 'Playstation 4'),"
                + "(3, 'Playstation 3'),"
                + "(4, 'Xbox One'),"
                + "(5, 'Xbox 360'),"
                + "(6, 'Nintendo DS'),"
                + "(7, 'Nintendo Switch')";

        statement.executeUpdate(populatePlatforms);
        statement.close();
    }

    public static void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        
        statement.executeUpdate("DROP TABLE PLATFORM");
        statement.executeUpdate("DROP TABLE GAME_LIST");
        System.out.println("Dropped");
        
        statement.close();
    }
}
