package il.co.falk.andromeda.game

import java.lang.Math.pow

/** A Producer is any game object that helps produce something.
 *  it can be a factory (products, other producers), tractors (food) and
 *  libraries (research) */
class Producer(val type : Type, var tech: Int, var population: Int,
               var initial_producers: Int, val techModifier : Double, val unskilledModifier: Double) {
    var producers : Resource<Int> = Resource<Int>(initial_producers, 0, -1)
    var production : Int = calculateProduction()

    enum class Type {
        factory, food, research
    }

    private fun calculateProduction() : Int {
        val techEffect = pow(techModifier, tech.toDouble())
        val skilledLabor = Math.min(producers.get(), population)
        val unskilledLabor = population - skilledLabor
        val skilledProduction = techEffect * skilledLabor
        val unskilledProduction = techEffect * unskilledLabor * unskilledModifier
        return (skilledProduction + unskilledProduction).toInt()
    }

    fun produce(): Int {
        return production
    }

    fun upgrade() {
        tech++
        production = calculateProduction()
    }

    fun build(quantity : Int) {
        producers += quantity
        production = calculateProduction()
    }

    fun destroy(quantity : Int) {
        producers -= quantity
        production = calculateProduction()
    }
}