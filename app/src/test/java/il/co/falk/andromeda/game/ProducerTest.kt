package il.co.falk.andromeda.game

import org.junit.jupiter.api.Test

class ProducerTest {
    @Test
    fun sanity() {
        val producer = Producer(Producer.Type.research, 0, 10,
            0, 2.0, 0.5)
        var production = producer.produce()
        assert(production == 5)

        producer.build(4)
        production = producer.produce()
        assert(production == 7)

        producer.build(4)
        production = producer.produce()
        assert(production == 9)

        producer.destroy(2)
        production = producer.produce()
        assert(production == 8)

        // Upgrade
        producer.destroy(30)
        producer.upgrade()

        production = producer.produce()
        assert(production == 10)

        producer.build(4)
        production = producer.produce()
        assert(production == 14)

        producer.build(4)
        production = producer.produce()
        assert(production == 18)

        producer.destroy(2)
        production = producer.produce()
        assert(production == 16)

    }
}