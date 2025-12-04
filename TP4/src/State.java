package src ;

import java.util.List;

public class State
{
    private City currentCity ;
    private List<City> visitedCities ;

    public State ( City currentCity , List<City> visitedCities )
    {
        this.currentCity = currentCity ;
        this.visitedCities = visitedCities ;
    }


    /**
     * 
     */
    @Override
    public String toString()
    {
        return "Current City: " + this.currentCity.name() + ", Visited Cities: " + this.visitedCities.size() ;
    }
}
