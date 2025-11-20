package AlgoRes;
import java.util.LinkedList;
import java.util.List;


public class Node {
    private final State s;
    private final Node p;
    private final Action a;

    public Node(State state, Node node, Action action){
        this.s = state;
        this.p = node;
        this.a = action;
    }

    public State getState() {
        return this.s;
    }
    
    public Node getParent() {
        return this.p;
    }

    public Action getParentAction() {
        return this.a;
    }

    public Node succNode(Action a){
        int c = this.s.getCLeft();
        int m = this.s.getMLeft();
        boolean boatLeft = this.s.isBoatLeft();

        int rc, rm;
        boolean rBoatLeft;
        
        if (boatLeft){
            rc = c - a.getDc();
            rm = m - a.getDm();
            rBoatLeft = false;
        } else {
            rc = c + a.getDc();
            rm = m + a.getDm();
            rBoatLeft = true;
        }
        State nextState = new State(rc, rm, rBoatLeft);
        if (nextState.isValidState()) {
            return new Node(nextState, this, a);
        }
        return null;
    }

    public List<Node> expand(){
        List<Node> children = new LinkedList<>();
        for (Action action : Action.values()) {
            Node child = this.succNode(action);
            if (child != null) {
                children.add(child);
            }
        }
        return children;
    }


}
