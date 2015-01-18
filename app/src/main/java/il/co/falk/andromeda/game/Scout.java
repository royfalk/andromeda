package il.co.falk.andromeda.game;


/**
 * Created by roy on 1/2/15.
 */
public class Scout extends Unit {
    public final static int MOVE = 10;
    public final static int MAX_HP = 1;
    public final static int ATTACK = 0;
    public final static int COST = 5;


    Scout(Location l) {
        super(MOVE, MAX_HP, ATTACK, COST, l);
    }


}
