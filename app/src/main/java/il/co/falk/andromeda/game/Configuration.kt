package il.co.falk.andromeda.game

import android.graphics.Color

object Configuration {
    const val numberOfPlanets: Int = 15
    const val numberOfPlayers: Int = 4
    const val universeHeight: Int = 100
    const val universeWidth: Int = 100

    var unskilledFoodProductionModifier = 0.3

    val playerColors: IntArray =
        intArrayOf(Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN)
}
