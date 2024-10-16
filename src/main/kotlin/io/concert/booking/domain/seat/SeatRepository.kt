package io.concert.booking.domain.seat

interface SeatRepository {

    fun findById(id: Long): Seat?
    fun save(seat: Seat): Seat
}
