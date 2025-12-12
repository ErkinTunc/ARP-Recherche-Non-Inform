package src ;

import java.io.Serializable;

/**
 * User abstract class representing a player in the game.
 * It contains the player's name, tokens color and score.
 * @author ELNASORY Karam
 */
public abstract class User implements Serializable
{
    private final String name ;

    /**
     * Getter method for the player name.
     * @return User name (String)
     */

    public String name ()
    {
        return name ;
    }
    
    int score ;
    
    /**
     * Getter methode for the player score.
     * @return int
     */
    public int score ()
    {
        return score ;
    }

    /**
     * Constructor for User class.
     * @param name The name of the player.
     * @param score The score of the player.
     * @param playerColor The color of the player.
     */
    public User (String name, int score) 
    {
        this.name = name ;
        this.score = score ;
    }

    public abstract Action choseAction (State actualState) ;

    /**
     * Methode to return the object state as a string.
     * @return String representation of the object.
     */
    public String toString () 
    {
        StringBuilder sb = new StringBuilder() ;

        sb.append ("Name : ") ;
        sb.append (name) ;
        sb.append (" | ") ;

        sb.append ("Score : ") ;
        sb.append (score) ;
        sb.append (" | ") ;

        return sb.toString() ;
    }
}