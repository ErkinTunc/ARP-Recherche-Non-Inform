/**
 * Class Exercise act as an entry point for the programme
<<<<<<< HEAD:TP1 & TP2/Exo3 Code Cavalier/Exercise.java
 * 
 * Compilation: javac -d ./bin/ ./*.java
 * Execution: java -cp ./bin/ Exercise
 */

public class Exercise
{
    public static void main(String[] args)
    {
        System.out.println("Hello, this program should solve the Knights Tour!");
        
=======
 */

public class Exercise {

    public static void main(String[] args) {
        System.out.println(
            "Hello, this program should solve the Knights Tour!"
        );

>>>>>>> a52afd60381d23c9e0100ae1b82ef36ec0a1c69e:TP1/Exo3 Code Cavalier/Exercise.java
        int NB_ROWS = 5;
        int NB_COLS = 5;

        KnightsTourProblem problem = new KnightsTourProblem(NB_ROWS, NB_COLS);
        Node solutionNode = ResearchAlgorithm.search(problem);

<<<<<<< HEAD:TP1 & TP2/Exo3 Code Cavalier/Exercise.java
        if (solutionNode != null)
        {
            System.out.println("Search has found a solution:");
            solutionNode.printSolution();
        }
        else
        {
=======
        if (solutionNode != null) {
            System.out.println("Search has found a solution:");
            solutionNode.printSolution();
        } else {
>>>>>>> a52afd60381d23c9e0100ae1b82ef36ec0a1c69e:TP1/Exo3 Code Cavalier/Exercise.java
            System.out.println("Could not find a solution.");
        }
    }
}
