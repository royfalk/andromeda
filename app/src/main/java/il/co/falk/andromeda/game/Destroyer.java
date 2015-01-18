package il.co.falk.andromeda.game;

/**
 * Created by roy on 1/3/15.
 */
public class Destroyer extends Unit {
    public final static int MOVE = 6;
    public final static int MAX_HP = 20;
    public final static int ATTACK = 4;
    public final static int COST = 15;


    public Destroyer(Location l) {
        super(MOVE, MAX_HP, ATTACK, COST, l);
    }
}
