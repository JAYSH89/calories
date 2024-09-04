package nl.jaysh.calories.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
  @SerialName("access_token") val accessToken: String,
  @SerialName("refresh_token") val refreshToken: String,
  @SerialName("expires_in") val expiresIn: Long? = null,
)
