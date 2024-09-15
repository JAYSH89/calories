package nl.jaysh.calories.core.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.jaysh.calories.core.model.profile.Sex

object SexSerializer : KSerializer<Sex?> {

  override val descriptor = PrimitiveSerialDescriptor("Sex", PrimitiveKind.STRING)

  @OptIn(ExperimentalSerializationApi::class)
  override fun deserialize(decoder: Decoder): Sex? {
    val value = decoder.decodeNullableSerializableValue(deserializer = String.serializer())
    return value?.let { Sex.valueOf(it) }
  }

  @OptIn(ExperimentalSerializationApi::class)
  override fun serialize(encoder: Encoder, value: Sex?) {
    val result = value?.toString()
    encoder.encodeNullableSerializableValue(String.serializer(), result)
  }
}
