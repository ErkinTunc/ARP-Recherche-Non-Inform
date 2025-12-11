package src;
/**
 * 
 * @author ELNASORY Karam
 */

public enum Action
{
    DIVIED_BY_2(2, 0),
    SUBTRACT_1(0, 1);

    private int divide;
    private int subtract;

    Action (int divide, int subtract)
    {
        this.divide = divide;
        this.subtract = subtract;
    }

    public int getDivide() {
        return divide;
    }

    public int getSubtract() {
        return subtract;
    }

}

