package io.concert.booking.domain.booking

interface BookingRepository {

    fun save(booking: Booking): Booking
    fun findById(id: Long): Booking?
    fun findByIdWithLock(id: Long): Booking?
}
