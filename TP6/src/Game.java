/**
 * TP6 - Game Logic and Save/Load System
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Game implements Serializable {
    private final User player1;
    private final User player2;
    private User currentUser;
    private int n; // The game number

    /**
     * Constructor for a new Game
     */
    public Game(User p1, User p2, int startN) {
        this.player1 = p1;
        this.player2 = p2;
        this.n = startN;
        // Random starter
        this.currentUser = (new Random().nextBoolean()) ? player1 : player2;
    }

    /**
     * Main Game Loop.
     * Returns true if game finished, false if paused (menu).
     */
    public boolean play() {
        clear();
        System.out.println("GAME STARTING WITH N = " + n);
        
        while (n > 0) {
            State currentState = new State(n);
            
            // Ask current user for move
            Action action = currentUser.choseAction(currentState);

            // Logic to handle Pause/Menu
            if (action == null) {
                return false; // Game paused
            }

            // Apply move
            System.out.println(currentUser.name() + " chose to " + action);
            if (action == Action.DIVIDE_BY_2) {
                n = n / 2;
            } else {
                n = n - 1;
            }

            // Check Victory
            if (n == 0) {
                System.out.println("\n===========================");
                System.out.println("   🏆 " + currentUser.name() + " WINS! 🏆");
                System.out.println("===========================\n");
                return true; // Game Over
            }

            // Switch Player
            currentUser = (currentUser == player1) ? player2 : player1;
        }
        return true;
    }

    // --- SAVE & LOAD SYSTEM ---

    public void save() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(Calendar.getInstance().getTime());
        String fileName = timestamp + "_Halving_" + n + ".sav";
        
        File dir = new File("./SavedGames/");
        if (!dir.exists()) dir.mkdirs();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dir.getPath() + "/" + fileName))) {
            out.writeObject(this);
            System.out.println("Game saved successfully!");
        }
    }

    public static Game loadGame() {
        File[] files = new File("./SavedGames/").listFiles((d, name) -> name.endsWith(".sav"));
        
        if (files == null || files.length == 0) {
            System.out.println("No saved games found.");
            return null;
        }

        System.out.println("Saved games:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }

        System.out.print("Enter choice: ");
        try {
            int choice = Integer.parseInt(System.console().readLine());
            File fileToLoad = files[choice - 1];
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileToLoad))) {
                return (Game) in.readObject();
            }
        } catch (Exception e) {
            System.out.println("Error loading game.");
        }
        return null;
    }

    public static void clear() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) 
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else 
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {}
    }
}