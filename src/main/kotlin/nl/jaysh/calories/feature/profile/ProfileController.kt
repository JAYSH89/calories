package nl.jaysh.calories.feature.profile

import nl.jaysh.calories.core.model.profile.Profile
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/profile")
class ProfileController(private val service: ProfileService) {

  @GetMapping("/{id}")
  fun getProfile(@PathVariable("id") id: String): Profile {
    return service.getProfile(id = id)
  }

  @PostMapping
  fun create(@RequestBody profile: Profile): Profile {
    return service.saveProfile(profile)
  }

  @DeleteMapping
  fun delete(@RequestBody profile: Profile) {
    service.deleteProfile(profile)
  }
}
