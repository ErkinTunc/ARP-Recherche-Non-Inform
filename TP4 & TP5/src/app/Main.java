package app;

import model.City;
import search.ResearchAlgo;

import model.*;
import search.*;
import algorithm.*;

// -------------------- HOW TO RUN -------------------
// Go to TP4 folder in terminal
//---------------------------------------------------

// WINDOWS
// Compile in to build :      javac -d build src\algorithm\*.java src\model\*.java src\search\*.java src\app\*.java
// Run from bin folder :      java -cp build app.Main
//---------------------------------------------------
// MAC / LINUX
// Compile in to build :      javac -d build src/algorithm/*.java src/model/*.java src/search/*.java src/app/*.java
// Run from bin folder :      java -cp build app.Main

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("us_capitals.txt");
        ArrayList<City> cities = City.readCitiesFromFile(file);
        System.out.println("Number of cities loaded: " + cities.size());

        City startCity = cities.get(0); // Assuming the first city is the starting point
        Problem problem = new Problem(cities, startCity);

        Node solutionNode = ResearchAlgo.depthFirstSearch(problem);
        if (solutionNode != null) {
            System.out.println("Solution found!");
            ArrayList<City> path = solutionNode.path();
            for (City city : path) {
                System.out.println(city.id() + " at " + city.coordonates().x() + ", " + city.coordonates().y());
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
