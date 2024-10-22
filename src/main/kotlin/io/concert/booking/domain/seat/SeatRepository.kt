package io.concert.booking.domain.seat

interface SeatRepository {

    fun findById(id: Long): Seat?
    fun save(seat: Seat): Seat
    fun findByIdWithLock(id: Long): Seat?
    fun findAllByIdIn(seatIds: List<Long>): List<Seat>
    fun findAllByConcertIdAndStatus(concertId: Long, status: SeatStatus): List<Seat>
}
