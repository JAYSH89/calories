package nl.jaysh.calories.core.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable data class RefreshTokenRequest(@SerialName("refresh_token") val refreshToken: String)
