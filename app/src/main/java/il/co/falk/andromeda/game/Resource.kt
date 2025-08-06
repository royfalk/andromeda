package il.co.falk.andromeda.game

/**
 * @brief A resource is any part of the game that can be used up and filled up.
 * The purpose of this class is to simplify code throughout the game by placing it here.
 * Instead of adding to health and then checking it isn't above max health, it's
 * done automatically.
 */
class Resource<T :  Number>(
    var value_: T = 0 as T,
    val min_value_: T = 0 as T,
    val max_value_: T = -1 as T,
    val no_max_:Boolean = (max_value_ == -1)
) {

    operator fun plusAssign(other: T) {
        val new_value: T = (value_.toDouble() + other.toDouble()) as T
        set(new_value)
    }

    operator fun minusAssign(other: T) {
        val new_value: T = (value_.toDouble() - other.toDouble()) as T
        set(new_value)
    }

    fun percent(): Double {
        if(no_max_) {   // Can't calculate percent if there's no max
            return 0.0
        }

        if(max_value_ == 0) {   // Can't calculate percent if divider is 0
            return 0.0
        }

        return (value_.toDouble()) / (max_value_.toDouble());
    }

    fun get(): T {
        return value_ as T
    }

    fun getMin(): T {
        return min_value_ as T
    }

    fun getMax(): T {
        return max_value_
    }

    fun set(value:T) {
        value_ = value;
        if(!no_max_) {
            value_ = if(max_value_.toDouble() <= value_.toDouble()) max_value_ else value_
        }
        value_ = if(min_value_.toDouble() >= value_.toDouble()) (min_value_) else (value_)
    }

    fun setToMax() {
        if(no_max_) {   // Can't set to max if there's no max
            return
        }

        value_ = max_value_;
    }
}