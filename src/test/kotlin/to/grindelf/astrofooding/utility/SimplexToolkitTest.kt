package to.grindelf.astrofooding.utility

import org.junit.jupiter.api.Test
import org.apache.commons.math3.optimization.GoalType
import org.apache.commons.math3.optimization.PointValuePair
import org.apache.commons.math3.optimization.linear.LinearConstraint
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction
import org.apache.commons.math3.optimization.linear.LinearOptimizer
import org.apache.commons.math3.optimization.linear.Relationship
import org.apache.commons.math3.optimization.linear.SimplexSolver
import org.junit.jupiter.api.Assertions.*

class SimplexToolkitTest {

    @Test
    fun `GIVEN diet problem data WHEN solve() applied THAN expected result returned`() {
        val matrix = listOf(
            listOf(20.0, 10.0, 70.0),
            listOf(30.0, 15.0, 80.0),
            listOf(25.0, 20.0, 85.0),
            listOf(35.0, 25.0, 90.0)
        ) // proteins, fats, carbs respectively for each meal
        val limits = listOf(179.0, 5.0, 584.0)  // minimum required amounts of proteins, fats, carbs
        val minimizers = listOf(200.0, 300.0, 250.0, 350.0)  // weights of the meals

        SimplexToolkit.solve(matrix, limits, minimizers)?.let {
            assertEquals(listOf(3, 1, 3, 0), it)
        }
    }
}