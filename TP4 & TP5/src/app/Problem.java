package app;

import model.City;
import model.State;

import java.util.ArrayList;

public class Problem {
    private ArrayList<City> cities;
    private City startCity;

    public Problem(ArrayList<City> cities, City startCity) {
        this.cities = cities;
        this.startCity = startCity;
    }

    public State initialState() {
        return new State(this.startCity);
    }

    public ArrayList<City> cities() {
        return new ArrayList<>(this.cities);
    }

    public boolean isGoalState(State state) {
        return (state.visitedCities().size() == this.cities.size());
    }

}
