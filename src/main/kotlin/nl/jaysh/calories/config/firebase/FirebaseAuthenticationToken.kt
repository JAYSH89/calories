package nl.jaysh.calories.config.firebase

import com.google.firebase.auth.FirebaseToken
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class FirebaseAuthenticationToken(
    private val idToken: String,
    private val firebaseToken: FirebaseToken,
    authorities: Collection<GrantedAuthority>,
) : AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): String = idToken

    override fun getPrincipal(): String = firebaseToken.uid
}
