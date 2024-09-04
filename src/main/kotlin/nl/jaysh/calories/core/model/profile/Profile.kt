package nl.jaysh.calories.core.model.profile

import java.time.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.jaysh.calories.core.serializer.LocalDateSerializer
import nl.jaysh.calories.core.serializer.PhysicalActivitySerializer
import nl.jaysh.calories.core.serializer.SexSerializer

@Serializable
data class Profile(
  @SerialName("user_id") val userId: String,
  @SerialName("height") val height: Int?,
  @SerialName("weight") val weight: Int?,
  @SerialName("birthday") @Serializable(with = LocalDateSerializer::class) val birthday: LocalDate?,
  @SerialName("sex") @Serializable(with = SexSerializer::class) val sex: Sex?,
  @SerialName("physical_activity_level")
  @Serializable(with = PhysicalActivitySerializer::class)
  val physicalActivityLevel: PhysicalActivityLevel?,
)
