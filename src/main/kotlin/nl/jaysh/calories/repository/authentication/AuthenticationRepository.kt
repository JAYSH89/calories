package nl.jaysh.calories.repository.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord.CreateRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import nl.jaysh.calories.model.authentication.FirebaseLoginRequest
import nl.jaysh.calories.model.authentication.FirebaseLoginResponse
import nl.jaysh.calories.model.authentication.FirebaseRefreshRequest
import nl.jaysh.calories.model.authentication.FirebaseRefreshResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.env.Environment
import org.springframework.stereotype.Repository

@Repository
class AuthenticationRepository(
  @Qualifier("authClient") private val authClient: HttpClient,
  @Qualifier("tokenClient") private val tokenClient: HttpClient,
  private val environment: Environment,
) {
  private val apiKey by lazy {
    environment.getProperty("FIREBASE_API_KEY")
      ?: IllegalStateException("FIREBASE_API_KEY is missing")
  }

  fun login(firebaseLoginRequest: FirebaseLoginRequest): FirebaseLoginResponse = runBlocking {
    authClient
      .post("/v1/accounts:signInWithPassword") {
        contentType(ContentType.Application.Json)
        parameter("key", apiKey)
        setBody(firebaseLoginRequest)
      }
      .body<FirebaseLoginResponse>()
  }

  fun register(email: String, password: String): FirebaseLoginResponse = runBlocking {
    val createRequest =
      CreateRequest().setEmail(email).setPassword(password).setEmailVerified(false)

    FirebaseAuth.getInstance().createUser(createRequest)

    val request = FirebaseLoginRequest(email = email, password = password, returnSecureToken = true)
    login(firebaseLoginRequest = request)
  }

  fun refreshToken(request: FirebaseRefreshRequest): FirebaseRefreshResponse = runBlocking {
    tokenClient
      .post("/v1/token") {
        contentType(ContentType.Application.Json)
        parameter("key", apiKey)
        setBody(request)
      }
      .body<FirebaseRefreshResponse>()
  }
}
