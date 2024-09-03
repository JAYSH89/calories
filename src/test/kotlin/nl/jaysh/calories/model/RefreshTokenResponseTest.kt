package nl.jaysh.calories.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.helper.minifyJson
import nl.jaysh.calories.model.authentication.RefreshTokenResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class RefreshTokenResponseTest {

    private val refreshResponse = RefreshTokenResponse(
        accessToken = "accessToken",
        expiresIn = 3600,
        tokenType = "Bearer",
        refreshToken = "refreshToken",
    )

    private val refreshResponseRaw = """
    {
        "access_token": "accessToken",
        "expires_in": 3600,
        "token_type": "Bearer",
        "refresh_token": "refreshToken"
    }
    """.trimIndent().minifyJson()

    @Test
    fun `serialize to json`() {
        val json = Json.encodeToString(refreshResponse)
        assertEquals(refreshResponseRaw, json)
    }

    @Test
    fun `deserialize to object`() {
        val request = Json.decodeFromString<RefreshTokenResponse>(refreshResponseRaw)
        assertEquals(refreshResponse, request)
    }
}
