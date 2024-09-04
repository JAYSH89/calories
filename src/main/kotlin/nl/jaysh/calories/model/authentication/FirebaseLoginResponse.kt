package nl.jaysh.calories.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseLoginResponse(
  @SerialName("kind") val kind: String,
  @SerialName("localId") val localId: String,
  @SerialName("email") val email: String,
  @SerialName("displayName") val displayName: String,
  @SerialName("idToken") val idToken: String,
  @SerialName("registered") val registered: Boolean,
  @SerialName("refreshToken") val refreshToken: String,
  @SerialName("expiresIn") val expiresIn: String,
) {
  fun toLoginResponse(): AuthenticationResponse {
    return AuthenticationResponse(
      accessToken = idToken,
      refreshToken = refreshToken,
      expiresIn = expiresIn.toLongOrNull(),
    )
  }
}
