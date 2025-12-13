package Ex2_Tour_Cavalier_DFS;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a uninformed research algorithm implementing a breadth-first search (BFS) strategy
 * Algorithme de recherche non informée implémentant une stratégie de parcours en profondeur (DFS)
 *
 */

// Transformr this algo into a depth-first search (DFS) algorithm
public class ResearchAlgorithm {

    int depthLimit = 10; // Limite de profondeur par défaut
    
    /**
     * Search for a solution to the given problem using DFS
     * @param problem
     * @return
     */
    public static Node DFS_Search(KnightsTourProblem problem)
    {
        int counter = 0;
        List<Node> frontier = new LinkedList<>();

        Node root = new Node(problem.initialState(), null, null);
        frontier.add(root); // Starts with the root node
        
        while ( ! frontier.isEmpty() )
        {
            Node currentNode = frontier.remove(0);
            counter += 1;
            
            if (problem.isGoalState(currentNode.getState())) // Condition d'arrêt qu'on va exploiter pour faire un tour fermé
            {
                System.out.println("Found a solution after evaluating " + counter + " nodes.");
                return currentNode;
            }

            frontier.addAll(0, currentNode.expand(problem));  // Exploration des enfants du noeud courant => parcours en profondeur
        }

        return null;
    }

    public static Node BFS_Search(KnightsTourProblem problem)
    {
        int counter = 0;
        List<Node> frontier = new LinkedList<>();

        Node root = new Node(problem.initialState(), null, null);
        frontier.add(root); // Starts with the root node
        
        while ( ! frontier.isEmpty() )
        {
            Node currentNode = frontier.remove(0);
            counter += 1;
            
            if (problem.isGoalState(currentNode.getState())) // Condition d'arrêt qu'on va exploiter pour faire un tour fermé
            {
                System.out.println("Found a solution after evaluating " + counter + " nodes.");
                return currentNode;
            }

            frontier.addAll(currentNode.expand(problem));  // Exploration des enfants du noeud courant => parcours en largeur
        }

        return null;
    }


    /**
     * Limited Depth-First Search
     * @param problem
     */
    public Node Limited_DFS_Search(KnightsTourProblem problem, int depthLimit)
    {
        int counter = 0;
        List<Node> frontier = new LinkedList<>();

        Node root = new Node(problem.initialState(), null, null);
        frontier.add(root); // Starts with the root node

        int nodeDepth = 0 ;
        int localNodePosition = 0 ; 
        
        while ( ! frontier.isEmpty() )
        {
            Node currentNode = frontier.remove(0);
            counter += 1;
            
            if (problem.isGoalState(currentNode.getState())) // Condition d'arrêt qu'on va exploiter pour faire un tour fermé
            {
                System.out.println("Found a solution after evaluating " + counter + " nodes.");
                return currentNode;
            }

            if ( nodeDepth < depthLimit)
            {
                frontier.addAll (0, currentNode.expand(problem).get(localNodePosition) );  // Exploration des enfants du noeud courant => parcours en profondeur
                localNodePosition += 1 ;
                nodeDepth += 1 ;
            }
        }
        // TODO : implémenter la recherche en profondeur limitée correctement
        return null;
    }


    /**
     * Limited Breadth-First Search
     */
    public Node Limited_BFS_Search(KnightsTourProblem problem, int depthLimit)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Iterative Deepening Depth-First Search
     */
    public Node Iter_DFS_Search(KnightsTourProblem problem)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Iterative Deepening Breadth-First Search
     */
    public Node Iter_BFS_Search(KnightsTourProblem problem)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}


