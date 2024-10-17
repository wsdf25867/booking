package io.concert.booking.domain.seat

interface SeatRepository {

    fun findById(id: Long): Seat?
    fun save(seat: Seat): Seat
    fun findByIdWithLock(id: Long): Seat?
    fun findAllByIds(seatIds: List<Long>): List<Seat>
}
