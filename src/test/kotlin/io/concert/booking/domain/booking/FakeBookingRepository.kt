package io.concert.booking.domain.booking


class FakeBookingRepository: BookingRepository {
    private val store: MutableMap<Long, Booking> = mutableMapOf()
    private var sequence: Long = 1

    override fun save(booking: Booking): Booking =
        Booking(
            userId = booking.userId,
            seatId = booking.seatId,
            status = booking.status,
            createdAt = booking.createdAt,
            id = sequence++,
        ).also {
            store[it.id] = it
        }

    override fun findById(id: Long): Booking? = store[id]
    override fun findByIdWithLock(id: Long): Booking? {
        return findById(id)
    }

}
