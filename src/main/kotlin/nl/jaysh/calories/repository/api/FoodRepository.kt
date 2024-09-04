package nl.jaysh.calories.repository.api

import java.time.LocalDateTime
import java.util.*
import nl.jaysh.calories.model.api.Food
import org.springframework.stereotype.Repository

// Dummy

@Repository
class FoodRepository {
  private val foods =
    listOf(
      Food(
        id = UUID.randomUUID(),
        userId = "",
        name = "Egg",
        carbs = 1.0,
        proteins = 2.0,
        fats = 3.0,
        calories = 4.0,
        size = 1.0,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
      ),
      Food(
        id = UUID.randomUUID(),
        userId = "",
        name = "Test",
        carbs = 1.0,
        proteins = 2.0,
        fats = 3.0,
        calories = 4.0,
        size = 1.0,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
      ),
      Food(
        id = UUID.randomUUID(),
        userId = "",
        name = "Random",
        carbs = 1.0,
        proteins = 2.0,
        fats = 3.0,
        calories = 4.0,
        size = 1.0,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
      ),
    )

  fun getAllFood(userId: String): List<Food> {
    return foods.map { food -> food.copy(userId = userId) }
  }
}
