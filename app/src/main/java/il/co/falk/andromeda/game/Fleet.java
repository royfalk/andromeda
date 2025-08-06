package il.co.falk.andromeda.game;

import java.util.List;

/**
 * Created by roy on 2/28/16.
 */
public class Fleet {
    public List<Ship> ships;
    public Player player;
    public int move;
    public Location location, destination;

    public Fleet(List<Ship> ships, Location location, Player player) {
        this.ships = ships;
        this.location = this.destination = location;
        this.player = player;
        
    }

    void move(Location destination) {
        this.destination = destination;
    }


}
