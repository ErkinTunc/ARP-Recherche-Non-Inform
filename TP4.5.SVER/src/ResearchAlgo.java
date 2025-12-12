import java.util.*;

public class ResearchAlgo {

    // ===== Q2 : DFS =====
    public static Node dfs(State s, List<City> all, double cost, Node parent) {
        Node n = new Node();
        n.state = s;
        n.parent = parent;
        n.g = cost;

        if (s.isGoal(all.size())) return n;

        for (City c : all) {
            if (!s.visited.contains(c)) {
                State ns = new State();
                ns.current = c;
                ns.visited = new HashSet<>(s.visited);
                ns.visited.add(c);

                Node res = dfs(ns, all, cost + s.current.distanceTo(c), n);
                if (res != null) return res;
            }
        }
        return null;
    }

    // ===== Heuristique MST =====
    public static double heuristicMST(State s, List<City> all) {
        List<City> remaining = new ArrayList<>();
        for (City c : all) if (!s.visited.contains(c)) remaining.add(c);

        if (remaining.size() <= 1) return 0;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < remaining.size(); i++) for (
            int j = i + 1;
            j < remaining.size();
            j++
        ) {
            City a = remaining.get(i);
            City b = remaining.get(j);
            edges.add(new Edge(a, b, a.distanceTo(b)));
        }

        return Kruskal.mstWeight(remaining, edges);
    }

    // ===== Q3 : A* =====
    public static Node aStar(State start, List<City> all) {
        PriorityQueue<Node> open = new PriorityQueue<>(
            Comparator.comparingDouble(Node::f)
        );

        Node startNode = new Node();
        startNode.state = start;
        startNode.g = 0;
        startNode.h = heuristicMST(start, all);
        open.add(startNode);

        while (!open.isEmpty()) {
            Node n = open.poll();
            if (n.state.isGoal(all.size())) return n;

            for (City c : all) {
                if (!n.state.visited.contains(c)) {
                    State ns = new State();
                    ns.current = c;
                    ns.visited = new HashSet<>(n.state.visited);
                    ns.visited.add(c);

                    Node child = new Node();
                    child.state = ns;
                    child.parent = n;
                    child.g = n.g + n.state.current.distanceTo(c);
                    child.h = heuristicMST(ns, all);

                    open.add(child);
                }
            }
        }
        return null;
    }

    // ===== Q4 : Branch & Bound =====
    static double best = Double.POSITIVE_INFINITY;
    static Node bestNode = null;

    public static void branchBound(
        State s,
        List<City> all,
        double cost,
        Node parent
    ) {
        double bound = cost + heuristicMST(s, all);
        if (bound >= best) return;

        Node n = new Node();
        n.state = s;
        n.parent = parent;
        n.g = cost;

        if (s.isGoal(all.size())) {
            best = cost;
            bestNode = n;
            return;
        }

        for (City c : all) {
            if (!s.visited.contains(c)) {
                State ns = new State();
                ns.current = c;
                ns.visited = new HashSet<>(s.visited);
                ns.visited.add(c);

                branchBound(ns, all, cost + s.current.distanceTo(c), n);
            }
        }
    }
}
