package io.concert.booking.application.concert.dto

import io.concert.booking.application.seat.dto.SeatResult

data class ConcertWithSeatsResult(
    val concert: ConcertResult,
    val seats: List<SeatResult>
) {

    companion object {
        fun of(concert: ConcertResult, seats: List<SeatResult>): ConcertWithSeatsResult {
            return ConcertWithSeatsResult(concert, seats)
        }
    }
}
