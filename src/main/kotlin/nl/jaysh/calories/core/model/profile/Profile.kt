package nl.jaysh.calories.core.model.profile

import java.time.LocalDate
import java.time.LocalDateTime

data class Profile(
  val userId: String,
  val height: Int? = null,
  val weight: Int? = null,
  val birthday: LocalDate? = null,
  val sex: Sex? = null,
  val physicalActivityLevel: PhysicalActivityLevel? = null,
  val createdAt: LocalDateTime? = null,
  val updatedAt: LocalDateTime? = null,
)
