package nl.jaysh.calories.core.model.authentication

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson

class FirebaseRefreshRequestTest {

  private val refreshRequest =
    FirebaseRefreshRequest(grantType = "refresh_token", refreshToken = "token")

  private val refreshRequestRaw =
    """
    {
        "grant_type": "refresh_token",
        "refresh_token": "token"
    }
    """
      .trimIndent()
      .minifyJson()

  @Test
  fun `serialize to json`() {
    val json = Json.encodeToString(refreshRequest)
    assertEquals(refreshRequestRaw, json)
  }

  @Test
  fun `deserialize to object`() {
    val request = Json.decodeFromString<FirebaseRefreshRequest>(refreshRequestRaw)
    assertEquals(refreshRequest.grantType, request.grantType)
    assertEquals(refreshRequest.refreshToken, request.refreshToken)
  }

  @Test
  fun `method fromRefreshRequest test`() {
    val refreshTokenRequest = RefreshTokenRequest(refreshToken = "token")
    val result = FirebaseRefreshRequest.fromRefreshRequest(request = refreshTokenRequest)
    assertEquals(refreshRequest, result)
  }
}
