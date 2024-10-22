package io.concert.booking.interfaces.dto.queue

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

data class TokenResponse(
    val userId: Long,
    val token: UUID,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val expiredAt: LocalDateTime
) {

}
