package nl.jaysh.calories.config.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import java.io.FileInputStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FirebaseAuthConfig {

  @Bean
  fun firebaseAuth(): FirebaseAuth {
    val file = FileInputStream("src/main/resources/service_account.json")
    val credentials = GoogleCredentials.fromStream(file)
    val options = FirebaseOptions.builder().setCredentials(credentials).build()
    val firebaseApp = FirebaseApp.initializeApp(options)
    return FirebaseAuth.getInstance(firebaseApp)
  }
}
