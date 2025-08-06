package il.co.falk.andromeda.game

/**
 * Created by roy on 1/2/15.
 */
open class Player internal constructor(val index: Int, val name: String) {
    var dead = false
    var human = false

    val knownPlanets = mutableListOf<Planet>()

    // TODO: remove this after converting to Kotlin
    @JvmField
    protected val colonies = mutableListOf<Colony>()
    val ships = mutableListOf<Ship>()

    var color = Configuration.playerColors[index]
    var techManager = TechManager()

    //name = NamesFactory.getRaceName()


    fun act() {
        for (colony in colonies) {
            colony.nextTurn()
        }
    }

    fun addColony(colony: Colony) {
        colonies.add(colony)
    }

    fun addShip(ship: Ship) {
        ships.add(ship)
    }

    fun getColonies(): List<Colony> {
        return colonies
    }

    fun getColoniesAsPlanets(): List<Planet> {
        val list = mutableListOf<Planet>()
        for(colony in colonies) list.add(colony.planet)
        return list
    }

    fun canColonize(): Boolean {
        for (ship in ships) if (ship.name == "Colony Ship") return true
        /*for(Colony c : colonies)
            for(Unit u : c.units)
                if(u.getClass().equals(ColonyShip.class))
                    return true;*/
        return false
    }

    fun canAttack(): Boolean {
        for (ship in ships) if (ship.name == "Destroyer") return true
        return false
    }

    fun colonize(planet: Planet) {
        // this really shouldn't happen
        //if(canColonize()==false) return;

        // Add colony

        val colony = Colony(this, planet)
        colonies.add(colony)

        // Select a colony ship
        // TODO: remove closest ship and add travel time
        var colonyShip: Ship? = null
        var l: Location? = null
        for (ship in ships) {
            if (ship.name == "Colony Ship") {
                colonyShip = ship
                l = ship.location
            }
        }


        // Remove ship from colony list
        var colonyWithShip: Colony? = null

        for (c in colonies) if (c != null && c.planet.location === l) colonyWithShip = c

        // Remove ship from global list
        if (colonyShip != null) ships.remove(colonyShip)

        // Remove ship from colony list
        if (colonyShip != null && colonyWithShip != null) colonyWithShip.planet.ships.remove(
            colonyShip
        )
    }


    fun attack(planet: Planet) {
        // this really shouldn't happen
        //if(canAttack()==false) return;

        // Remove colony from other player

        val oldColony = planet.colony
        val oldPlayer = oldColony.player
        oldPlayer.colonies.remove(oldColony)

        // Add colony
        val colony = Colony(this, planet)
        colonies.add(colony)
    }

    companion object {
        var numPlayers: Int = 0
    }
}
