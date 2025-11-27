

public class MCB_Problem {

    // Attributes
    int c_num = 3; // Number of Cannibales
    int m_num = 3; // Number of Missionaires
    int b_num = 1; // Number of Boats

    int boat_capacity = 2; // Capacity of the boat

    protected State initialState;
    protected State goalState;

    public MCB_Problem(State initialState, State goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public MCB_Problem() {
        this.initialState = new State(3, 3, true);
        this.goalState = new State(0, 0, false);
    }

    // TODO : Maybe create a custom constructor for custom number of cannibales, missionaires and boats

    public State initialState() {
        return initialState;
    }

    public State getGoalState() {
        return goalState;
    }

    public boolean isGoalState(State state) {
        return state.equals(goalState); // TODO : should we redefine .equals() in State class ?
    }

    public State succession(State state, Action action)
    {
        int c = state.getCLeft();
        int m = state.getMLeft();
        boolean boatLeft = state.isBoatLeft();

        int rc, rm;
        boolean rBoatLeft;

        if (boatLeft) {
            rc = c - action.getDc();
            rm = m - action.getDm();
            rBoatLeft = false;
        } else {
            rc = c + action.getDc();
            rm = m + action.getDm();
            rBoatLeft = true;
        }
        State nextState = new State(rc, rm, rBoatLeft);
        if (nextState.isValidState()) {
            return nextState;
        }
        return null;
    }
}
