package io.ryan.booking.domain.booking.application

import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingValidator
import io.ryan.booking.domain.booking.dto.BookingCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val bookingValidators: List<BookingValidator>
) {

    @Transactional
    fun create(request: BookingCreateRequest): Long {
        val booking = Booking(userId = request.userId, seatId = request.seatId, price = request.price)
        bookingValidators.forEach { it.validate(booking) }
        booking.heldTemporarily()

        return bookingRepository.save(booking).id
    }
}
