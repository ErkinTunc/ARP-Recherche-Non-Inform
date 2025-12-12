package src;

import java.util.HashMap;

/**
 * @description This class represents a computer player in the game.
 * * It extends the User class and inherits its properties and methods.
 * 
 * @author ELNASORY Karam
 * 
 */

public class Computer extends User 
{   
    /**
     * Constructor for Computer class, 
     * @param color The Color of the player.
     * @description creats a computer player with a default score of 15.
     */
    public Computer ()
    {
        super ("Computer", 15) ; // IDEA : Use a default color for the computer player
    }

    /**
     * Constructor for Computer class, 
     * @param score initial score of the player.
     * @param color The Color of the player tokens.
     * @description This constructor is meant to be used to set a custom score for the computer player.
     */
    public Computer (int score)
    {
        super ("Computer", score) ; // IDEA : Use a default color for the computer player
    }
    
    /**
     * This method is used to prompt the computer to choose a placement on the matrix.
     * It randomly chooses a placement on the matrix.
     * @param actualState The current state of the game.
     * @return Coordonates The coordinates of the placement.
     * @description This method is meant to be used with a backtracking algorithm to find the best placement.
     */
    @Override
    public Action choseAction (State actualState) 
    {
        return minmax_policiy(actualState) ;
    }

    /**
     * 
     * @param s State object 
     * @return
     */

    Action minmax_policiy(State s)
    {
        HashMap<Action, Integer> action_utilities = new HashMap<>() ;

        for (Action a : s.actions())
        {
            State succ = s.succ(a) ;
            int utility = succ.utility() ;
            action_utilities.put(a, utility) ;
        }

        Action best_action = null ;
        int best_utility = Integer.MAX_VALUE ;

        for (Action a : action_utilities.keySet())
        {
            int utility = action_utilities.get(a) ;
            if (utility < best_utility)
            {
                best_utility = utility ;
                best_action = a ;
            }
        }

        return best_action ;
    }
}
