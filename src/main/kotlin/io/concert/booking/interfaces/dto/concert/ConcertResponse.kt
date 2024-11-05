package io.concert.booking.interfaces.dto.concert

import com.fasterxml.jackson.annotation.JsonFormat
import io.concert.booking.application.concert.dto.ConcertResult
import java.time.LocalDateTime

data class ConcertResponse(
    val id: Long,
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val date: LocalDateTime
) {
    companion object {
        fun from(concert: ConcertResult): ConcertResponse {
            return ConcertResponse(
                concert.id,
                concert.name,
                concert.date
            )
        }
    }
}
