package  src;

import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        File file = new File("us_capitals.txt");
        ArrayList<City> cities = City.readCitiesFromFile(file);
        
        City startCity = cities.get(0); // Assuming the first city is the starting point
        Problem problem = new Problem(cities, startCity);

        Node solutionNode = ResearchAlgo.depthFirstSearch(problem);
        if (solutionNode != null) {
            System.out.println("Solution found!");
            ArrayList<City> path = solutionNode.path();
            for (City city : path) {
                System.out.println(city.name() + " at " + city.coordonates().x() + ", " + city.coordonates().y());
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
