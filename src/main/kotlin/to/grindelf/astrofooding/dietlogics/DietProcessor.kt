package to.grindelf.astrofooding.dietlogics

import to.grindelf.astrofooding.entities.*
import to.grindelf.astrofooding.utility.SimplexSolver
import java.io.File

/**
 * This class contains all the logic for calculating optimal macronutrients and diet
 */
class DietProcessor(
    private val astronaut: Astronaut
) {

    private val macronutrientsToFit: Macronutrients

    companion object {

        /**
         * Activity factor for calculating calories (it is constant
         * because we assume that astronauts are very active people;
         * they have to perform a lot of physical exercises a day
         * to stay in shape and to avoid muscle atrophy because of
         * microgravity)
         */
        private const val ACTIVITY_FACTOR = 1.725

        /**
         * Menu file
         */
        private val MENU_FILE = File("src/main/resources/menus/menu1.json")
    }

    init {
        macronutrientsToFit = calculateOptimalMacronutrients()
    }

    /**
     * Calculates optimal macronutrients for an astronaut
     * @return Macronutrients
     */
    fun calculateOptimalMacronutrients(): Macronutrients {

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

        return Macronutrients(calories, protein, fat, carbs) // TODO: update the formulae
    }

    /**
     * Calculates optimal diet for an astronaut
     * @return Results
     */
    fun calculateOptimalDiet(): Results {
        val limits = listOf(
            macronutrientsToFit.protein,
            macronutrientsToFit.fat,
            macronutrientsToFit.carbs
        ) // declare limits for each macronutrient

        val matrix = mutableListOf<List<Double>>()

        val menu = getMenuFrom()
        menu.forEach { meal ->
            matrix.add(
                listOf(
                    meal.protein,
                    meal.fat,
                    meal.carbs
                )
            )
        } // declare matrix of macronutrients for each meal of the menu

        val simplexSolutions = SimplexSolver.solve(matrix, limits) // solve the problem

        val mealsByQuantity = mutableListOf<MealByQuantity>()

        simplexSolutions.forEachIndexed { index, quantity ->
            mealsByQuantity.add(
                MealByQuantity(
                    meal = menu[index], quantity = quantity
                )
            )
        } // create a list of meals with their quantities

        return Results(
            astronaut = astronaut,
            macronutrients = macronutrientsToFit,
            mealsByQuantity = mealsByQuantity
        )
    }

    /**
     * Returns a menu of meals read from JSON file
     * @return List<Meal>
     */
    private fun getMenuFrom(): List<Meal> {
        TODO("Not yet implemented")
    }
}
