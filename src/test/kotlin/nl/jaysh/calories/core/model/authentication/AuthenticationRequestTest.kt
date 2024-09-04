package nl.jaysh.calories.core.model.authentication

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson

class AuthenticationRequestTest {

  private val authRequest = AuthenticationRequest("test@example.com", "testPass123$")

  private val authRequestRaw =
    """
    {
        "email": "test@example.com",
        "password": "testPass123$"
    }
    """
      .trimIndent()
      .minifyJson()

  @Test
  fun `serialize to json`() {
    val json = Json.encodeToString(authRequest)
    assertEquals(authRequestRaw, json)
  }

  @Test
  fun `deserialize to object`() {
    val request = Json.decodeFromString<AuthenticationRequest>(authRequestRaw)
    assertEquals(authRequest.email, request.email)
    assertEquals(authRequest.password, request.password)
  }
}
