package nl.jaysh.calories.helper.objects

import nl.jaysh.calories.model.authentication.AuthenticationRequest
import nl.jaysh.calories.model.authentication.AuthenticationResponse
import nl.jaysh.calories.model.authentication.FirebaseLoginRequest
import nl.jaysh.calories.model.authentication.FirebaseLoginResponse
import nl.jaysh.calories.model.authentication.FirebaseRefreshRequest
import nl.jaysh.calories.model.authentication.FirebaseRefreshResponse
import nl.jaysh.calories.model.authentication.RefreshTokenRequest
import nl.jaysh.calories.model.authentication.RefreshTokenResponse

object AuthenticationObjectHelper {
  val authRequest = AuthenticationRequest(email = "test@example.com", password = "testPass123$")

  val authResponse =
    AuthenticationResponse(
      accessToken = "accessToken",
      refreshToken = "refreshToken",
      expiresIn = 3600,
    )

  val loginRequest =
    FirebaseLoginRequest(
      email = "test@example.com",
      password = "testPass123$",
      returnSecureToken = true,
    )

  val loginResponse =
    FirebaseLoginResponse(
      kind = "test kind",
      localId = "localId",
      email = "test@example.com",
      displayName = "",
      idToken = "accessToken",
      registered = true,
      refreshToken = "refreshToken",
      expiresIn = "3600",
    )

  val refreshTokenRequest = RefreshTokenRequest(refreshToken = "refreshToken")

  val refreshRequest =
    FirebaseRefreshRequest(grantType = "refresh_token", refreshToken = "refreshToken")

  val refreshResponse =
    FirebaseRefreshResponse(
      accessToken = "accessToken",
      expiresIn = "3600",
      tokenType = "Bearer",
      refreshToken = "refreshToken",
      idToken = "accessToken",
      userId = "testUser",
      projectId = "1",
    )

  val refreshTokenResponse =
    RefreshTokenResponse(
      accessToken = "accessToken",
      expiresIn = 3600,
      tokenType = "Bearer",
      refreshToken = "refreshToken",
    )
}
