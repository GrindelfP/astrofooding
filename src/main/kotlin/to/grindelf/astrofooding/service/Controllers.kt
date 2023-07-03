package to.grindelf.astrofooding.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import to.grindelf.astrofooding.dietlogics.DietProcessor.calculateOptimalMacronutrients
import to.grindelf.astrofooding.dietlogics.DietProcessor.calculateOptimalDiet
import to.grindelf.astrofooding.entities.Astronaut
import to.grindelf.astrofooding.entities.Macronutrients
import to.grindelf.astrofooding.entities.Results

@RestController
@RequestMapping("/optimal-diet")
class Controllers {

    @GetMapping
    fun getOptimalDiet(
            @RequestParam name: String,
            @RequestParam age: Int,
            @RequestParam weight: Int,
            @RequestParam height: Int,
            @RequestParam gender: String
    ): Results {

        val astronaut = Astronaut(name, age, weight, height, gender)
        val optimalMacronutrients = calculateOptimalMacronutrients(astronaut)

        return calculateOptimalDiet(optimalMacronutrients)
    }

    @GetMapping("/test-astronaut")
    fun testAstronaut(
            @RequestParam name: String,
            @RequestParam age: Int,
            @RequestParam weight: Int,
            @RequestParam height: Int,
            @RequestParam gender: String
    ): Astronaut {
        return Astronaut(name, age, weight, height, gender)
    }

    @GetMapping("/test-components")
    fun testMacronutrients(
            @RequestParam name: String,
            @RequestParam age: Int,
            @RequestParam weight: Int,
            @RequestParam height: Int,
            @RequestParam gender: String
    ): Macronutrients {
        val astronaut = Astronaut(name, age, weight, height, gender)

        return calculateOptimalMacronutrients(astronaut)
    }
}
