package io.concert.booking.domain.seat

interface SeatBookedEventHandler {
    fun handle(event: SeatBookedEvent)
}
