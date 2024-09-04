package nl.jaysh.calories.core.config

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClientConfig {

  @Bean(name = ["authClient"])
  fun authClient(): HttpClient = createHttpClient(hostUrl = "identitytoolkit.googleapis.com")

  @Bean(name = ["tokenClient"])
  fun tokenClient(): HttpClient = createHttpClient(hostUrl = "securetoken.googleapis.com")

  private fun createHttpClient(hostUrl: String) =
    HttpClient(CIO) {
      expectSuccess = true

      install(ContentNegotiation) {
        json(
          Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
          }
        )
      }

      install(Logging) { level = LogLevel.ALL }

      install(HttpTimeout) {
        requestTimeoutMillis = 10_000
        socketTimeoutMillis = 10_000
        connectTimeoutMillis = 10_000
      }

      install(DefaultRequest) {
        url {
          protocol = URLProtocol.HTTPS
          host = hostUrl
        }
      }
    }
}
