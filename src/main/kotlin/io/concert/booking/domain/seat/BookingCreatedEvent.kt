package io.concert.booking.domain.seat

import io.concert.booking.domain.booking.Booking

data class BookingCreatedEvent(
    val seatId: Long
) {
    constructor(booking: Booking) : this(booking.seatId)
}
