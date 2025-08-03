package il.co.falk.andromeda.game

import org.junit.jupiter.api.Test
class ResourceTest {

    @Test
    fun resourceTest() {
        var resource = Resource<Int>(100,0,100)

        assert(resource.value_==100)
        assert(resource.min_value_==0)
        assert(resource.max_value_==100)
        assert(resource.get()==100)
        assert(resource.getMin()==0)
        assert(resource.getMax()==100)
        assert(resource.percent()==1.0)

        resource.set(50)
        assert(resource.value_==50)
        assert(resource.min_value_==0)
        assert(resource.max_value_==100)
        assert(resource.get()==50)
        assert(resource.percent()==0.5)

        resource.setToMax()
        assert(resource.value_==100)
        assert(resource.min_value_==0)
        assert(resource.max_value_==100)
        assert(resource.get()==100)
        assert(resource.getMin()==0)
        assert(resource.getMax()==100)
        assert(resource.percent()==1.0)
    }

}