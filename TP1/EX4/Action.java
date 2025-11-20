package AlgoRes;

public enum Action {
    C1(1, 0), 
    C2(2, 0), 
    M1(0, 1), 
    M2(0, 2), 
    CM(1, 1); 


    public final int dc;
    public final int dm;


    Action(int dc, int dm) {
    this.dc = dc;
    this.dm = dm;
    }

    public int getDc() {
        return dc;
    }
    public int getDm() {
        return dm;
    }
}
