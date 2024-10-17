package io.concert.booking.application.booking.dto

import io.concert.booking.domain.booking.Booking

data class BookingDto(
    val bookingId: Long,
    val seatId: Long,
    val userId: Long,
) {
    companion object {
        fun from(booking: Booking): BookingDto =
            BookingDto(
                booking.id,
                booking.seatId,
                booking.userId,
            )
    }

}
