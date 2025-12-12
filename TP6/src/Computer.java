/**
 * TP6 - MinMax Algorithm Implementation
 */

import java.util.List;

public class Computer extends User {

    public Computer() {
        super("Computer");
    }

    @Override
    public Action choseAction(State currentState) {
        System.out.println("\nComputer is thinking...");
        try {
            Thread.sleep(800); // Small delay for realism
        } catch (InterruptedException e) { e.printStackTrace(); }

        return minmax_policy(currentState);
    }

    /**
     * Entry point for MinMax algorithm.
     */
    private Action minmax_policy(State s) {
        List<Action> actions = s.getAvailableActions();
        
        Action bestAction = actions.get(0);
        int bestValue = Integer.MIN_VALUE;

        for (Action a : actions) {
            // We want to maximize our gain. 
            // The opponent will try to minimize our gain (so we call min).
            int value = minValue(s.succ(a));
            if (value > bestValue) {
                bestValue = value;
                bestAction = a;
            }
        }
        return bestAction;
    }

    private int maxValue(State s) {
        if (s.isGoal()) return -1; // If I receive 0, I lost.
        
        int v = Integer.MIN_VALUE;
        for (Action a : s.getAvailableActions()) {
            v = Math.max(v, minValue(s.succ(a)));
        }
        return v;
    }

    private int minValue(State s) {
        if (s.isGoal()) return 1; // If opponent receives 0, I won.
        
        int v = Integer.MAX_VALUE;
        for (Action a : s.getAvailableActions()) {
            v = Math.min(v, maxValue(s.succ(a)));
        }
        return v;
    }
}