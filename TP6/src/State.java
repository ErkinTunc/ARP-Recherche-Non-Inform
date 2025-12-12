/**
 * 
 * @author ELNASORY Karam
 */



import java.util.ArrayList;

public class State
{
    private int n ;
    private int player ;

    public State (int player, int n)
    {
        this.player = player ;
        this. n = n ;
    }

    public boolean is_goal ()
    {
        return player == n ;
    }

    public int utility ()
    {
        if (is_goal())
            return -1 ;
        else
            return 1 ;
    }

    ArrayList <Action> actions ()
    {
        ArrayList <Action> acts = new ArrayList<>() ;

        if (n % 2 == 0) acts.add(Action.DIVIED_BY_2) ;
        if (n > 0 ) acts.add(Action.SUBTRACT_1) ;
        
        return acts ;
    }

    State succ (Action a)
    {
        int new_n = n ;
        if (a == Action.DIVIED_BY_2)
            new_n = n / 2 ;

        else if (a == Action.SUBTRACT_1)
            new_n = n - 1 ;

        return new State (1 - player % 2, new_n) ;
    }

    void display ()
    {
        System. out. println ("Player: " + player + " N: " + n) ;
    }
}
