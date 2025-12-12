import java.util.*;

public class State {

    City current;
    Set<City> visited;

    public boolean isGoal(int n) {
        return visited.size() == n;
    }
}
