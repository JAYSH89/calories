package nl.jaysh.calories.config.firebase

import com.google.firebase.auth.FirebaseAuth
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class FirebaseAuthenticationFilter : OncePerRequestFilter() {
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain,
  ) {
    val token = request.getHeader("Authorization")?.removePrefix("Bearer ")

    if (token.isNullOrEmpty()) {
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization header missing or invalid")
      return
    }

    try {
      val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)

      val userId = decodedToken.uid

      val authToken = FirebaseAuthenticationToken(userId, decodedToken, emptyList())

      val newContext = SecurityContextHolder.createEmptyContext()
      newContext.authentication = authToken
      newContext.authentication.isAuthenticated = true
      SecurityContextHolder.setContext(newContext)

      filterChain.doFilter(request, response)
    } catch (e: Exception) {
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token")
    }
  }

  override fun shouldNotFilter(request: HttpServletRequest): Boolean {
    return request.servletPath.startsWith("/auth")
  }
}
