package nl.jaysh.calories.feature.authentication

import nl.jaysh.calories.core.model.authentication.AuthenticationRequest
import nl.jaysh.calories.core.model.authentication.AuthenticationResponse
import nl.jaysh.calories.core.model.authentication.RefreshTokenRequest
import nl.jaysh.calories.core.model.authentication.RefreshTokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(private val service: AuthenticationService) {

  @PostMapping("/login")
  fun login(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
    return service.login(request = request)
  }

  @PostMapping("/register")
  fun register(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
    return service.register(request = request)
  }

  @PostMapping("/refresh")
  fun refresh(@RequestBody request: RefreshTokenRequest): RefreshTokenResponse {
    return service.refresh(request = request)
  }
}
