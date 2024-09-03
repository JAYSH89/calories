package nl.jaysh.calories.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseRefreshResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: String,
    @SerialName("token_type") val tokenType: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("id_token") val idToken: String,
    @SerialName("user_id") val userId: String,
    @SerialName("project_id") val projectId: String,
) {
    fun toRefreshTokenResponse(): RefreshTokenResponse = RefreshTokenResponse(
        accessToken = accessToken,
        expiresIn = expiresIn.toLongOrNull(),
        tokenType = tokenType,
        refreshToken = refreshToken
    )
}
