
import java.util.ArrayList;


/**
 * 
 */
public class Problem
{
    private ArrayList<City> cities ;
    private City startCity ;
    
    public Problem ( ArrayList<City> cities , City startCity )
    {
        this.cities = cities ;
        this.startCity = startCity ;
    }
    /**
     * 
     * @return
     */
    public State initialState()
    {
        return new State(this.startCity);
    }

    /**
     * 
     * @return
     */
    public ArrayList<City> cities()
    {
        return new ArrayList<>(this.cities);
    }

    /**
     * 
     * @param state
     * @return
     */
    public boolean isGoalState(State state)
    {
        return ( state.visitedCities().size() == this.cities.size() ) ;
    }
    
    
}
