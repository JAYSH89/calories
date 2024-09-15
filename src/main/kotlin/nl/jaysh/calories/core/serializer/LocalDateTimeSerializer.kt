package nl.jaysh.calories.core.serializer

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

  private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

  override val descriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): LocalDateTime {
    val value = decoder.decodeString()
    return LocalDateTime.parse(value)
  }

  override fun serialize(encoder: Encoder, value: LocalDateTime) {
    val formattedDate = value.format(formatter)
    encoder.encodeString(formattedDate)
  }
}
