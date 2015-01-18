package il.co.falk.andromeda.game;

/**
 * Created by roy on 1/3/15.
 */
public class ColonyShip extends Unit {
    public final static int MOVE = 3;
    public final static int MAX_HP = 100;
    public final static int ATTACK = 0;
    public final static int COST = 30;


    public ColonyShip(Location l) {
        super(MOVE, MAX_HP, ATTACK, COST, l);
    }
}
