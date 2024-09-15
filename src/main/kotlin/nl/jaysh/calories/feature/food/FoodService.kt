package nl.jaysh.calories.feature.food

import nl.jaysh.calories.core.config.firebase.FirebaseAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

// Dummy

@Service
class FoodService(private val repository: FoodRepository) {

  fun getAllFood() = repository.getAllFood(userId = getUserId())

  private fun getUserId(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    val firebaseAuthToken = authentication as FirebaseAuthenticationToken
    return firebaseAuthToken.credentials
  }
}
