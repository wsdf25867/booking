package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.seat.domain.Seat
import io.ryan.booking.domain.seat.domain.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BookingSeatValidator(
    private val seatRepository: SeatRepository
): BookingValidator {
    override fun validate(booking: Booking) {
        val seat = getSeat(booking)

        check(seat.isEmpty()) { "이미 선택된 좌석입니다. seatId: ${seat.id}" }
    }

    private fun getSeat(booking: Booking): Seat {
        return seatRepository.findByIdOrNull(booking.seatId) ?: throw EntityNotFoundException()
    }
}
