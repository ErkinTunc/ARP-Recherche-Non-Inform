public class Node {

    State state;
    Node parent;
    double g;
    double h;

    public double f() {
        return g + h;
    }
}
