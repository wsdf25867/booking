package io.concert.booking.domain.booking

interface BookingRepository {

    fun save(booking: Booking): Booking
}
