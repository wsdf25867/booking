package io.concert.booking.interfaces.dto.concert

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class BookingResponse(
    val bookingId: Long,
    val concertId: Long,
    val seatId: Long,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val bookedAt: LocalDateTime,
){

}
