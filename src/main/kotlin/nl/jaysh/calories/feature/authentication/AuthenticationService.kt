package nl.jaysh.calories.feature.authentication

import io.ktor.client.request.*
import nl.jaysh.calories.core.exception.ApiRequestException
import nl.jaysh.calories.core.exception.ErrorMessages.INVALID_EMAIL_ADDRESS
import nl.jaysh.calories.core.exception.ErrorMessages.INVALID_PASSWORD
import nl.jaysh.calories.core.model.authentication.AuthenticationRequest
import nl.jaysh.calories.core.model.authentication.AuthenticationResponse
import nl.jaysh.calories.core.model.authentication.FirebaseLoginRequest
import nl.jaysh.calories.core.model.authentication.FirebaseRefreshRequest
import nl.jaysh.calories.core.model.authentication.RefreshTokenRequest
import nl.jaysh.calories.core.model.authentication.RefreshTokenResponse
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val repository: AuthenticationRepository) {
  fun login(request: AuthenticationRequest): AuthenticationResponse {
    validateEmail(request.email)

    val loginRequest = FirebaseLoginRequest.fromLoginRequest(loginRequest = request)
    val response = repository.login(firebaseLoginRequest = loginRequest)
    return response.toLoginResponse()
  }

  fun register(request: AuthenticationRequest): AuthenticationResponse {
    validateEmail(request.email)
    validatePassword(request.password)

    val response = repository.register(email = request.email, password = request.password)

    return response.toLoginResponse()
  }

  fun refresh(request: RefreshTokenRequest): RefreshTokenResponse {
    val refreshRequest = FirebaseRefreshRequest.fromRefreshRequest(request = request)
    val response = repository.refreshToken(refreshRequest)
    return response.toRefreshTokenResponse()
  }

  @Throws(ApiRequestException::class)
  private fun validateEmail(email: String) {
    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    if (!email.matches(emailPattern)) {
      throw ApiRequestException(INVALID_EMAIL_ADDRESS)
    }
  }

  @Throws(ApiRequestException::class)
  private fun validatePassword(password: String) {
    val length = password.length >= 10
    val uppercase = password.any { it.isUpperCase() }
    val lowercase = password.any { it.isLowerCase() }
    val specialCharacter = password.any { it in "!@#\\\$%^&*()-_=+[]{}|;:'\\\",.<>?/" }

    val validPassword = length && uppercase && lowercase && specialCharacter

    if (!validPassword) throw ApiRequestException(INVALID_PASSWORD)
  }
}
