package to.grindelf.astrofooding.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DietProcessorTest {

    @Test
    fun testBadListOfMeals() {
        val astronaut = Astronaut(
            name = "Grisha",
            weight = 68,
            height = 187,
            age = 20,
            gender = "male"
        )

        val dietProcessor = DietProcessor(astronaut)

        println(dietProcessor.calculateOptimalDiet())
    }
}