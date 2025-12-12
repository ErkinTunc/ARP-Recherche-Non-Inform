package model;

import java.util.ArrayList;

public class State {
    private City currentCity; // Current city in the state
    private double cost; // Cost to reach this state

    private ArrayList<City> visitedCities; // List of visited cities
    private ArrayList<Integer> path; // Path taken to reach this state

    private double g; // g :cout du départ jusqu’a ici (somme des distance parcours)

    public State(City currentCity, ArrayList<City> visitedCities, ArrayList<Integer> path, double cost, double g) {
        this.currentCity = currentCity;
        this.visitedCities = visitedCities;
        this.path = new ArrayList<>();
        this.cost = cost;
        this.g = g;
    }

    public State(City currentCity) {
        this.currentCity = currentCity;
        this.visitedCities = new java.util.ArrayList<>();
        this.visitedCities.add(currentCity);
        this.path = new ArrayList<>();
        this.cost = 0;
        this.g = 0;
    }

    // -------------- Getters --------------

    public ArrayList<City> visitedCities() {
        return this.visitedCities;
    }

    public City currentCity() {
        return this.currentCity;
    }

    public ArrayList<City> actions(ArrayList<City> cities) {
        ArrayList<City> res = new ArrayList<>(cities);
        res.removeAll(this.visitedCities);
        res.remove(currentCity);
        return res;
    }

    // -------------- Methods --------------

    public State succession(State state, City cityToVisit, ArrayList<Integer> newPath, double stepCost) {
        ArrayList<City> newVisitedCities = new ArrayList<>(state.visitedCities());
        newVisitedCities.add(cityToVisit);
        return new State(cityToVisit, newVisitedCities, newPath, state.cost + stepCost , state.g + stepCost);
    }

    @Override
    public String toString() {
        return "Current City: " + this.currentCity.id() + ", Visited Cities: " + this.visitedCities.size();
    }
}
