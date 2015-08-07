package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Unit extends Product {
    public int move;
    public Location destination;


    public Unit(String name, int move, int maxHP, int attack, int cost, Location location, Player player) {
        super(name, maxHP, attack, cost, location, player);

        this.move = move;
    }

    public Unit(Unit u) {
        super(u);
        move = u.move;
    }

    void move(Location destination) {
        this.destination = destination;
    }

    void move() {

    }
}
