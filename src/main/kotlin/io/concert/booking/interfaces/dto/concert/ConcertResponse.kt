package io.concert.booking.interfaces.dto.concert

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ConcertResponse(
    val concertId: Long,
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val date: LocalDateTime,
    val seats: List<SeatResponse>
)
