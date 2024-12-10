package io.concert.booking.application.seat.domain

import io.concert.booking.application.booking.domain.BookingCreatedEvent

interface SeatBookedEventHandler {
    fun handle(event: BookingCreatedEvent)
}
