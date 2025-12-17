package Ex2_Tour_Cavalier_DFS;

public class Position {
    // a position is immutable : all fields are final
    public final int x;
    public final int y;
    
    /** ------------------ constructeurs ------------------ */
    /** Default constructor at (0,0) */
    public Position()
    {
        this(0,0);
    }

    /** Constructor with given coordinates. Can only be accessed via move method */
    private Position(int i, int j)
    {
        this.x = i;
        this.y = j;
    }

    /** ------------------ builders ------------------ */
    /** Retourne une nouvelle position après un déplacement (`dx`,`dy`) */
    public Position move(int dx, int dy)
    {
        return new Position(this.x + dx, this.y + dy);
    }
}
