/**
 * Main class to run the search algorithm on the city problem.
 */

package app;

import search.ResearchAlgo;

import model.*;
import search.*;
import algorithm.*;

// -------------------- HOW TO RUN -------------------

// Go to TP4 folder in terminal
//---------------------------------------------------

// Compile in to build :      javac -d build src\algorithm\*.java src\model\*.java src\search\*.java src\app\*.java
// Run from bin folder :      java -cp build app.Main
//---------------------------------------------------
// MAC / LINUX
// Compile in to build :      javac -d build src/algorithm/*.java src/model/*.java src/search/*.java src/app/*.java
// Run from bin folder :      java -cp build app.Main

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // -------------- Problem Definition --------------
        int startCityId = 0; // Set the starting city ID
        int maxCitiesLoaded = 10; // Set the maximum number of cities to load

        // -------------- Load Cities from File --------------
        File file = new File("us_capitals.txt");
        ArrayList<City> cities = City.readCitiesFromFile(file, maxCitiesLoaded);
        System.out.println("Number of cities loaded: " + cities.size());

        // -------------- Problem Setup and Solution Search --------------
        City startCity = cities.get(startCityId); // Assuming the first city is the starting point
        Problem problem = new Problem(cities, startCity);

        Node solutionNode = ResearchAlgo.depthFirstSearch(problem);

        if (solutionNode != null) {
            System.out.println("Solution found!");

            System.out.println("Cost of solution: " + solutionNode.state().g());

            System.out.println("Path to solution:");
            for (City city : solutionNode.state().visitedCities()) {
                System.out.println(
                        city.id() + " at " +
                                city.coordonates().x() + ", " +
                                city.coordonates().y());
            }
        } else {
            System.out.println("No solution found.");
        }

        // -------------- A* Search --------------
        System.out.println("\n---------------------------------------------------");
        System.out.println("Starting A* Search...");
        Node aStarSolution = ResearchAlgo.aStar(problem);

        if (aStarSolution != null) {
            System.out.println("A* Solution found!");
            System.out.println("Cost of solution: " + aStarSolution.state().g());
            System.out.println("Path to solution:");
            for (City city : aStarSolution.state().visitedCities()) {
                System.out.println(city.id() + " at " + city.coordonates().x() + ", " + city.coordonates().y());
            }
        } else {
            System.out.println("No A* solution found.");
        }

        // -------------- Branch & Bound Search --------------
        System.out.println("\n---------------------------------------------------");
        System.out.println("Starting Branch & Bound Search...");
        Node bbSolution = ResearchAlgo.branchBoundSearch(problem);

        if (bbSolution != null) {
            System.out.println("Branch & Bound Solution found!");
            System.out.println("Cost of solution: " + bbSolution.state().g());
            System.out.println("Path to solution:");
            for (City city : bbSolution.state().visitedCities()) {
                System.out.println(city.id() + " at " + city.coordonates().x() + ", " + city.coordonates().y());
            }
        } else {
            System.out.println("No Branch & Bound solution found.");
        }
    }
}
