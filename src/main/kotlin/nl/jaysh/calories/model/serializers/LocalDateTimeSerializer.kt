package nl.jaysh.calories.model.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "LocalDateTime",
        kind = PrimitiveKind.STRING,
    )

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val value = decoder.decodeString()
        return LocalDateTime.parse(value)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val formattedDate = value.format(formatter)
        encoder.encodeString(formattedDate)
    }
}
