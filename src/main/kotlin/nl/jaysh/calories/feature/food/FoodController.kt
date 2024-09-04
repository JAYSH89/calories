package nl.jaysh.calories.feature.food

import nl.jaysh.calories.core.model.food.Food
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// Dummy

@RestController
@RequestMapping("/api/v1/food")
class FoodController(private val service: FoodService) {

  @GetMapping fun getAllFood(): List<Food> = service.getAllFood()
}
