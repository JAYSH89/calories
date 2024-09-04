package nl.jaysh.calories.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseLoginRequest(
  @SerialName("email") val email: String,
  @SerialName("password") val password: String,
  @SerialName("returnSecureToken") val returnSecureToken: Boolean,
) {
  companion object {
    fun fromLoginRequest(loginRequest: AuthenticationRequest): FirebaseLoginRequest {
      return FirebaseLoginRequest(
        email = loginRequest.email,
        password = loginRequest.password,
        returnSecureToken = true,
      )
    }
  }
}
