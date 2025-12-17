



public class State {

    int cLeft;
    int mLeft;
    boolean boatLeft;

    public State(int cLeft, int mLeft, boolean boatLeft) {
        this.cLeft = cLeft;
        this.mLeft = mLeft;
        this.boatLeft = boatLeft;
    }

    public int getCLeft() {
        return cLeft;
    }

    public int getMLeft() {
        return mLeft;
    }

    public boolean isBoatLeft() {
        return boatLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof State)) {
            return false;
        }
        State s = (State) o;
        return cLeft == s.cLeft && mLeft == s.mLeft && boatLeft == s.boatLeft;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cLeft, mLeft, boatLeft);
    }

    @Override
    public String toString() {
        return "(" + cLeft + ", " + mLeft + ", " + (boatLeft ? "G" : "D") + ")";
    }

    public boolean isValidState() {
        int cRight = 3 - cLeft;
        int mRight = 3 - mLeft;
        if (cLeft < 0 || mLeft < 0 || cRight < 0 || mRight < 0) {
            return false;
        }
        if ((cLeft > 0 && cLeft < mLeft) || (cRight > 0 && cRight < mRight)) {
            return false;
        }
        return true;
    }

    public State isFinaleState() {
        if (cLeft == 0 && mLeft == 0 && !boatLeft) {
            return this;
        }
        return null;
    }
}
