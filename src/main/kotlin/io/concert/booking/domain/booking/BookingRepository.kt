package io.concert.booking.domain.booking

import java.time.LocalDateTime

interface BookingRepository {

    fun save(booking: Booking): Booking
    fun findById(id: Long): Booking?
    fun findByIdWithLock(id: Long): Booking?
    fun findAllByStatusAndCreatedAtBefore(status: BookingStatus, createdAt: LocalDateTime): List<Booking>
}
