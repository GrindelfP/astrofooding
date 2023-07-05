package to.grindelf.astrofooding.service

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import to.grindelf.astrofooding.domain.DietProcessor
import to.grindelf.astrofooding.domain.Astronaut
import to.grindelf.astrofooding.domain.Macronutrients
import to.grindelf.astrofooding.domain.Diet

@RestController
@RequestMapping("/optimal-diet")
class Controllers {

    @CrossOrigin
    @GetMapping
    fun getOptimalDiet(
            @RequestParam name: String,
            @RequestParam age: Int,
            @RequestParam weight: Int,
            @RequestParam height: Int,
            @RequestParam gender: String
    ): Diet {
        val astronaut = Astronaut(name, age, weight, height, gender)
        val dietProcessor = DietProcessor(astronaut)

        return dietProcessor.calculateOptimalDiet()
    }

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/test-components")
    fun testMacronutrients(
            @RequestParam name: String,
            @RequestParam age: Int,
            @RequestParam weight: Int,
            @RequestParam height: Int,
            @RequestParam gender: String
    ): Macronutrients {
        val astronaut = Astronaut(name, age, weight, height, gender)

        return DietProcessor(astronaut).calculateOptimalMacronutrients()
    }
}
