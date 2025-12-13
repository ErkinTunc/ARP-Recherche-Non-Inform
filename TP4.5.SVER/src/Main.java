import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<City> cities = new ArrayList<>();
        BufferedReader br = new BufferedReader(
            new FileReader("us_capitals.txt")
        );
        String line;
        while ((line = br.readLine()) != null) {
            String[] t = line.split(" ");
            cities.add(
                new City(
                    Integer.parseInt(t[0]),
                    Double.parseDouble(t[1]),
                    Double.parseDouble(t[2])
                )
            );
        }
        br.close();

        City startCity = cities.get(0);
        State start = new State();
        start.current = startCity;
        start.visited = new HashSet<>();
        start.visited.add(startCity);

        // Q2 DFS
        Node dfsSol = ResearchAlgo.dfs(start, cities, 0, null);
        System.out.println("DFS coût = " + dfsSol.g);

        // Q3 A*
        // limit city number to 15
        Node aStarSol = ResearchAlgo.aStar(start, cities.subList(0, 20));
        System.out.println("A* coût = " + aStarSol.g);

        // Q4 Branch & Bound
        // limit city number to 15
        ResearchAlgo.branchBound(start, cities.subList(0, 15), 0, null);
        System.out.println("B&B coût = " + ResearchAlgo.best);
    }
}
