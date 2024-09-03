package nl.jaysh.calories.controller.api

import nl.jaysh.calories.model.api.Food
import nl.jaysh.calories.service.api.FoodService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// Dummy

@RestController
@RequestMapping("/api/v1/food")
class FoodController(private val service: FoodService) {

    @GetMapping
    fun getAllFood(): List<Food> = service.getAllFood()
}
