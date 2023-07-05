package to.grindelf.astrofooding.utility

import org.apache.commons.math3.optim.MaxIter
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.*
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

object SimplexToolkit {

    /**
     * Solves a linear programming problem using the simplex method.
     */
    fun solve(matrix: List<List<Double>>, limits: List<Double>, minimizers: List<Double>): List<Int> {

        // TODO: DEBUG
        println("Matrix: $matrix")
        println("Limits: $limits")
        println("Minimizers: $minimizers")

        val coefficients: DoubleArray = objectiveFunctionCoefficients(minimizers)
        val constraints: ArrayList<LinearConstraint> = constraints(matrix, limits)
        val function = LinearObjectiveFunction(coefficients, 0.0)


        val solution: PointValuePair = SimplexSolver().optimize(
            function,
            LinearConstraintSet(constraints),
            GoalType.MINIMIZE,
            NonNegativeConstraint(true),
            MaxIter(100)
        )

        // TODO: DEBUG
        print("Solutions: ")
        solution.point.forEach { print("$it ") }
        println()

        return solution.point.map { xi -> roundToUpperInt(xi)}
    }

    private fun roundToUpperInt(xi: Double): Int {

        println("xi: $xi")

        if (xi.absoluteValue - xi.roundToInt().absoluteValue > 0.2) {
            println("rounding up: ${xi.roundToInt() + 1}")
            return xi.roundToInt() + 1
        }
        println("rounding down: ${xi.roundToInt()}")
        return xi.roundToInt()
    }

    private fun objectiveFunctionCoefficients(minimizers: List<Double>): DoubleArray = minimizers.toDoubleArray()

    /**
     * Creates an array list of constraints for the simplex solver
     */
    private fun constraints(matrix: List<List<Double>>, limits: List<Double>): ArrayList<LinearConstraint> {
        // TODO: AI
        val constraints = arrayListOf<LinearConstraint>()

        // Define the constraints
        for (i in matrix[0].indices) {
            val coefficients = DoubleArray(matrix.size) { matrix[it][i] }
            constraints.add(LinearConstraint(coefficients, Relationship.GEQ, limits[i]))
        }

        return constraints
    }
}

