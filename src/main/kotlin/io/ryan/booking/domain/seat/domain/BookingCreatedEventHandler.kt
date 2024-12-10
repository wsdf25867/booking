package io.ryan.booking.domain.seat.domain

import io.ryan.booking.domain.booking.domain.BookingCreatedEvent

interface BookingCreatedEventHandler {
    fun handle(event: BookingCreatedEvent)
}
