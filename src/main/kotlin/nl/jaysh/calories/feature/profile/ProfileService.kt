package nl.jaysh.calories.feature.profile

import nl.jaysh.calories.core.config.firebase.FirebaseAuthenticationToken
import nl.jaysh.calories.core.exception.ErrorMessages.PROFILE_NOT_FOUND
import nl.jaysh.calories.core.exception.NotFoundException
import nl.jaysh.calories.core.model.profile.Profile
import nl.jaysh.calories.core.model.profile.ProfileRequest
import nl.jaysh.calories.core.model.profile.ProfileResponse
import nl.jaysh.calories.core.model.profile.fromProfile
import nl.jaysh.calories.core.model.profile.toProfile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ProfileService(private val repository: ProfileRepository) {

  @Throws(NotFoundException::class)
  fun getProfile(): Profile {
    val userId = getUserId()
    return repository.findById(userId) ?: throw NotFoundException(PROFILE_NOT_FOUND)
  }

  fun saveProfile(profileRequest: ProfileRequest): ProfileResponse {
    val userId = getUserId()
    val upsertProfile = profileRequest.toProfile(userId)

    val profile = repository.upsert(upsertProfile)
    return ProfileResponse.fromProfile(profile)
  }

  fun deleteProfile() {
    val userId = getUserId()
    repository.delete(userId)
  }

  private fun getUserId(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    val firebaseAuthToken = authentication as FirebaseAuthenticationToken
    return firebaseAuthToken.credentials
  }
}
