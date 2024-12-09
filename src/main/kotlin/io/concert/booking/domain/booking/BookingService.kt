package io.concert.booking.domain.booking

import io.concert.booking.application.BookingCreateCommand
import io.concert.booking.domain.queue.TokenRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(
    private val tokenRepository: TokenRepository,
    private val bookingRepository: BookingRepository,
    private val bookingValidator: BookingValidator
) {

    @Transactional
    fun create(command: BookingCreateCommand): Long {
        val token = tokenRepository.findByUuid(command.uuid) ?: throw EntityNotFoundException()
        val booking = Booking(userId = token.userId, seatId = command.seatId, price = command.price)
        booking.validate(bookingValidator)

        return bookingRepository.save(booking).id
    }
}
