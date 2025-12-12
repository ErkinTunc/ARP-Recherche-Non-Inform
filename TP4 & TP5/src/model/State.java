package model;

import java.util.ArrayList;

public class State {
    private final City currentCity; // current city
    private final ArrayList<City> visitedCities; // visited list
    private final ArrayList<Integer> path; // city ids (or indexes)
    private final double g; // cost so far

    public State(City currentCity, ArrayList<City> visitedCities, ArrayList<Integer> path, double g) {
        this.currentCity = currentCity;
        this.visitedCities = visitedCities;
        this.path = path;
        this.g = g;
    }

    public State(City startCity) {
        this.currentCity = startCity;

        this.visitedCities = new ArrayList<>();
        this.visitedCities.add(startCity);

        this.path = new ArrayList<>();
        this.path.add(startCity.id()); // or index, but be consistent

        this.g = 0.0;
    }

    // ----- Getters -----
    public City currentCity() {
        return currentCity;
    }

    public ArrayList<City> visitedCities() {
        return visitedCities;
    }

    public ArrayList<Integer> path() {
        return path;
    }

    public double g() {
        return g;
    }

    // ----- Actions -----
    public ArrayList<City> actions(ArrayList<City> allCities) {
        ArrayList<City> res = new ArrayList<>();
        for (City c : allCities) {
            if (!visitedCities.contains(c)) { // still O(n) per check, but simpler
                res.add(c);
            }
        }
        return res;
    }

    // ----- Successor -----
    public State successor(City cityToVisit, double stepCost) {
        ArrayList<City> newVisited = new ArrayList<>(visitedCities);
        newVisited.add(cityToVisit);

        ArrayList<Integer> newPath = new ArrayList<>(path);
        newPath.add(cityToVisit.id());

        return new State(cityToVisit, newVisited, newPath, g + stepCost);
    }

    @Override
    public String toString() {
        return "Current City: " + currentCity.id() + ", Visited: " + visitedCities.size() + ", g=" + g;
    }
}
