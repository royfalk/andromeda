package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class AIPlayer extends Player {

    //public static final double EXPLORE = 0.05;
    public static final double COLONIZE = 0.3;
    public static final double ATTACK = 0.4;
    public static final double DEFENCE = 0.5;

    public static final int STOP_DEFENCE_ON = 3;
    public static final int ATTACK_ON = 3;



    void play() {
        build();
        move();
    }

    void move() {
        for(Colony c : colonies) {

        }
    }

    void build() {
        for(Colony c : colonies) {
            Random r = new Random();
            float f = r.nextFloat();

            if(f<COLONIZE)
                c.currentlyBuilding = "Colony Ship";
            else if(f<COLONIZE+ATTACK)
                c.currentlyBuilding = "Destroyer";
            else
                ProductFactory.getProductFactory().getProduct("Missile Base", c.planet.location, this);
        }
    }


}
