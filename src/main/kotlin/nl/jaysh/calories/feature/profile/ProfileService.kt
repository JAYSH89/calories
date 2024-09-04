package nl.jaysh.calories.feature.profile

import nl.jaysh.calories.core.exception.NotFoundException
import nl.jaysh.calories.core.model.profile.Profile
import org.springframework.stereotype.Service

@Service
class ProfileService(private val repository: ProfileRepository) {

  @Throws(NotFoundException::class)
  fun getProfile(id: String): Profile {
    return repository.findById(id = id) ?: throw NotFoundException("profile not found")
  }

  fun saveProfile(profile: Profile): Profile {
    return repository.upsert(profile)
  }

  fun deleteProfile(profile: Profile) {
    repository.delete(id = profile.userId)
  }
}
