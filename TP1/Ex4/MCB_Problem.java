package Ex4;

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

    public Action[] getActions(State state) {
        // Want to create a very generic method to get possible actions from a given state

        Action[] possibleActions = new Action[Action.values().length];

        if (state.isBoatLeft()) {} else {
            // Boat is on the right side
        }

        for (int i = 0; i < Action.values().length; i++) {}
    }

    public State succession(State state, Action action) {}
}
