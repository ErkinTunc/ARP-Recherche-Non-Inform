
/**
 * TP6 - Halving Game
 * 
 * This code is part of the TP6 project for the Halving Game.
 * It defines the possible actions a player can take during the game.
 */
import java.io.Serializable;

/**
 * Enum representing possible actions in the Halving Game.
 */
public enum Action implements Serializable {
    DIVIDE_BY_2,
    SUBTRACT_1;
    
    @Override
    public String toString() {
        switch(this) {
            case DIVIDE_BY_2: return "Divide by 2";
            case SUBTRACT_1: return "Subtract 1";
            default: return super.toString();
        }
    }
}