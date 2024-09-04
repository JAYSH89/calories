package nl.jaysh.calories.core.model.food

import java.time.LocalDateTime
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.jaysh.calories.core.serializer.LocalDateTimeSerializer
import nl.jaysh.calories.core.serializer.UUIDSerializer

@Serializable
data class Food(
    @SerialName("id") @Serializable(with = UUIDSerializer::class) val id: UUID,
    @SerialName("name") val name: String,
    @SerialName("user_id") val userId: String,
    @SerialName("carbs") val carbs: Double,
    @SerialName("proteins") val proteins: Double,
    @SerialName("fats") val fats: Double,
    @SerialName("calories") val calories: Double,
    @SerialName("size") val size: Double,
    @SerialName("created_at")
  @Serializable(with = LocalDateTimeSerializer::class)
  val createdAt: LocalDateTime,
    @SerialName("updated_at")
  @Serializable(with = LocalDateTimeSerializer::class)
  val updatedAt: LocalDateTime,
)
