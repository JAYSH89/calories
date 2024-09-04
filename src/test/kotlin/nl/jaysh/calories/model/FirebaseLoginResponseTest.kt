package nl.jaysh.calories.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson
import nl.jaysh.calories.model.authentication.AuthenticationResponse
import nl.jaysh.calories.model.authentication.FirebaseLoginResponse

class FirebaseLoginResponseTest {

  private val loginResponse =
    FirebaseLoginResponse(
      kind = "kind",
      localId = "1",
      email = "test@example.com",
      displayName = "",
      idToken = "accessToken",
      registered = true,
      refreshToken = "refreshToken",
      expiresIn = "3600",
    )

  private val loginResponseRaw =
    """
    {
        "kind":"kind",
        "localId":"1",
        "email":"test@example.com",
        "displayName":"",
        "idToken":"accessToken",
        "registered":true,
        "refreshToken":"refreshToken",
        "expiresIn":"3600"
    }
    """
      .trimIndent()
      .minifyJson()

  @Test
  fun `serialize to json`() {
    val json = Json.encodeToString(loginResponse)
    assertEquals(loginResponseRaw, json)
  }

  @Test
  fun `deserialize to object`() {
    val request = Json.decodeFromString<FirebaseLoginResponse>(loginResponseRaw)
    assertEquals(loginResponse, request)
  }

  @Test
  fun `test fromLoginRequest successful`() {
    val result = loginResponse.toLoginResponse()

    val expected =
      AuthenticationResponse(
        accessToken = "accessToken",
        refreshToken = "refreshToken",
        expiresIn = 3600,
      )

    assertEquals(expected, result)
  }

  @Test
  fun `test fromLoginRequest invalid expiresIn`() {
    val invalidExpiresIn = loginResponse.copy(expiresIn = "invalid")
    val result = invalidExpiresIn.toLoginResponse()
    assertNull(result.expiresIn)
  }
}
