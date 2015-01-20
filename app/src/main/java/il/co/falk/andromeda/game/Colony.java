package il.co.falk.andromeda.game;

import java.util.ArrayList;

/**
 * Created by roy on 1/2/15.
 */
public class Colony {
    public Player player;
    public Planet planet;
    ArrayList<Unit> units;
    int queue;
    public Unit currentlyBuilding;

    Colony(Player player, Planet planet) {
        this.player = player;
        this.planet = planet;
        units = new ArrayList<Unit>();
        queue = 0;
        currentlyBuilding = new Unit("Trade Goods", 0,0,0,999, planet.location);
        planet.colony = this;
    }

    public void nextTurn() {
        build();
    }

    void build() {
        queue += planet.production;

        if(queue >= currentlyBuilding.cost) {
            queue -= currentlyBuilding.cost;
            units.add(currentlyBuilding);
            player.units.add(currentlyBuilding);
            currentlyBuilding = new Unit(currentlyBuilding);
        }
    }

    public int getRemainingTurns() {
        double t = (currentlyBuilding.cost - queue)/planet.production;
        return (int)Math.ceil(t);
    }
}
