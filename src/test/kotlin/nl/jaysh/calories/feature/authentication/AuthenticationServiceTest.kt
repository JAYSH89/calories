package nl.jaysh.calories.feature.authentication

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import nl.jaysh.calories.core.exception.ApiRequestException
import nl.jaysh.calories.helper.objects.AuthenticationObjectHelper
import nl.jaysh.calories.core.model.authentication.FirebaseLoginRequest
import nl.jaysh.calories.core.model.authentication.RefreshTokenRequest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AuthenticationServiceTest {

  private lateinit var repository: AuthenticationRepository
  private lateinit var service: AuthenticationService

  @BeforeTest
  fun setUp() {
    repository = mockk()
    service = AuthenticationService(repository)
  }

  @Test
  fun `login should return AuthenticationResponse successful`() {
    val request = AuthenticationObjectHelper.loginRequest
    every { repository.login(request) } returns AuthenticationObjectHelper.loginResponse

    val authRequest = AuthenticationObjectHelper.authRequest
    val result = service.login(authRequest)

    val expected = AuthenticationObjectHelper.authResponse
    assertEquals(expected, result)

    verify { repository.login(request) }
  }

  @ParameterizedTest(name = "login invalid email: {0} should throw IllegalArgumentException")
  @CsvSource(
    "'', 'empty email'",
    "'nodomain', 'missing domain'",
    "'@example.com', 'missing local part'",
    "'test@.com', 'incomplete domain'",
    "'test@com', 'missing dot'",
    "'test@example@com', 'invalid email'",
  )
  fun `login invalid email should ApiRequestException`(invalidEmail: String, description: String) {
    val authRequest = AuthenticationObjectHelper.authRequest.copy(email = invalidEmail)

    assertFailsWith<ApiRequestException> { service.login(authRequest) }

    val loginRequest = FirebaseLoginRequest.fromLoginRequest(authRequest)
    verify(exactly = 0) { repository.login(loginRequest) }
  }

  @Test
  fun `register should return AuthenticationResponse successful`() {
    val request = AuthenticationObjectHelper.loginRequest
    every { repository.register(request.email, request.password) } returns
      AuthenticationObjectHelper.loginResponse

    val authRequest = AuthenticationObjectHelper.authRequest
    val result = service.register(authRequest)

    val expected = AuthenticationObjectHelper.authResponse
    assertEquals(expected, result)

    verify { repository.register(request.email, request.password) }
  }

  @ParameterizedTest(name = "register invalid email: {0} should throw IllegalArgumentException")
  @CsvSource(
    "'', 'empty email'",
    "'nodomain', 'missing domain'",
    "'@example.com', 'missing local part'",
    "'test@.com', 'incomplete domain'",
    "'test@com', 'missing dot'",
    "'test@example@com', 'invalid email'",
  )
  fun `register invalid email should ApiRequestException`(
    invalidEmail: String,
    description: String,
  ) {
    val authRequest = AuthenticationObjectHelper.authRequest.copy(email = invalidEmail)

    assertFailsWith<ApiRequestException> { service.register(authRequest) }

    verify(exactly = 0) { repository.register(any(), any()) }
  }

  @ParameterizedTest(name = "register invalid password: {0} should throw IllegalArgumentException")
  @CsvSource(
    "'', 'empty password'",
    "'short', 'too short'",
    "'nouppercase1$', 'missing uppercase'",
    "'NOLOWERCASE1$', 'missing lowercase'",
    "'NoSpecialChar1', 'missing special character'",
  )
  fun `register invalid password should ApiRequestException`(
    invalidPassword: String,
    description: String,
  ) {
    val authRequest = AuthenticationObjectHelper.authRequest.copy(password = invalidPassword)

    assertFailsWith<ApiRequestException> { service.register(authRequest) }

    verify(exactly = 0) { repository.register(any(), any()) }
  }

  @Test
  fun `refresh should return RefreshTokenResponse successful`() {
    val refreshRequest = AuthenticationObjectHelper.refreshRequest
    every { repository.refreshToken(refreshRequest) } returns
      AuthenticationObjectHelper.refreshResponse

    val request = RefreshTokenRequest(refreshToken = "refreshToken")
    val result = service.refresh(request)

    val expected = AuthenticationObjectHelper.refreshTokenResponse
    assertEquals(expected, result)

    verify { repository.refreshToken(refreshRequest) }
  }
}
