package nl.jaysh.calories.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson
import nl.jaysh.calories.model.authentication.AuthenticationResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthenticationResponseTest {

    private val authResponse = AuthenticationResponse(
        accessToken = "example",
        refreshToken = "token",
        expiresIn = 3600,
    )

    private val authResponseRaw = """
    {
        "access_token":"example",
        "refresh_token":"token",
        "expires_in":3600
    }
    """.trimIndent().minifyJson()

    @Test
    fun `serialize to json`() {
        val json = Json.encodeToString(authResponse)
        assertEquals(authResponseRaw, json)
    }

    @Test
    fun `deserialize to object`() {
        val request = Json.decodeFromString<AuthenticationResponse>(authResponseRaw)

        assertEquals(authResponse.accessToken, request.accessToken)
        assertEquals(authResponse.refreshToken, request.refreshToken)
        assertEquals(authResponse.expiresIn, request.expiresIn)
    }
}
