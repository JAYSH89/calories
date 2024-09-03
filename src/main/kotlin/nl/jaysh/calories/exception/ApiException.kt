package nl.jaysh.calories.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.http.HttpStatus
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class ApiException(
    @SerialName("message")
    val message: String,

    @SerialName("status_code")
    val statusCode: HttpStatus,

    @SerialName("timestamp")
    val timestamp: String = ZonedDateTime
        .now(ZoneId.of("UTC"))
        .format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
)
