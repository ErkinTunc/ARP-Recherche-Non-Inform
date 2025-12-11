package Ex2_Tour_Cavalier_DFS;

import java.util.ArrayList;
import java.util.List;

public class KnightsTourProblem
{
    private final int NB_ROWS;
    private final int NB_COLS;

    public KnightsTourProblem(int n, int m)
    {
        if (n <= 0 || m <= 0)
        {
            throw new IllegalArgumentException("Number of rows and columns must be positive.");
        }

        this.NB_ROWS = n;
        this.NB_COLS = m;
    }
    
    /** Retourne l'état initial : une grille vide avec le cavalier sur un coin */
    public State initialState()
    {
        // Initialize knight position at (0,0) and mark starting cell as visited
        Position position = new Position();
        int[][] grid = new int[NB_ROWS][NB_COLS];
        grid[position.x][position.y] = 1; // mark start visited

        State initialState = new State(grid, position, 1);
        return initialState;
    }

    /** Retourne la liste des actions */
    public List<Action> actions()
    {
        /* Actions

        *HG = (+2, +1)
        HD = (+2, -1)
        DH = (+1, +2)
        DB = (-1, +2)
        BD = (-2, +1)
        BG = (-2, -1)
        GH = (+1, -2)
        GB = (-1, -2)
        */

        List<Action> actions = new ArrayList<>();

        int[][] moves =
        {
            {2, 1}, {2, -1}, {1, 2}, {-1, 2},
            {-2, 1}, {-2, -1}, {1, -2}, {-1, -2}
        };

        for (int[] move : moves)
        {
            int i = move[0];
            int j = move[1];
            Action action = new Action(i, j);
            actions.add(action);
        }

        return actions;
    }

    /** Vérifie si l'état est terminal */
    public boolean isGoalState(State state)
    {
        int totalCells = NB_ROWS * NB_COLS;
        // On vérifie si on a visité toutes les cases (en prenant )

        return state.getScore() == totalCells ; // Pour un tour ouvert

        // return state.getScore() == totalCells && state.getKnight().equals(new Position()); // Pour un tour fermé
    }

    /** Retourne l'état successeur après avoir appliqué une action */
    public State succession(State state, Action action)
    {
        Position currentPosition = state.getKnight();
        
        int newX = currentPosition.x + action.dx;
        int newY = currentPosition.y + action.dy;

        // Vérifier si la nouvelle position est valide
        if (newX < 0 || newX >= NB_ROWS || newY < 0 || newY >= NB_COLS)
        {
            return null; // Mouvement invalide
        }

        // Créer une nouvelle position pour le cavalier
        Position newPosition = currentPosition.move(action.dx, action.dy);
        
        // Créer une nouvelle grille pour l'état successeur
        int[][] newGrid = new int[NB_ROWS][NB_COLS];
        
        for (int i = 0; i < NB_ROWS; i++)
        {
            System.arraycopy(state.getBoard()[i], 0, newGrid[i], 0, NB_COLS);
        }

        // Marquer la nouvelle position comme visitée (si elle ne l'était pas)
        int newScore = state.getScore();
        if (state.getBoard()[newX][newY] == 0)
        {
            newGrid[newX][newY] = 1; // Marquer comme visité
            newScore += 1; // Incrémenter le score
        }

        // Retourner le nouvel état
        return new State(newGrid, newPosition, newScore);
    }


    class Action
    {
        public final int dx;
        public final int dy;
        
        public Action(int dx, int dy)
        {
            this.dx = dx;
            this.dy = dy;
        }
        
        @Override
        public String toString()
        {
            return "(" + dx + "," + dy + ")";
        }
        
        /** Coût de l'action (toujours 1 dans ce problème) */
        public int cout()
        {
            return 1;
        }
    }
}
