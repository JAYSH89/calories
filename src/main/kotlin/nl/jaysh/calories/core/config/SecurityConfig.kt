package nl.jaysh.calories.core.config

import nl.jaysh.calories.core.config.firebase.FirebaseAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .cors { it.disable() }
      .csrf { it.disable() }
      .authorizeHttpRequests { requests ->
        requests.requestMatchers("/").permitAll()
        requests.requestMatchers("/error").permitAll()
        requests.requestMatchers("/auth/**").permitAll()
        requests.requestMatchers("/swagger-ui/**").permitAll()
        requests.requestMatchers("/api-docs/**").permitAll()
        requests.anyRequest().authenticated()
      }
      .logout { it.logoutSuccessUrl("/") }
      .oauth2Login(withDefaults())
      .addFilterBefore(
        FirebaseAuthenticationFilter(),
        UsernamePasswordAuthenticationFilter::class.java,
      )
      .build()
  }
}
