/**
 * ResearchAlgo class implementing search algorithms:
 * - Depth-First Search (DFS)
 * - Heuristic MST
 * - A* Search
 * - Branch & Bound
 */

package search;

import app.Problem;

import model.Node;
import model.State;
import model.City;
import model.Edge;
import algorithm.Kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class ResearchAlgo {

    // ===== Static variables for Branch & Bound =====
    private static double best = Double.POSITIVE_INFINITY;
    private static Node bestNode = null;

    // ===== Q2 : DFS =====
    public static Node depthFirstSearch(Problem problem) {
        // Initialize the stack with the initial state
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(problem.initialState(), null));

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            // Check if the current state is a goal state
            if (problem.isGoalState(currentNode.state())) {
                return currentNode; // Return the solution node
            }

            // Expand the current node and add its children to the stack
            for (City city : currentNode.state().actions(problem.cities())) {
                State s = currentNode.state();

                double stepCost = s.currentCity().coordonates()
                        .distanceTo(city.coordonates());

                State newState = s.successor(city, stepCost);

                Node childNode = new Node(newState, currentNode);
                stack.push(childNode);
            }
        }
        return null; // No solution found
    }

    // ===== Heuristic MST =====
    /**
     * Computes the MST heuristic for A* and Branch & Bound.
     * The heuristic is the weight of the Minimum Spanning Tree
     * of the remaining unvisited cities.
     * 
     * @param state   Current state
     * @param problem Problem containing all cities
     * @return MST weight of unvisited cities
     */
    public static double heuristicMST(State state, Problem problem) {
        ArrayList<City> allCities = problem.cities();
        ArrayList<City> visited = state.visitedCities();

        // Get remaining (unvisited) cities
        ArrayList<City> remaining = new ArrayList<>();
        for (City c : allCities) {
            if (!visited.contains(c)) {
                remaining.add(c);
            }
        }

        // If 0 or 1 remaining cities, MST weight is 0
        if (remaining.size() <= 1) {
            return 0;
        }

        // Build edges between remaining cities
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < remaining.size(); i++) {
            for (int j = i + 1; j < remaining.size(); j++) {
                City a = remaining.get(i);
                City b = remaining.get(j);
                double dist = a.coordonates().distanceTo(b.coordonates());
                // Edge uses city indices in the remaining list (0-indexed)
                edges.add(new Edge(i, j, dist));
            }
        }

        // Compute MST weight using Kruskal's algorithm
        return Kruskal.run(remaining.size(), edges);
    }

    // ===== Q3 : A* Search =====
    /**
     * A* search algorithm using MST heuristic.
     * 
     * @param problem The TSP problem
     * @return The goal node if found, null otherwise
     */
    public static Node aStar(Problem problem) {
        PriorityQueue<Node> open = new PriorityQueue<>(
                Comparator.comparingDouble(Node::f));

        // Create start node with heuristic
        State startState = problem.initialState();
        double h = heuristicMST(startState, problem);
        Node startNode = new Node(startState, null, h);
        open.add(startNode);

        while (!open.isEmpty()) {
            Node currentNode = open.poll();

            // Check if goal reached
            if (problem.isGoalState(currentNode.state())) {
                return currentNode;
            }

            // Expand current node
            for (City city : currentNode.state().actions(problem.cities())) {
                State s = currentNode.state();

                double stepCost = s.currentCity().coordonates()
                        .distanceTo(city.coordonates());

                State newState = s.successor(city, stepCost);

                // Create child node with heuristic
                double childH = heuristicMST(newState, problem);
                Node childNode = new Node(newState, currentNode, childH);

                open.add(childNode);
            }
        }
        return null; // No solution found
    }

    // ===== Q4 : Branch & Bound =====
    /**
     * Resets the Branch & Bound static variables.
     * Call this before starting a new Branch & Bound search.
     */
    public static void resetBranchBound() {
        best = Double.POSITIVE_INFINITY;
        bestNode = null;
    }

    /**
     * Returns the best node found by Branch & Bound.
     * Call this after branchBound() completes.
     * 
     * @return The best node found
     */
    public static Node getBestNode() {
        return bestNode;
    }

    /**
     * Returns the best cost found by Branch & Bound.
     * 
     * @return The best cost
     */
    public static double getBestCost() {
        return best;
    }

    /**
     * Branch & Bound algorithm using MST heuristic for pruning.
     * 
     * @param state   Current state
     * @param problem The TSP problem
     * @param cost    Cost so far (g value)
     * @param parent  Parent node
     */
    public static void branchBound(State state, Problem problem, double cost, Node parent) {
        // Compute lower bound (cost so far + heuristic)
        double bound = cost + heuristicMST(state, problem);

        // Prune if bound >= best known solution
        if (bound >= best) {
            return;
        }

        // Create current node
        Node currentNode = new Node(state, parent);

        // Check if goal reached
        if (problem.isGoalState(state)) {
            best = cost;
            bestNode = currentNode;
            return;
        }

        // Expand children
        for (City city : state.actions(problem.cities())) {
            double stepCost = state.currentCity().coordonates()
                    .distanceTo(city.coordonates());

            State newState = state.successor(city, stepCost);

            branchBound(newState, problem, cost + stepCost, currentNode);
        }
    }

    /**
     * Convenience method to run Branch & Bound from a Problem.
     * Resets the static variables, runs the algorithm, and returns the best node.
     * 
     * @param problem The TSP problem
     * @return The best node found
     */
    public static Node branchBoundSearch(Problem problem) {
        resetBranchBound();
        State initialState = problem.initialState();
        branchBound(initialState, problem, 0, null);
        return getBestNode();
    }
}