package nl.jaysh.calories.core.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.jaysh.calories.core.model.profile.Sex

object SexSerializer : KSerializer<Sex> {

  override val descriptor = PrimitiveSerialDescriptor("Sex", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): Sex {
    val value = decoder.decodeString()
    return Sex.valueOf(value)
  }

  override fun serialize(encoder: Encoder, value: Sex) {
    val result = value.toString()
    encoder.encodeString(result)
  }
}
