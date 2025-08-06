package il.co.falk.andromeda.game

import android.graphics.Rect

fun generatePlayers(numberOfPlayers: Int): List<Player> {
    val list = mutableListOf<Player>()

    list.add(Player(0, NamesFactory.getRaceName()))

    repeat(numberOfPlayers-1) {
        list.add(AIPlayer(list.size, NamesFactory.getRaceName()))
    }

    return list
}

fun generatePlanets(players: List<Player>): List<Planet> {
    val list = mutableListOf<Planet>()

    for(player in players) {
        val planet = Planet(true)
        val colony = Colony(player, planet)
        player.addColony(colony)
        list.add(planet)
    }

    // Create rest of universe
    repeat(Configuration.numberOfPlanets - Configuration.numberOfPlayers) {
        list.add(Planet(false))
    }

    return list
}

fun getPlanetByName(name: String, planets: List<Planet>): Planet? {
    for (p in planets) if (p.name == name) return p
    return null
}

fun getColonyByName(name: String, colonies: List<Colony>): Colony? {
    for (colony in colonies) {
        if (colony.planet.name == name) return colony
    }
    return null
}

fun getPlanetsInRect(rect: Rect, planets: List<Planet>): List<Planet> {
    val planetsInRect = mutableListOf<Planet>()

    for (planet in planets) {
        if (rect.contains(planet.location.x, planet.location.y)) {
            planetsInRect.add(planet)
        }
    }

    return planetsInRect
}



// TODO:
fun getShipsAtLocation(location: Location): List<Ship> {
    val fleet = mutableListOf<Ship>()
    for (ship in Game.ships) {
        if (ship.location.isInSamePlace(location)) fleet.add(ship)
    }

    return fleet
}

// TODO:
/*fun locationCollision(l: Location): Boolean {
    for (p in planets) {
        if (p.location.isInSamePlace(l)) return true
    }
    return false
}*/







