package il.co.falk.andromeda.game;

/**
 * Created by roy on 1/3/15.
 */
public class MissileBase extends Unit {
    public final static int MOVE = 0;
    public final static int MAX_HP = 24;
    public final static int ATTACK = 3;
    public final static int COST = 15;


    public MissileBase(Location l) {
        super(MOVE, MAX_HP, ATTACK, COST, l);
    }
}
