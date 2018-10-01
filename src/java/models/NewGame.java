package models;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 * Bean that represents a new game object, similar to Game class
 * but with additional variables
 * 
 * @author Mikhaela Tajonera
 */
@Stateless
public class NewGame implements Serializable {

    private String name;
    private String status;
    private boolean isAdded;
    private int errorNumber;
    private int platformID;

    private String dateAdded;

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

    public boolean isIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    
     public int getPlatformID() {
        return platformID;
    }

    public void setPlatformID(int platformID) {
        this.platformID = platformID;
    }

    public NewGame() {
    }

    public NewGame(Game game) {
        setName(game.getName());
        setStatus(game.getStatus());
        setIsAdded(false);
        setErrorNumber(0);
        setPlatformID(game.getPlatformID());
    }

}
