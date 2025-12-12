/**
 * Coordonates class representing a point in a 2D space.
 * It contains the x and y coordinates.
 * 
 * class Coordonates {
    -x: double
    -y: double
    +x(): double
    +y(): double
    +distanceTo(other: Coordonates): double
  }
 */
package model;

/**
 * Coordonates class representing a point in a 2D space.
 * It contains the x and y coordinates.
 * 
 * @author ELNASORY Karam
 */

public class Coordonates {

    private final double x;
    private final double y;

    public Coordonates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double distanceTo(Coordonates other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "Coordonates{ " + x + " , " + y + " }";
    }

}
