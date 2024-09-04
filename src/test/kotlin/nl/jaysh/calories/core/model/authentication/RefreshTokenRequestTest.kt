package nl.jaysh.calories.core.model.authentication

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson

class RefreshTokenRequestTest {

  private val refreshRequest = RefreshTokenRequest(refreshToken = "token")

  private val refreshRequestRaw =
    """
    {
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
    val request = Json.decodeFromString<RefreshTokenRequest>(refreshRequestRaw)
    assertEquals(refreshRequest, request)
  }
}
