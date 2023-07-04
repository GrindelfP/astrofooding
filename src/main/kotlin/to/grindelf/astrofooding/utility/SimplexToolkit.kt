package to.grindelf.astrofooding.utility

import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException
import org.apache.commons.math3.optimization.GoalType.MAXIMIZE
import org.apache.commons.math3.optimization.PointValuePair
import org.apache.commons.math3.optimization.linear.LinearConstraint
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction
import org.apache.commons.math3.optimization.linear.Relationship
import org.apache.commons.math3.optimization.linear.SimplexSolver

object SimplexToolkit {

    private const val mealLimit = 3.0 // Maximum allowed quantity for each meal

    /**
     * Solves a linear programming problem using the simplex method.
     */
    fun solve(matrix: List<List<Double>>, limits: List<Double>, minimizers: List<Double>): List<Int>? {

        val function = LinearObjectiveFunction(DoubleArray(minimizers.size) { i -> -minimizers[i] }, 0.0)
        val constraints = arrayListOf<LinearConstraint>()

        // Define the constraints
        for (i in matrix[0].indices) {
            val coefficients = DoubleArray(matrix.size) { matrix[it][i] }
            constraints.add(LinearConstraint(coefficients, Relationship.GEQ, limits[i]))
        }

        // Add new constraints for meal limits
        for (i in minimizers.indices) {
            val coefficients = DoubleArray(minimizers.size) { if (it == i) 1.0 else 0.0 }
            constraints.add(LinearConstraint(coefficients, Relationship.LEQ, mealLimit))
        }

        // Create a solver and solve the problem
        val solver = SimplexSolver()
        val solution: PointValuePair
        return try {
            solution = solver.optimize(function, constraints, MAXIMIZE, true)
            solution.point.map { it.toInt() }
        } catch (exception: NoFeasibleSolutionException) {
            null
        }
    }
}
