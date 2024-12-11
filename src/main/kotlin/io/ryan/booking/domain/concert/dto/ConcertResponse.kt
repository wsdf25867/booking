package io.ryan.booking.domain.concert.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ConcertResponse(
    val id: Long,
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val date: LocalDateTime
) {
}
