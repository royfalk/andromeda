package il.co.falk.andromeda.game

import org.junit.jupiter.api.Test

class TechTest {
    @Test
    fun researchTest() {
        val tech = Tech("Some Tech")
        tech.research(500)
        assert(tech.current_research == 500)
        assert(tech.required_research == 2000)
        assert(tech.level == 1)
        tech.debug()

        tech.research(2500)
        assert(tech.current_research == 1000)
        assert(tech.required_research == 4000)
        assert(tech.level == 2)
        tech.debug()

        tech.research(2500)
        assert(tech.current_research == 3500)
        assert(tech.required_research == 4000)
        assert(tech.level == 2)
        tech.debug()

        tech.research(500)
        assert(tech.current_research == 0)
        assert(tech.required_research == 8000)
        assert(tech.level == 3)
        tech.debug()
    }

}