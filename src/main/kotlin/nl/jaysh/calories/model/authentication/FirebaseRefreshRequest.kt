package nl.jaysh.calories.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseRefreshRequest(
  @SerialName("grant_type") val grantType: String,
  @SerialName("refresh_token") val refreshToken: String,
) {
  companion object {
    fun fromRefreshRequest(request: RefreshTokenRequest): FirebaseRefreshRequest {
      return FirebaseRefreshRequest(
        grantType = "refresh_token",
        refreshToken = request.refreshToken,
      )
    }
  }
}
