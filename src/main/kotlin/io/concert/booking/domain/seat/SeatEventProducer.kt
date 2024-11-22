package io.concert.booking.domain.seat

interface SeatEventProducer {

    fun send(event: SeatBookedEvent)
}
