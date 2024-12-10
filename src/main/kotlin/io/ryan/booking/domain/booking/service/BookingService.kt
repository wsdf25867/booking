package io.ryan.booking.domain.booking.service

import io.ryan.booking.domain.booking.domain.Booking
import io.ryan.booking.domain.booking.domain.BookingRepository
import io.ryan.booking.domain.booking.domain.BookingValidator
import io.ryan.booking.domain.booking.dto.BookingCreateRequest
import io.ryan.booking.domain.queue.domain.TokenRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookingService(
    private val tokenRepository: TokenRepository,
    private val bookingRepository: BookingRepository,
    private val bookingValidators: List<BookingValidator>
) {

    @Transactional
    fun create(uuid: UUID, request: BookingCreateRequest): Long {
        val token = tokenRepository.findByUuid(uuid) ?: throw EntityNotFoundException("존재하지 않는 토큰 uuid: $uuid")
        val booking = Booking(userId = token.userId, seatId = request.seatId, price = request.price)
        bookingValidators.forEach {
            it.validate(booking)
        }
        booking.heldTemporarily()

        return bookingRepository.save(booking).id
    }
}
