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
            @RequestParam age: Double,
            @RequestParam weight: Double,
            @RequestParam height: Double,
            @RequestParam gender: String
    ): Diet {

        println("name: $name, ${name::class.java.simpleName}")
        println("age: $age, ${age::class.java.simpleName}")
        println("weight: $weight, ${weight::class.java.simpleName}")
        println("height: $height, ${height::class.java.simpleName}")
        println("gender: $gender, ${gender::class.java.simpleName}")

        val astronaut = Astronaut(name, age, weight, height, gender)
        val dietProcessor = DietProcessor(astronaut)

        return dietProcessor.calculateOptimalDiet()
    }

    @CrossOrigin
    @GetMapping("/test-astronaut")
    fun testAstronaut(
            @RequestParam name: String,
            @RequestParam age: Double,
            @RequestParam weight: Double,
            @RequestParam height: Double,
            @RequestParam gender: String
    ): Astronaut {
        return Astronaut(name, age, weight, height, gender)
    }

    @CrossOrigin
    @GetMapping("/test-components")
    fun testMacronutrients(
            @RequestParam name: String,
            @RequestParam age: Double,
            @RequestParam weight: Double,
            @RequestParam height: Double,
            @RequestParam gender: String
    ): Macronutrients {
        val astronaut = Astronaut(name, age, weight, height, gender)

        return DietProcessor(astronaut).calculateOptimalMacronutrients()
    }
}
