package il.co.falk.andromeda.game;

import java.util.List;

/**
 * Created by roy on 2/28/16.
 */
public class Fleet {
    public List<Unit> units;
    public Player player;
    public int move;
    public Location location, destination;

    public Fleet(List<Unit> units, Location location, Player player) {
        this.units = units;
        this.location = this.destination = location;
        this.player = player;
        
    }

    void move(Location destination) {
        this.destination = destination;
    }


}
