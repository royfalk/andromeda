package il.co.falk.andromeda.game;


/**
 * Created by roy on 1/3/15.
 */
object Game {
    var players : List<Player> = generatePlayers(5)
    var planets : List<Planet> = generatePlanets(players)


    val humanPlayer: Player = players.get(0)
    // TODO: remove this after converting to Kotlin
    @JvmField
    val ships = mutableListOf<Ship>()
    var livingPlayers = players.size
    var turn: Int = 1

    fun nextTurn() {
        turn++

        for (player in players) {
            if(player.dead) {
                if(player.human) {
                    // Do something
                }
                continue
            }

            player.act()
        }
    }

    fun gameEnded(): Boolean {
        return livingPlayers == 1 // || humanPlayer.dead
    }

    /*fun getPlanets(): List<Planet> {
        return planets
    }

    fun getPlayers(): List<Player> {
        return players
    }

    fun getShip(): List<Ship> {
        return ships
    }*/
}
