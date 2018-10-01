package models;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 * Stateless Bean that represents a Game object. 
 * Contains variables and appropriate getters and setters
 * 
 * @author Mikhaela Tajonera
 */
@Stateless
public class Game implements Serializable {
    
    private int ID;
    private String name;
    private String status;
    private int platformID;
    private String platformName;

    private String dateAdded;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
       public int getPlatformID() {
        return platformID;
    }

    public void setPlatformID(int platformID) {
        this.platformID = platformID;
    }
    
    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    
    public Game() {
    }
    
    public Game(String name, String status) {
        setID(0);
        setName(name);
        setStatus(status);
    }

   
    
    
    
}
