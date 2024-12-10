package io.ryan.booking.domain.booking.service

import io.ryan.booking.domain.booking.service.dto.BookingCreateCommand
import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingValidator
import io.ryan.booking.domain.queue.domain.TokenRepository
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
        val token = tokenRepository.findByUuid(command.uuid) ?: throw EntityNotFoundException("존재하지 않는 토큰 uuid: ${command.uuid}")
        val booking = Booking(userId = token.userId, seatId = command.seatId, price = command.price)
        bookingValidator.validate(booking)
        booking.heldTemporarily()

        return bookingRepository.save(booking).id
    }
}
