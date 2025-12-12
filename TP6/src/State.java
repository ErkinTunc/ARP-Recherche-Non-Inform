/**
 * TP6 - Game of Reducing N to 0
 * 
 * This code defines the State class representing the state of the game,
 * where players can either subtract 1 from N or divide N by 2 if it's even.
 * The goal is to reach N = 0.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of the game (the number N).
 */
public class State implements Serializable {
    private final int n;

    public State(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    /**
     * Checks if the game is over (N == 0).
     */
    public boolean isGoal() {
        return n == 0;
    }

    /**
     * Returns valid actions based on current N.
     */
    public List<Action> getAvailableActions() {
        List<Action> actions = new ArrayList<>();
        if (n > 0)
        {
            actions.add(Action.SUBTRACT_1);
            actions.add(Action.DIVIDE_BY_2);
        }
        return actions;
    }

    /**
     * Generates the next state based on an action.
     */
    public State succ(Action a) {
        if (a == Action.DIVIDE_BY_2) {
            return new State(n / 2);
        } else if (a == Action.SUBTRACT_1) {
            return new State(n - 1);
        }
        return this; // Should not happen
    }

    @Override
    public String toString() {
        return "Current Value N = " + n;
    }
}