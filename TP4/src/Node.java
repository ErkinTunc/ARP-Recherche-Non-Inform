public class Node
{
    private State state ;

    private double g ; // g :cout du départ jusqu’a ici (somme des distance parcours)
    private double h ; // h :cout heuristique

    private Node parent ; // Parent node in the graph

    // Getters

    /**
     * 
     * @return double
     */
    public double f ()
    {
        return this.g + this.h ;
    }

}
