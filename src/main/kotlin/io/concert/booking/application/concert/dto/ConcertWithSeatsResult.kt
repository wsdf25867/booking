package io.concert.booking.application.concert.dto

import io.concert.booking.application.seat.dto.SeatResult
import io.concert.booking.domain.concert.Concert
import io.concert.booking.domain.seat.Seat

data class ConcertWithSeatsResult(
    val concert: ConcertResult,
    val seats: List<SeatResult>
) {

    companion object {
        fun of(concert: Concert, seats: List<Seat>): ConcertWithSeatsResult {
            return ConcertWithSeatsResult(
                concert.let(ConcertResult::from),
                seats.map(SeatResult::from)
            )
        }
    }
}
