/**
 * TP6 - Game of Reducing N to 0
 * 
 * This code defines the abstract User class representing a player in the game,
 * where players can either subtract 1 from N or divide N by 2 if it's even.
 * The goal is to reach N = 0.
 */

import java.io.Serializable;

/**
 * Abstract class representing a player.
 */
public abstract class User implements Serializable {
    protected String name;
    protected int score; // Optional for this game, but kept for compatibility

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    public String name() {
        return name;
    }

    /**
     * Determines the move to make based on the current state.
     * Returns NULL if the user wants to pause/exit (for Human).
     */
    public abstract Action choseAction(State currentState);

    @Override
    public String toString() {
        return name;
    }
}