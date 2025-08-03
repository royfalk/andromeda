package il.co.falk.andromeda.game

import java.lang.Math.pow

/** A Producer is any game object that helps produce something.
 *  it can be a factory (products, other producers), tractors (food) and
 *  libraries (research) */
class Producer(val type : Type, var tech: Int, var size: Int, val techModifier : Double) {
    var production : Int = calculateProduction()

    enum class Type {
        factory, food, research
    }

    private fun calculateProduction() : Int {
        return (pow(techModifier, tech.toDouble()) * size).toInt()
    }

    fun produce(): Int {
        return production
    }

    fun upgrade() {
        tech++
        production = calculateProduction()
    }

    fun build(quantity : Int) {
        size += quantity
        production = calculateProduction()
    }

    fun destroy(quantity : Int) {
        Math.max(0, size - quantity)
        production = calculateProduction()
    }
}