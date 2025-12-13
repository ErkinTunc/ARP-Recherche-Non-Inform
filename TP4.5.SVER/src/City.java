public class City {

    public int id;
    public double x, y;

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double distanceTo(City other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof City)) return false;
        return id == ((City) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
