package il.co.falk.andromeda.game

val research_multiplier = 1000

class Tech(val name: String) {
    var level = 1;
    var current_research = 0;
    var required_research = (level+1)* research_multiplier


    fun debug() {
        println("$name $level $current_research $required_research")
    }

    fun research(research_points: Int) {
        current_research += research_points
        if(current_research >= required_research) {
            current_research -= required_research
            level++;
            required_research *= 2
        }
    }
}