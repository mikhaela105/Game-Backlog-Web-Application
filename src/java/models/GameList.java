package models;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 * Stateful Bean that represents an ArrayList of Games
 * 
 * @author Mikhaela Tajonera
 */
@Stateful
public class GameList implements Serializable {
    
    private ArrayList<Game> gameList;

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> list) {
        this.gameList = list;
    }
    
    public GameList() {
    }
    
    public GameList(ArrayList<Game> list){
        setGameList(list);
    }
    
}
