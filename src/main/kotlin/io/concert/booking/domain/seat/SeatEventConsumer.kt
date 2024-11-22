package io.concert.booking.domain.seat

interface SeatEventConsumer {

    fun consume(message: String)
}
