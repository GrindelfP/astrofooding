package to.grindelf.astrofooding.dietlogics

import to.grindelf.astrofooding.entities.Astronaut
import to.grindelf.astrofooding.entities.Macronutrients
import to.grindelf.astrofooding.entities.Results

object DietProcessor {

    private const val ACTIVITY_FACTOR = 1.725

    fun calculateOptimalMacronutrients(astronaut: Astronaut): Macronutrients {

        val calories = when (astronaut.gender) {
            "male" -> (66.5 + 13.75 * astronaut.weight + 5.003 * astronaut.height -
                    6.755 * astronaut.age) * ACTIVITY_FACTOR

            "female" -> (655.1 + 9.6 * astronaut.weight + 1.9 * astronaut.height -
                    4.7 * astronaut.age) * ACTIVITY_FACTOR

            else -> throw IllegalArgumentException("This gender is not supported yet")
        }

        val protein = astronaut.weight * 2.2 * 1.2
        val fat = calories * 0.3 / 9
        val carbs = (calories - protein * 4 - fat * 9) / 4

        return Macronutrients(calories, protein, fat, carbs)
    }

    fun calculateOptimalDiet(macronutrientsToFit: Macronutrients): Results {
        TODO("Not yet implemented")
    }

}