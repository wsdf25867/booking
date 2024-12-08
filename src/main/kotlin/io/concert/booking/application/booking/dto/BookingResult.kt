package io.concert.booking.application.booking.dto

import io.concert.booking.domain.booking.Booking

data class BookingResult(
    val bookingId: Long,
    val seatId: Long,
    val userId: Long,
) {
    companion object {
        fun from(booking: Booking): BookingResult =
            BookingResult(
                booking.id,
                booking.seatId,
                booking.userId,
            )
    }

}
