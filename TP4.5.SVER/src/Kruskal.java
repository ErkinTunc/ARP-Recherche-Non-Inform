import java.util.*;

public class Kruskal {

    static class DSU {

        Map<City, City> parent = new HashMap<>();

        City find(City x) {
            if (parent.get(x) == x) return x;
            parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        void union(City a, City b) {
            parent.put(find(a), find(b));
        }
    }

    public static double mstWeight(List<City> nodes, List<Edge> edges) {
        edges.sort(Comparator.comparingDouble(e -> e.w));

        DSU dsu = new DSU();
        for (City c : nodes) dsu.parent.put(c, c);

        double total = 0;
        int count = 0;

        for (Edge e : edges) {
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
                total += e.w;
                count++;
                if (count == nodes.size() - 1) break;
            }
        }
        return total;
    }
}
