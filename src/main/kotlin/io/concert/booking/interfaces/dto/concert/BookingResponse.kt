package io.concert.booking.interfaces.dto.concert

import com.fasterxml.jackson.annotation.JsonFormat
import io.concert.booking.application.booking.dto.BookingResult
import java.time.LocalDateTime

data class BookingResponse(
    val bookingId: Long,
    val seatId: Long,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val bookedAt: LocalDateTime,
){
    companion object {
        fun from(booking: BookingResult): BookingResponse {
            return BookingResponse(
                booking.bookingId,
                booking.seatId,
                LocalDateTime.now()
            )
        }
    }

}
