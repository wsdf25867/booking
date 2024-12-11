package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.Seat
import io.ryan.booking.domain.concert.domain.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BookingSeatValidator(
    private val seatRepository: SeatRepository
): BookingValidator {
    override fun validate(booking: Booking) {

    }

}
