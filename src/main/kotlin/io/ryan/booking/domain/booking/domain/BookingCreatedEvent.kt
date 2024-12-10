package io.ryan.booking.domain.booking.domain

data class BookingCreatedEvent(
    val booking: Booking
) {

    val seatId: Long
        get() = booking.seatId
}
