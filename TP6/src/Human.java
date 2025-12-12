
/**
 * Humain class representing a human player in the game.
 * It extends the User class.
 * @author ELNASORY Karam
 */

public class Human extends User
{
    /**
     * @description Constructor for Humain class, meant to be used for new players.
     * @param name name of the player
     * @param color color of the player
     * @deprecated
     */
    public Human (String name) 
    {
        super(name) ;
    }

    /**
     * @description Constructor for Humain class, for new players, interact's with the user.
     */
    public Human ()
    {
        super (promptForName()) ;
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

    /**
     * @description Method to choose a ActionToTake on the board to play a move (place a token).
     * @return Action object representing the chosen ActionToTake.
     */
    @Override
    public Action choseAction (State currentState) 
    {
        System.out.print("Enter your move (x y): ") ;
        String input = System.console().readLine() ;
        
        String[] parts = input.split("\\s+") ;

        Action ActionToTake = null ;

        if (parts.length > 0) 
        {
            try 
            {
                // Take the action number 1 or 2 and create the corresponding action 
                
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input. Please enter two integers.") ;

                return choseAction(currentState) ; // Retry the input
            }
        } 
        else 
        {
            System.out.println("Invalid input. Please enter two integers.") ;

            return choseAction(currentState) ; // Retry the input
        }

        return ActionToTake ;
    } 
}
