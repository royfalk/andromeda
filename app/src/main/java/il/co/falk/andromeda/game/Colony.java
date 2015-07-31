package il.co.falk.andromeda.game;

import java.util.ArrayList;

/**
 * Created by roy on 1/2/15.
 */
public class Colony {
    public Player player;
    public Planet planet;

    public int queue;
    public Unit currentlyBuilding;

    Colony(Player player, Planet planet) {
        this.player = player;
        this.planet = planet;
        queue = 0;

        currentlyBuilding = UnitFactory.getUnitFactory().getUnit("Missile Base", planet.location);
        //currentlyBuilding = new Unit("Trade Goods", 0,0,0,999, planet.location);
        planet.colony = this;
    }

    public void nextTurn() {
        research();
        build();
    }

    void build() {
        queue += planet.production;

        if(queue >= currentlyBuilding.cost) {
            queue -= currentlyBuilding.cost;
            planet.units.add(currentlyBuilding);
            player.units.add(currentlyBuilding);
            currentlyBuilding = new Unit(currentlyBuilding);
        }
    }

    void research() {
        // TODO: something more meaningful here
        player.techManager.research(10);
    }

    public int getRemainingTurns() {
        double t = (currentlyBuilding.cost - queue)/planet.production;
        return (int)Math.ceil(t);
    }
}
