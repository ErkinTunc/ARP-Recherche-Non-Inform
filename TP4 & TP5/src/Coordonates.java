package model;

/**
 * Coordonates class representing a point in a 2D space.
 * It contains the x and y coordinates.
 * @author ELNASORY Karam
 */

public class Coordonates
{
    /*
     */
    private final int x;
    private final int y;

    public Coordonates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public double distanceTo(Coordonates other)
    {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString()
    {
        return "Coordonates{ " + x + " , "  + y +" }" ;
    }
    
}
