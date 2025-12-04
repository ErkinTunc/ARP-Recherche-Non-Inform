package src ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class City
{
    private String name ;

    private Coordonates coordonates ;

    public City ( String name , Coordonates coordonates )
    {
        this.name = name ;
        this.coordonates = coordonates ;
    }

    // -------------- Getters --------------

    /**
     * 
     * @return
     */
    public String name ()
    {
        return this.name ;
    }
    
    /**
     * 
     * @return
     */
    public Coordonates coordonates ()
    {
        return this.coordonates ;
    }

    /**
     * Read a representation of a City from a file
     * Format is : name x y
     * @param file
     * @return
     */
    public static City readCityFromFile (File file)
    {
        // read a line from the file
        // split the line into tokens
        // parse the tokens into name, x, y
        // create a Coordonates object
        // create and return a City object
        
        try (Scanner scanner = new Scanner(file))
        {
            if (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                
                if (tokens.length >= 3)
                {
                    String name = tokens[0];
                    int x = Integer.parseInt(tokens[1]);
                    int y = Integer.parseInt(tokens[2]);
                    
                    Coordonates coordonates = new Coordonates(x, y);
                    return new City(name, coordonates);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found: " + e.getMessage());
        }
        
        return null;
    }

    public static ArrayList<City> readCitiesFromFile ( File file )
    {
        ArrayList<City> cities = new ArrayList<>() ;
        
        File[] files = file.listFiles() ;
        
        if ( files != null )
        {
            for ( line : file )
            {
                City city = City.readCityFromFile(file) ;

                if ( city != null )
                {
                    cities.add(city) ;
                }
            }
        }
        
        return cities ;
    }
}
