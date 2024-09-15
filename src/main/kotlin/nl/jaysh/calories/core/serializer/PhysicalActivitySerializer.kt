package nl.jaysh.calories.core.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.jaysh.calories.core.model.profile.PhysicalActivityLevel

object PhysicalActivitySerializer : KSerializer<PhysicalActivityLevel?> {

  override val descriptor = PrimitiveSerialDescriptor("PhysicalActivityLevel", PrimitiveKind.STRING)

  @OptIn(ExperimentalSerializationApi::class)
  override fun deserialize(decoder: Decoder): PhysicalActivityLevel? {
    val value = decoder.decodeNullableSerializableValue(deserializer = String.serializer())
    return value?.let { PhysicalActivityLevel.valueOf(it) }
  }

  @OptIn(ExperimentalSerializationApi::class)
  override fun serialize(encoder: Encoder, value: PhysicalActivityLevel?) {
    val result = value?.toString()
    encoder.encodeNullableSerializableValue(String.serializer(), result)
  }
}
