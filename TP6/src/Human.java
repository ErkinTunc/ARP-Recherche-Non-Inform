/**
 * TP6 - Game of Reducing N to 0
 * 
 * This code defines the Human class representing a human player in the game,
 * where players can either subtract 1 from N or divide N by 2 if it's even.
 * The goal is to reach N = 0.
 */

import java.util.List;

public class Human extends User {

    public Human() {
        super(promptForName());
    }
    
    // Constructor used when loading or internal creation
    public Human(String name) {
        super(name);
    }

    public static String promptForName() {
        System.out.print("Enter your name: ");
        return System.console().readLine();
    }

    @Override
    public Action choseAction(State currentState) {
        System.out.println("\n--- It is " + this.name + "'s turn ---");
        System.out.println("Current Number: " + currentState.getN());
        
        List<Action> actions = currentState.getAvailableActions();
        
        System.out.println("Available moves:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + actions.get(i));
        }
        System.out.println("M. Menu / Save");

        System.out.print("Choose your move: ");
        String input = System.console().readLine();

        // Handle Menu request
        if (input.equalsIgnoreCase("M")) {
            return null; // Signal to Game to open menu
        }

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= actions.size()) {
                return actions.get(choice - 1);
            }
        } catch (NumberFormatException e) {
            // Ignore
        }

        System.out.println("Invalid input. Try again.");
        return choseAction(currentState);
    }
}