package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class City {
    private final String name;

    private final Coordonates coordonates;

    public City(String name, Coordonates coordonates) {
        this.name = name;
        this.coordonates = coordonates;
    }

    // -------------- Getters --------------

    /**
     * 
     * @return
     */
    public String name() {
        return this.name;
    }

    /**
     * 
     * @return
     */
    public Coordonates coordonates() {
        return this.coordonates;
    }

    /**
     * @deprecated Because we now read multiple cities from a file
     *             Read a representation of a City from a file
     *             Format is : name x y
     * @param file
     * @return
     */
    public static City readCityFromFile(File file) {
        // read a line from the file
        // split the line into tokens
        // parse the tokens into name, x, y
        // create a Coordonates object
        // create and return a City object

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");

                if (tokens.length >= 3) {
                    String name = tokens[0];
                    int x = Integer.parseInt(tokens[1]);
                    int y = Integer.parseInt(tokens[2]);

                    Coordonates coordonates = new Coordonates(x, y);
                    return new City(name, coordonates);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return null;
    }

    /**
     * Read multiple City objects from a file
     * Each line in the file should be in the format : name x y
     * Charged cities should be limited to 15 entries
     * 
     * @param file
     * @return
     */
    public static ArrayList<City> readCitiesFromFile(File file) {
        ArrayList<City> cities = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            int count = 0;
            while (scanner.hasNextLine() && count < 10) {
                count++;
                String line = scanner.nextLine().trim();
                if (line.isEmpty())
                    continue;

                // split on whitespace (handles multiple spaces)
                String[] tokens = line.split("\\s+");
                if (tokens.length < 3)
                    continue;

                // Some input files may not include a textual name; use the first token as name
                String name = tokens[0];
                try {
                    int x = Integer.parseInt(tokens[1]);
                    int y = Integer.parseInt(tokens[2]);
                    Coordonates coord = new Coordonates(x, y);
                    cities.add(new City(name, coord));
                } catch (NumberFormatException nfe) {
                    // skip malformed lines
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return cities;
    }
}
