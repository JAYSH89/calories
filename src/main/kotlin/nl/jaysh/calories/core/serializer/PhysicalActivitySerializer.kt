package nl.jaysh.calories.core.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.jaysh.calories.core.model.profile.PhysicalActivityLevel

object PhysicalActivitySerializer : KSerializer<PhysicalActivityLevel> {

  override val descriptor = PrimitiveSerialDescriptor("PhysicalActivityLevel", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): PhysicalActivityLevel {
    val value = decoder.decodeString()
    return PhysicalActivityLevel.valueOf(value)
  }

  override fun serialize(encoder: Encoder, value: PhysicalActivityLevel) {
    val result = value.toString()
    encoder.encodeString(result)
  }
}
