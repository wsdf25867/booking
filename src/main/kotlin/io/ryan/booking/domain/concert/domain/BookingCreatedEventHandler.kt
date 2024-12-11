package io.ryan.booking.domain.concert.domain

import io.ryan.booking.domain.booking.domain.BookingCreatedEvent

interface BookingCreatedEventHandler {
    fun handle(event: BookingCreatedEvent)
}
