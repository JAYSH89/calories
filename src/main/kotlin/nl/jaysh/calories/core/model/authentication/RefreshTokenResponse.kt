package nl.jaysh.calories.core.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
  @SerialName("access_token") val accessToken: String,
  @SerialName("expires_in") val expiresIn: Long? = null,
  @SerialName("token_type") val tokenType: String,
  @SerialName("refresh_token") val refreshToken: String,
)
