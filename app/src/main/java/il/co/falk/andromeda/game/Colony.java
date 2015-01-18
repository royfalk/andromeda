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
        currentlyBuilding = new MissileBase(planet.location);
        planet.colony = this;
    }

    public void nextTurn() {
        build();
    }

    void build() {
        queue += planet.production;

        if(queue >= currentlyBuilding.cost) {
            ProductFactory factory = ProductFactory.getFactory();
            queue -= currentlyBuilding.cost;
            Unit u = factory.getUnit(currentlyBuilding.getClass().getSimpleName());
            units.add(u);
            player.units.add(u);
            u.location = planet.location;
        }
    }

    public int getRemainingTurns() {
        double t = (currentlyBuilding.cost - queue)/planet.production;
        return (int)Math.ceil(t);
    }
}
