package il.co.falk.andromeda.game;

/**
 * Created by roy on 1/2/15.
 */
public class Colony {
    public Player player;
    public Planet planet;

    public int queue;
    public String currentlyBuilding;
    public int currentCostToBuild;

    Colony(Player player, Planet planet) {
        this.player = player;
        this.planet = planet;
        queue = 0;

        currentlyBuilding = "Missile Base";
        currentCostToBuild = ProductFactory.getProductFactory().getProductPrice(currentlyBuilding);
        //currentlyBuilding = new Unit("Trade Goods", 0,0,0,999, planet.location);
        planet.colony = this;
    }

    public void nextTurn() {
        research();
        build();
    }

    void build() {
        queue += planet.production + player.techManager.manufacturing.getLevel();

        if(queue >= currentCostToBuild) {
            queue -= currentCostToBuild;
            Unit unit = ProductFactory.getProductFactory().getProduct(currentlyBuilding, planet.location, player);
            planet.units.add(unit);
            player.units.add(unit);

            // Apply Technology Bonuses
            unit.hp += player.techManager.armor.getLevel();
            if(unit.name.equals("Missile Base")) unit.attack += player.techManager.missile.getLevel();
            if(unit.name.equals("Destroyer")) unit.attack += player.techManager.beam.getLevel();
        }
    }

    void research() {
        // TODO: something more meaningful here
        player.techManager.research(10+ player.techManager.research.getLevel());
    }

    public void changeProductToBuild(String newItemToBuild) {
        if(newItemToBuild.equals(currentlyBuilding)) return;
        currentlyBuilding = newItemToBuild;
        currentCostToBuild = ProductFactory.getProductFactory().getProductPrice(currentlyBuilding);
        queue /= 2;
    }

    public int getRemainingTurns() {
        double t = (currentCostToBuild - queue)/planet.production;
        return (int)Math.ceil(t);
    }
}
