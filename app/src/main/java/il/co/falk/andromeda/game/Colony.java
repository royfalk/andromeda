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
        queue += planet.production + player.getTechManager().manufacturing.getLevel();

        if(queue >= currentCostToBuild) {
            queue -= currentCostToBuild;
            Ship ship = ProductFactory.getProductFactory().getProduct(currentlyBuilding, planet.location, player);
            planet.ships.add(ship);
            player.addShip(ship);

            // Apply Technology Bonuses
            ship.hp += player.getTechManager().armor.getLevel();
            if(ship.name.equals("Missile Base")) ship.attack += player.getTechManager().missile.getLevel();
            if(ship.name.equals("Destroyer")) ship.attack += player.getTechManager().beam.getLevel();
        }
    }

    void research() {
        // TODO: something more meaningful here
        player.getTechManager().research(10+ player.getTechManager().research.getLevel());
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
