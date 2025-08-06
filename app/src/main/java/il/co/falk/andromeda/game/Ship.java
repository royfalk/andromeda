package il.co.falk.andromeda.game;


/**
 * Created by roy on 1/2/15.
 */
public class Ship extends Product {
    public int move;
    public Location destination;


    public Ship(String name, int move, int maxHP, int attack, int cost, Location location, Player player) {
        super(name, maxHP, attack, cost, location, player);

        this.move = move;
    }

    public Ship(Ship u) {
        super(u);
        move = u.move;
    }

    void move(Location destination) {
        this.destination = destination;
    }

    void move() {

    }
}
