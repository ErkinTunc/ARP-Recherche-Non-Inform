package src ;

/**
 * Humain class representing a human player in the game.
 * It extends the User class.
 * @author ELNASORY Karam
 */

public class Human extends User
{
    /**
     * @description Constructor for Humain class, meant to be used to create a custom player.
     * @param name The name of the player.
     * @param score The score of the player.
     */
    public Human (String name, int score) 
    {
        super(name, score) ;
    }

    /**
     * @description Constructor for Humain class, meant to be used for new players.
     * @param name name of the player
     * @param color color of the player
     * @deprecated
     */
    public Human (String name) 
    {
        super(name, 15) ;
    }

    /**
     * @description Constructor for Humain class, for new players, interact's with the user.
     * @param color color of the player
     * Will prompt the user for their name, and init the score to 15 (default).
     * @deprecated
     */
    public Human ()
    {
        super (promptForName(), 15) ;
    }

    /**
     * @description This constructor is meant to be used with 
     * @param score to be parametered if we want to change the game initial conditions.
     */
    public Human (int score)     {
        super ( promptForName(), score, promptForChoice() ) ;
    }

    /**
     * @description Method to prompt the user for their name.
     * @return The name of the player as a String.
     */
    public static String promptForName () 
    {
        System.out.print("Enter your name: ") ;

        String name = System.console().readLine() ;  

        return name ;
    }

    public static Action promptForChoice()    {
        System.out.print("Choose a color: ") ;
        Action.values() ;

        String colorInput = System.console().readLine() ;
        int colorIndex = Integer.parseInt(colorInput) ;

        Action[] colors = Action.values();

        try 
        {

        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Please enter a valid number!");
            return promptFor() ; 
        }
        return null ; // This line should never be reached, it is just to satisfy the compiler and make the typing system 
    }

    /**
     * @description Method to choose a ActionToTake on the board to play a move (place a token).
     * @return Coordonates object representing the chosen ActionToTake.
     */
    @Override
    public Action choseAction (State currentState) 
    {
        System.out.print("Enter your move (x y): ") ;
        String input = System.console().readLine() ;
        
        String[] parts = input.split("\\s+") ;

        Action ActionToTake = null ;

        if (parts.length == 2) 
        {
            try 
            {
                int x = Integer.parseInt(parts[0]) ;
                int y = Integer.parseInt(parts[1]) ;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input. Please enter two integers.") ;

                return choseActionToTake(matrix) ; // Retry the input
            }
        } 
        else 
        {
            System.out.println("Invalid input. Please enter two integers.") ;

            return choseActionToTake(matrix) ; // Retry the input
        }

        // Check if the ActionToTake is valid
        while (!matrix.isValidMove(ActionToTake)) 
        {
            System.out.println("Invalid ActionToTake! Try again.") ;
            ActionToTake = choseActionToTake(matrix) ; // Retry the input
        }

        return ActionToTake ;
    } 
}
