package io.ryan.booking.domain.concert.dto

import io.ryan.booking.domain.concert.domain.Concert
import io.ryan.booking.domain.concert.domain.Seat

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
