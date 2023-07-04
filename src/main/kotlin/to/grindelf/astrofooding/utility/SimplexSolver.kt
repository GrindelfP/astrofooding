package to.grindelf.astrofooding.utility

import org.apache.commons.math3.optim.MaxIter
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.*
import org.apache.commons.math3.optim.linear.SimplexSolver
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType

object SimplexSolver {

    private val slackVariableMatrix = listOf(
        listOf(-1.0, 0.0, 0.0),
        listOf(0.0, -1.0, 0.0),
        listOf(0.0, 0.0, -1.0)
    )

    /**
     * Solves a linear programming problem using the simplex method.
     */
    fun solve(matrix: List<List<Double>>, limits: List<Double>, minimizers: List<Double>): List<Int>? {
        val updatedMatrix = mutableListOf<List<Double>>()

        for (i in matrix.indices) {
            updatedMatrix.add(matrix[i] + slackVariableMatrix[i])
        }
        println(updatedMatrix)

        // Create list for constraint equations
        val matrixList = arrayListOf<LinearConstraint>()

        // Transform each equation into a LinearConstraint
        for (i in updatedMatrix.indices) {
            val equation = ArrayList<Double>(updatedMatrix[i])
            val constraint = LinearConstraint(equation.toDoubleArray(), Relationship.LEQ, limits[i])
            matrixList.add(constraint)
        }

        // Define the objective function
        val objectiveFunction = LinearObjectiveFunction(minimizers.toDoubleArray(), 0.0)

        // Create the solver
        val solver = SimplexSolver()

        // Generate solution
        val solution: PointValuePair?
        try {
            solution = solver.optimize(
                MaxIter(100),  // Maximum iterations
                objectiveFunction,  // Function to optimize
                LinearConstraintSet(matrixList),  // Problem constraints
                GoalType.MINIMIZE,  // Goal setting - minimizing
                PivotSelectionRule.DANTZIG  // Pivot rule selection
            )
        } catch (ex: Exception) {
            return null
        }

        return solution.point.map { it.toInt() }.toList()
    }

}
