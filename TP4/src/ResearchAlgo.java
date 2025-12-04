package src ;

import java.util.Stack;

public class ResearchAlgo
{
    // DFS modifier

    public static Node depthFirstSearch(Problem problem)
    {
        // Initialize the stack with the initial state
        Stack<Node> stack = new Stack<>();
        stack.push( new Node(problem.initialState() , null) );

        while (!stack.isEmpty())
        {
            Node currentNode = stack.pop();

            // Check if the current state is a goal state
            if (problem.isGoalState(currentNode.state()))
            {
                return currentNode; // Return the solution node
            }

            // Expand the current node and add its children to the stack
            for (City city : currentNode.state().actions(problem.cities()))
            {
                State newState = new State(city, currentNode.state().visitedCities());

                Node childNode = new Node(newState, currentNode);
                stack.push(childNode);
            }
        }

        return null; // No solution found
    }
}