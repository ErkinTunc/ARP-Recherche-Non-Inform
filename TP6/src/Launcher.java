
/**
 * TP6 - Game Launcher and Menu System
 * 
 * Compilation : 
 * javac -d build src/*.java
 * 
 * Execution : 
 * java -cp build Launcher
 * 
 */

import java.io.IOException;

public class Launcher {
    private Game game;

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.mainMenu();
    }

    public void mainMenu() {
        Game.clear();
        System.out.println("=====================================");
        System.out.println("🎮  Welcome to HALVING GAME !!");
        System.out.println("=====================================");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.print("Choice: ");

        String input = System.console().readLine();

        if (input.equals("1")) {
            startingMenu();
        } else if (input.equals("2")) {
            this.game = Game.loadGame();
            if (this.game != null)
                runGameLoop();
            else
                mainMenu();
        } else if (input.equals("3")) {
            System.exit(0);
        } else {
            mainMenu();
        }
    }

    public void startingMenu() {
        System.out.println("\n--- New Game Setup ---");
        System.out.println("1. Human VS Human");
        System.out.println("2. Human VS Computer");
        System.out.println("3. Back");
        System.out.print("Choice: ");

        String choice = System.console().readLine();

        if (choice.equals("3")) {
            mainMenu();
            return;
        }

        System.out.print("Enter starting number N (e.g. 20): ");
        int n;
        try {
            n = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            startingMenu();
            return;
        }

        if (choice.equals("1")) {
            this.game = new Game(new Human(), new Human(), n);
        } else if (choice.equals("2")) {
            this.game = new Game(new Human(), new Computer(), n);
        } else {
            startingMenu();
            return;
        }

        runGameLoop();
    }

    /**
     * Runs the game. If play() returns false, it means user paused -> Show InGame
     * Menu.
     */
    public void runGameLoop() {
        boolean gameFinished = this.game.play();

        if (gameFinished) {
            System.out.println("Press Enter to return to Main Menu...");
            System.console().readLine();
            mainMenu();
        } else {
            inGameMenu();
        }
    }

    public void inGameMenu() {
        System.out.println("\n--- Game Paused ---");
        System.out.println("1. Resume");
        System.out.println("2. Save Game");
        System.out.println("3. Exit to Main Menu (Unsaved progress lost)");
        System.out.print("Choice: ");

        String choice = System.console().readLine();

        if (choice.equals("1")) {
            runGameLoop();
        } else if (choice.equals("2")) {
            try {
                this.game.save();
            } catch (IOException e) {
                System.out.println("Save failed: " + e.getMessage());
            }
            runGameLoop(); // Resume after save
        } else if (choice.equals("3")) {
            mainMenu();
        } else {
            inGameMenu();
        }
    }
}