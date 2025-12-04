package src ;

import java.io.File

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
    public static City readFromFile (File file)
    {
        // read a line from the file
        // split the line into tokens
        // parse the tokens into name, x, y
        // create a Coordonates object
        // create and return a City object
        
    }
}
