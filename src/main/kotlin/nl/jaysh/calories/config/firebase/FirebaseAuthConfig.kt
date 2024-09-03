package nl.jaysh.calories.config.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseAuthConfig {

    @Bean
    fun firebaseAuth(): FirebaseAuth {
        val credentials = FileInputStream("src/main/resources/service_account.json")

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(credentials))
            .build()

        val firebaseApp = FirebaseApp.initializeApp(options)

        return FirebaseAuth.getInstance(firebaseApp)
    }
}
