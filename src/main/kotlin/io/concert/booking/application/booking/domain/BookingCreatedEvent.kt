package io.concert.booking.application.booking.domain

data class BookingCreatedEvent(
    val booking: Booking
) {

    val seatId: Long
        get() = booking.seatId
}
