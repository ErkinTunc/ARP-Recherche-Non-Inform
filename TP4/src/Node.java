package src ;

import java.util.ArrayList;

public class Node
{
    private State state ;

    private double g ; // g :cout du départ jusqu’a ici (somme des distance parcours)
    private double h ; // h :cout heuristique

    private Node parent ; // Parent node in the graph

    /**
     * 
     * @return State
     */
    public Node (State state)
    {
        this.state = state ;
    }

    // Getters

    /** 
     * 
     */
    public double g ()
    {
        return this.g ;
    }

    /**
     * 
     * @return
     */
    public double h ()
    {
        return this.h ;
    }

    /**
     * 
     * @return double
     */
    public double f ()
    {
        return this.g + this.h ;
    }

    /**
     * 
     * @return
     */
    public Node parentNode ()
    {
        return this.parent ;
    }

    /**
     * 
     * @return
     */
    public State state ()
    {
        return this.state ;
    }


    /**
     * 
     */

    public ArrayList<City> path ()
    {
        ArrayList<City> res = new ArrayList<>() ;
        Node currentNode = this ;

        while ( currentNode != null )
        {
            res.add(0, currentNode.state.currenCity() ) ;
            currentNode = currentNode.parentNode() ;
        }
        return res ;
    }
}
