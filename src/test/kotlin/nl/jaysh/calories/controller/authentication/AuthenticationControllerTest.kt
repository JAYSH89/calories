package nl.jaysh.calories.controller.authentication

import com.google.firebase.ErrorCode
import com.google.firebase.auth.AuthErrorCode
import com.google.firebase.auth.FirebaseAuthException
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.jaysh.calories.exception.ApiException
import nl.jaysh.calories.exception.ApiRequestException
import nl.jaysh.calories.exception.ErrorMessages
import nl.jaysh.calories.exception.ErrorMessages.EMAIL_EXISTS
import nl.jaysh.calories.exception.ErrorMessages.INVALID_EMAIL_ADDRESS
import nl.jaysh.calories.exception.ErrorMessages.INVALID_PASSWORD
import nl.jaysh.calories.helper.objects.AuthenticationObjectHelper
import nl.jaysh.calories.service.authentication.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [AuthenticationController::class])
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

  @Autowired private lateinit var mockMvc: MockMvc

  @MockkBean private lateinit var authService: AuthenticationService

  private val json = Json { ignoreUnknownKeys = true }

  // Helper method for POST request
  private fun performPostRequest(url: String, content: String): ResultActions {
    return mockMvc.perform(
      MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON).content(content)
    )
  }

  // Helper method to verify response and assert Exceptions
  private fun verifyExceptionResponse(
    result: ResultActions,
    expectedStatus: HttpStatus,
    expectedMessage: String,
  ) {
    val response =
      result
        .andExpect(MockMvcResultMatchers.status().`is`(expectedStatus.value()))
        .andReturn()
        .response
        .contentAsString

    val actualException = Json.decodeFromString<ApiException>(response)
    val expectedException = ApiException(message = expectedMessage, statusCode = expectedStatus)

    assertEquals(expectedException.message, actualException.message)
    assertEquals(expectedException.statusCode, actualException.statusCode)
  }

  @Test
  fun `register should return 200 and response success`() {
    val request = AuthenticationObjectHelper.authRequest
    every { authService.register(request) } returns AuthenticationObjectHelper.authResponse

    performPostRequest(url = "/auth/register", content = json.encodeToString(request))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect {
        val actualJson = it.response.contentAsString
        val expectedJson = Json.encodeToString(AuthenticationObjectHelper.authResponse)
        assertEquals(expectedJson, actualJson)
      }

    verify { authService.register(any()) }
  }

  @Test
  fun `register not valid email address should 400 BAD REQUEST`() {
    val invalidRequest = AuthenticationObjectHelper.authRequest.copy(email = "invalid-email")
    every { authService.register(invalidRequest) } throws
      ApiRequestException("invalid email address")

    val result =
      performPostRequest(url = "/auth/register", content = json.encodeToString(invalidRequest))
    verifyExceptionResponse(
      result = result,
      expectedStatus = HttpStatus.BAD_REQUEST,
      expectedMessage = INVALID_EMAIL_ADDRESS,
    )

    verify { authService.register(any()) }
  }

  @Test
  fun `register not valid password should 400 BAD REQUEST`() {
    val invalidRequest = AuthenticationObjectHelper.authRequest.copy(password = "")
    every { authService.register(invalidRequest) } throws
      ApiRequestException(ErrorMessages.INVALID_PASSWORD)

    val result = performPostRequest("/auth/register", content = Json.encodeToString(invalidRequest))
    verifyExceptionResponse(
      result = result,
      expectedStatus = HttpStatus.BAD_REQUEST,
      expectedMessage = INVALID_PASSWORD,
    )

    verify { authService.register(any()) }
  }

  @Test
  fun `register email already in use should 409 CONFLICT`() {
    val request = AuthenticationObjectHelper.authRequest
    val firebaseException =
      FirebaseAuthException(
        ErrorCode.ALREADY_EXISTS,
        EMAIL_EXISTS,
        Throwable(),
        null,
        AuthErrorCode.EMAIL_ALREADY_EXISTS,
      )
    every { authService.register(request) } throws firebaseException

    val result = performPostRequest(url = "/auth/register", content = json.encodeToString(request))
    verifyExceptionResponse(
      result = result,
      expectedStatus = HttpStatus.CONFLICT,
      expectedMessage = EMAIL_EXISTS,
    )

    verify { authService.register(any()) }
  }

  @Test
  fun `login should return 200 and response success`() {
    val request = AuthenticationObjectHelper.authRequest
    every { authService.login(request) } returns AuthenticationObjectHelper.authResponse

    performPostRequest("/auth/login", content = json.encodeToString(request))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect {
        val actualJson = it.response.contentAsString
        val expectedJson = Json.encodeToString(AuthenticationObjectHelper.authResponse)
        assertEquals(expectedJson, actualJson)
      }

    verify { authService.login(any()) }
  }

  @Test
  fun `register empty body should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `register email parameter missing should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `register password parameter missing should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `login invalid email address should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `login invalid email password combination should 401 UNAUTHORIZED`() {
    // todo
  }

  @Test
  fun `login empty body should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `login email parameter missing should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `login password parameter missing should 400 BAD REQUEST`() {
    // todo
  }

  @Test
  fun `refresh should return 200 and response success`() {
    val request = AuthenticationObjectHelper.refreshTokenRequest
    every { authService.refresh(request) } returns AuthenticationObjectHelper.refreshTokenResponse

    performPostRequest(url = "/auth/refresh", content = json.encodeToString(request))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect {
        val actualJson = it.response.contentAsString
        val expectedJson = Json.encodeToString(AuthenticationObjectHelper.refreshTokenResponse)
        assertEquals(expectedJson, actualJson)
      }

    verify { authService.refresh(any()) }
  }

  @Test
  fun `refresh empty body should 400 BAD REQUEST`() {
    // todo
  }
}
