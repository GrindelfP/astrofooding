package to.grindelf.astrofooding.entities

data class Meal(
        val name: String,
        val calories: Double,
        val protein: Double,
        val fat: Double,
        val carbs: Double,
        val weight: Int
)

data class Results(
        val astronaut: Astronaut,
        val macronutrients: Macronutrients,
        val mealsByQuantity: MealByQuantity
)

data class MealByQuantity(
        val meal: Meal,
        val quantity: Int
)

data class Macronutrients(
        val calories: Double,
        val protein: Double,
        val fat: Double,
        val carbs: Double
)
