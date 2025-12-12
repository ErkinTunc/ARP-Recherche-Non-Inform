package model;

import java.util.ArrayList;

public class State
{
    private City currentCity ;
    private ArrayList<City> visitedCities ;

    /**
     *  
     * @param currentCity
     * @param visitedCities
     */
    public State ( City currentCity , ArrayList<City> visitedCities )
    {
        this.currentCity = currentCity ;
        this.visitedCities = visitedCities ;
    }

    /**
     * 
     * @param currentCity
     */
    public State ( City currentCity )
    {
        this.currentCity = currentCity ;
        this.visitedCities = new java.util.ArrayList<>() ;
        this.visitedCities.add(currentCity) ;
    }

    // Getters

    /**
     * 
     * @return
     */
    public ArrayList<City> visitedCities()
    {
        return this.visitedCities ;
    }

    /**
     * 
     * @return
     */
    public City currenCity ()
    {
        return this.currentCity ;
    }

    /**
     * Retruns an ArrayList of cities that have not been visited yet
     * @param Cities
     * @return
     */
    public ArrayList<City> actions(ArrayList <City> cities)
    {
        ArrayList<City> res = new ArrayList<>(cities);
        res.removeAll(this.visitedCities) ;
        res.remove(currentCity) ;
        return res;
    }

    /**
     * 
     * @return
     */
    public State succession(State state, City cityToVisit)
    {
        ArrayList<City> newVisitedCities = new ArrayList<>(state.visitedCities()) ;
        newVisitedCities.add(cityToVisit) ;
        return new State(cityToVisit, newVisitedCities) ;
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
