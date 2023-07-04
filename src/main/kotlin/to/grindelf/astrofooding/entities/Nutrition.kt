package to.grindelf.astrofooding.entities

/**
 * Class representing a meal with its macronutrients and weight.
 */
data class Meal(
        val name: String,
        val protein: Double,
        val fat: Double,
        val carbs: Double,
        val weight: Int
)

/**
 * Class encapsulating the results of the optimal diet calculation.
 */
data class Results(
        val astronaut: Astronaut,
        val macronutrients: Macronutrients,
        val mealsByQuantity: List<MealByQuantity>
)

/**
 * Class representing a meal with its quantity.
 */
data class MealByQuantity(
        val meal: Meal,
        val quantity: Int
)

/**
 * Class representing macronutrients.
 */
data class Macronutrients(
        val calories: Double,
        val protein: Double,
        val fat: Double,
        val carbs: Double
)
