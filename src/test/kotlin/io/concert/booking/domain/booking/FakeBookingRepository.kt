package io.concert.booking.domain.booking

import io.concert.booking.domain.booking.Booking

class FakeBookingRepository: BookingRepository {
    private val store: MutableMap<Long, Booking> = mutableMapOf()
    private var sequence: Long = 1

    override fun save(booking: Booking): Booking =
        Booking(
            userId = booking.userId,
            seatId = booking.seatId,
            bookingStatus = booking.bookingStatus,
            createdAt = booking.createdAt,
            id = sequence++,
        ).also {
            store[it.id] = it
        }
}
