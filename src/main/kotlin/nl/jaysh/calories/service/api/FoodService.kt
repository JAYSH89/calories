package nl.jaysh.calories.service.api

import nl.jaysh.calories.config.firebase.FirebaseAuthenticationToken
import nl.jaysh.calories.repository.api.FoodRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

// Dummy

@Service
class FoodService(private val repository: FoodRepository) {

    fun getAllFood() = repository.getAllFood(userId = getUserId())

    private fun getUserId(): String {
        val authentication = SecurityContextHolder.getContext().authentication as FirebaseAuthenticationToken
        return authentication.credentials
    }
}
