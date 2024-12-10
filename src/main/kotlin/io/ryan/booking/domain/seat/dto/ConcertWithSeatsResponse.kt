package io.ryan.booking.domain.seat.dto

import io.ryan.booking.domain.concert.dto.ConcertWithSeatsResult

data class ConcertWithSeatsResponse(
    val concert: ConcertResponse,
    val seats: List<SeatResponse>
) {
    companion object {
        fun from(concertWithSeats: ConcertWithSeatsResult): ConcertWithSeatsResponse {
            return of(
                concertWithSeats.concert.let { ConcertResponse.from(it) },
                concertWithSeats.seats.map { SeatResponse.from(it) }
            )
        }

        fun of(concertResponse: ConcertResponse, seats: List<SeatResponse>): ConcertWithSeatsResponse {
            return ConcertWithSeatsResponse(
                concertResponse,
                seats
            )
        }
    }
}
