package to.grindelf.astrofooding.utility

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SimplexSolverTest {

    private val matrix = listOf(
        listOf(3.0, 2.0, 1.0, 1.0, -1.0, 0.0, 0.0),
        listOf(2.0, 1.0, 2.0, 3.0, 0.0, -1.0, 0.0),
        listOf(1.0, 2.0, 1.0, 1.0, 0.0, 0.0, -1.0),
    )

    private val limits = listOf(30.0, 20.0, 10.0)


    @Test
    fun solve() {
        val result = SimplexSolver.solve(
            matrix,
            limits,
            listOf(2.0, 3.0, 1.0, 4.0, 0.0, 0.0, 0.0)
        )

        println(result)

    }
}