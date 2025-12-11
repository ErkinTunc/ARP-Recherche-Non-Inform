/**
 * 
 * @author ELNASORY Karam
 */

package src;
import java.util.HashMap;

public class Game
{
    private int n ;

    public Game(int n)
    {
        this.n = n;
    }

    public State initial_state () 
    {
        State s = new State (0, this.n) ;
        return s ;
    }

    Boolean is_over (State s)
    {
        return s.is_goal() ;
    }

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


    int play ()
    {
        State current_state = initial_state() ;

        while (!is_over(current_state))
        {
            current_state.display() ;
            Action a = minmax_policiy(current_state) ;
            current_state = current_state.succ(a) ;
        }

        current_state.display() ;
        return current_state.utility() ;
    }   
}
