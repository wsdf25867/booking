package io.concert.booking.domain.seat

class FakeSeatRepository : SeatRepository {
    private val store: MutableMap<Long, Seat> = mutableMapOf()
    private var sequence: Long = 1

    override fun findById(id: Long): Seat? {
        return store[id]
    }

    override fun save(seat: Seat): Seat =
        Seat(
            concertId = seat.concertId,
            seatNumber = seat.seatNumber,
            price = seat.price,
            id = sequence++
        ).also {
            store[it.id] = it
        }

    override fun findByIdWithLock(seatId: Long): Seat? {
        return findById(seatId)
    }

    override fun findAllByIdIn(seatIds: List<Long>): List<Seat> {
        return store.filter { it.key in seatIds }.values.toList()
    }

    override fun findAllByConcertIdAndStatus(concertId: Long, status: SeatStatus): List<Seat> {
        return store.values
            .filter { it.concertId == concertId && it.status == status }
    }
}
