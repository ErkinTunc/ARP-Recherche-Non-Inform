
package src ;

import java.util.List;

/**
 * 
 */
public class Problem
{
    private List<City> cities ;
    private City startCity ;
    
    public Problem ( List<City> cities , City startCity )
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
    public State succession(State state, City cityToVisit)
    {

    }

    /**
     * 
     * @param state
     * @return
     */
    public boolean isGoalState(State state)
    {
        if (state.visitedCities() )
    }
    
    /**
     * 
     * @return
     */
    public List<Action> actions()
    {

    }
    
}
