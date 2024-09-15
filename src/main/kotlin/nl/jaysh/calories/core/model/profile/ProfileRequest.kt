package nl.jaysh.calories.core.model.profile

import java.time.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.jaysh.calories.core.serializer.LocalDateSerializer
import nl.jaysh.calories.core.serializer.PhysicalActivitySerializer
import nl.jaysh.calories.core.serializer.SexSerializer

@Serializable
data class ProfileRequest(
  @SerialName("height") val height: Int? = null,
  @SerialName("weight") val weight: Int? = null,
  @SerialName("birthday") @Serializable(with = LocalDateSerializer::class) val birthday: LocalDate? = null,
  @SerialName("sex") @Serializable(with = SexSerializer::class) val sex: Sex? = null,
  @SerialName("physical_activity_level")
  @Serializable(with = PhysicalActivitySerializer::class)
  val physicalActivityLevel: PhysicalActivityLevel? = null,
)

fun ProfileRequest.toProfile(userId: String): Profile {
  return Profile(
    userId = userId,
    height = height,
    weight = weight,
    birthday = birthday,
    sex = sex,
    physicalActivityLevel = physicalActivityLevel,
  )
}
