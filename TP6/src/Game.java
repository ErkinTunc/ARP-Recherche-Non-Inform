
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;


/**
 * Game class representing a game of Halving
 * It contains the players and the integer n that player can modify
 * @author ELNASORY Karam
 */

public class Game implements Serializable
{
    private final User player1 ;
    private final User player2 ;

    private User nextUser ;

    private State currentState ;

    // private final ArrayList< User > players ; // IDEA


    /**
     * 
     * @param n
     */

    public Game(int n)
    {
        this.n = n;

        this.player1 = new Human() ;
        this.player2 = new Computer() ;
    }

    /**
     * @description Constructor for Game class, Human vs Human
     * @param player1 the first player
     * @param player2 the second player
     */
    public Game ( Human player1, Human player2, int n ) 
    {
        this.n = n ;
        
        this.player1 = player1 ;
        this.player2 = player2 ;
    }
    
    /**
     * 
     */
    public State currentState ()
    {
        return this.currentState ;
    }
    
    private int n ; // The integer that players can modify


    public State initial_state () 
    {
        State s = new State (0, this.n) ;
        return s ;
    }

    Boolean is_over (State s)
    {
        return s.is_goal() ;
    }

    Action minmax_policiy(State s)
    {
        HashMap<Action, Integer> action_utilities = new HashMap<>() ;

        for (Action a : s.actions())
        {
            State succ = s.succ(a) ;
            int utility = succ.utility() ;
            action_utilities.put(a, utility) ;
        }

        Action best_action = null ;
        int best_utility = Integer.MAX_VALUE ;

        for (Action a : action_utilities.keySet())
        {
            int utility = action_utilities.get(a) ;
            if (utility < best_utility)
            {
                best_utility = utility ;
                best_action = a ;
            }
        }

        return best_action ;
    }


    int play ()
    {
        State current_state = initial_state() ;

        while (!is_over(current_state))
        {
            current_state.display() ;
            Action a = minmax_policiy(current_state) ;
            current_state = current_state.succ(a) ;
        }

        current_state.display() ;
        return current_state.utility() ;
    }   

    /**
     * @description Save the instance in a file
     * @throws IOException
     */
    public void save() throws IOException 
    {
        // Format the current date-time safely
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(Calendar.getInstance().getTime());

        // Clean player names to be safe in filenames (optional but good practice)
        String player1Name = player1.name().replaceAll("[^a-zA-Z0-9_-]", "_");
        String player2Name = player2.name().replaceAll("[^a-zA-Z0-9_-]", "_");

        // Construct safe filename
        String fileName = timestamp + "_" + player1Name + "_vs_" + player2Name + ".sav";
        String destination = "./SavedGames/" + fileName;

        // Create directory if it doesn't exist
        File dir = new File("./SavedGames/");
        if (!dir.exists()) 
        {
            dir.mkdirs();
        }

        // Write the object to file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destination))) 
        {
            out.writeObject(this);
        }
    }

    /**
     * @description Load a Game instance from a file
     * @param fileName of the file that contains the Game object to be loaded 
     * @return A Game instance
     */
    public static Game load (String fileName)
    {
        try ( ObjectInputStream in = new ObjectInputStream( new FileInputStream( "./SavedGames/" + fileName) ) ) 
        {
            Game game = (Game) in.readObject() ;
            return game ;
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        return null ;
    }

    /**
     * @description Discover the saved games in the directory "SavedGames", and display them to the user.
     */
    public static File[] discoverSavedGames ()
    {
        File directory = new File("./SavedGames/") ;

        File[] files = directory.listFiles() ;

        int fileIndex = 0 ;
        if ( files != null && files.length > 0 )
        {
            System.out.println("Saved games:") ;
            for ( File file : files )
            {
                if ( file.isFile() )
                {
                    System.out.println( ++fileIndex + ". " + file.getName() ) ;
                }
            }
        }
        else
        {
            System.out.println("No saved games found.") ;
        }
        return files ;
    }

    /**
     * @description Load a game from the saved games
     * @return A Game instance
     */
    public static Game loadGame ()
    {
        File[] files = Game.discoverSavedGames();

        System.out.print("Enter the number of the game you want to load: ") ;
        int choice = Integer.parseInt(System.console().readLine()) ;
        if ( choice < 1 || choice > files.length )
        {
            System.out.println("Invalid choice") ;
            return null ;
        }
        String fileName = files[choice].getName() ;

        Game game = Game.load(fileName) ;
        if ( game == null )
        {
            System.out.println("Error loading game") ;
            return null ;
        }
        else
        {
            System.out.println("Game loaded successfully") ;
            return game ;
        }
    }
    
    /**
     * Start the game 
     * 
     */
    public User start () 
    {
        Random random = new Random () ; 
        int starterChoice = random.nextInt(2) ;

        if ( starterChoice == 0 )
        {
            this.nextUser = this.player1 ;
        }
        else 
        {
            this.nextUser = this.player2 ;
        }
        
        this.nextUser.choseAction( this.currentState ) ; 

        // Swicht currentUser
        this.nextUser = ( this.nextUser == this.player1 ) ? this.player2 : this.player1 ;
        
        return this.continu() ;
    }

    /**
     * @description Continue the game until one of the players wins or there are no more tokens left
     * @return The user who won the game 
     */
    public User continu ()
    {
        clear() ;
        
        while (!this.currentState.is_goal() )
        {
            this.nextUser = ( this.nextUser == this.player1 ) ? this.player2 : this.player1 ;
            clear () ;
            System.out.println( this.currentState.toString() ) ;
        }

        if ( this.currentState.is_goal() )
        {
            System.out.println("Player " + (( this.nextUser == this.player1 ) ? this.player2 : this.player1).name() + " won the game") ;
            return this.nextUser ;
        }

        return nextUser ;
    }

    /**
     * @description Clear the console, to get a cleaner terminal
     * 
     */
    public static void clear() 
    {
        try 
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } 
        catch (final Exception e) 
        {
            System.out.println("Erreur lors de l'effacement de la console : " + e.getMessage());
        }
    }
}