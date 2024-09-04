package nl.jaysh.calories.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson
import nl.jaysh.calories.model.authentication.AuthenticationRequest
import nl.jaysh.calories.model.authentication.FirebaseLoginRequest

class FirebaseLoginRequestTest {

  private val loginRequest =
    FirebaseLoginRequest(
      email = "test@example.com",
      password = "testPass123$",
      returnSecureToken = true,
    )

  private val loginRequestRaw =
    """
    {
        "email":"test@example.com",
        "password":"testPass123$",
        "returnSecureToken":true
    }
    """
      .trimIndent()
      .minifyJson()

  @Test
  fun `serialize to json`() {
    val json = Json.encodeToString(loginRequest)
    assertEquals(loginRequestRaw, json)
  }

  @Test
  fun `deserialize json`() {
    val request = Json.decodeFromString<FirebaseLoginRequest>(loginRequestRaw)

    assertEquals(loginRequest.email, request.email)
    assertEquals(loginRequest.password, request.password)
    assertEquals(loginRequest.returnSecureToken, request.returnSecureToken)
  }

  @Test
  fun `test fromLoginRequest successful`() {
    val request = AuthenticationRequest(email = "test@example.com", password = "testPass123$")
    val result = FirebaseLoginRequest.fromLoginRequest(request)
    assertEquals(loginRequest, result)
  }
}
