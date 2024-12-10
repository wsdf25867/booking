package io.ryan.booking.domain.seat.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.ryan.booking.domain.concert.dto.ConcertResult
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
