package nl.jaysh.calories.feature.profile

import nl.jaysh.calories.core.model.profile.Profile
import nl.jaysh.calories.core.model.profile.ProfileRequest
import nl.jaysh.calories.core.model.profile.ProfileResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/profile")
class ProfileController(private val service: ProfileService) {

  @GetMapping
  fun getProfile(): Profile {
    return service.getProfile()
  }

  @PostMapping
  fun create(@RequestBody profile: ProfileRequest): ProfileResponse {
    return service.saveProfile(profile)
  }

  @PutMapping
  fun update(@RequestBody profile: ProfileRequest): ProfileResponse {
    return service.saveProfile(profile)
  }

  @DeleteMapping
  fun delete() {
    service.deleteProfile()
  }
}
