package io.concert.booking.domain.booking

import java.time.LocalDateTime


class FakeBookingRepository: BookingRepository {
    private val store: MutableMap<Long, Booking> = mutableMapOf()
    private var sequence: Long = 1

    override fun save(booking: Booking): Booking =
        Booking(
            userId = booking.userId,
            seatId = booking.seatId,
            price = booking.price,
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

    override fun findAllByStatusAndCreatedAtBefore(status: BookingStatus, createdAt: LocalDateTime): List<Booking> {
        return store.values.filter { it.status == status && it.createdAt < createdAt }
    }

}
