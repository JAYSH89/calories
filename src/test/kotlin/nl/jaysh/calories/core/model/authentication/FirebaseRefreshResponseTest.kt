package nl.jaysh.calories.core.model.authentication

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson

class FirebaseRefreshResponseTest {

  private val refreshResponse =
    FirebaseRefreshResponse(
      accessToken = "accessToken",
      expiresIn = "3600",
      tokenType = "Bearer",
      refreshToken = "refreshToken",
      idToken = "idToken",
      userId = "1",
      projectId = "1",
    )

  private val refreshResponseRaw =
    """
    {
        "access_token": "accessToken",
        "expires_in": "3600",
        "token_type": "Bearer",
        "refresh_token": "refreshToken",
        "id_token": "idToken",
        "user_id": "1",
        "project_id": "1"
    }
    """
      .trimIndent()
      .minifyJson()

  @Test
  fun `serialize to json`() {
    val json = Json.encodeToString(refreshResponse)
    assertEquals(refreshResponseRaw, json)
  }

  @Test
  fun `deserialize to object`() {
    val request = Json.decodeFromString<FirebaseRefreshResponse>(refreshResponseRaw)
    assertEquals(refreshResponse, request)
  }

  @Test
  fun `method toRefreshTokenResponse test`() {
    val expected =
      RefreshTokenResponse(
        accessToken = "accessToken",
        expiresIn = 3600,
        tokenType = "Bearer",
        refreshToken = "refreshToken",
      )
    val result = refreshResponse.toRefreshTokenResponse()
    assertEquals(expected, result)
  }

  @Test
  fun `invalid expiresIn should null`() {
    val invalidExpiresIn = refreshResponse.copy(expiresIn = "invalid")
    val result = invalidExpiresIn.toRefreshTokenResponse()
    assertNull(result.expiresIn)
  }
}
