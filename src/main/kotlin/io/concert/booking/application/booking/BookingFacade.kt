package io.concert.booking.application.booking

import io.concert.booking.application.booking.dto.BookingResult
import io.concert.booking.domain.booking.BookingService
import io.concert.booking.domain.booking.BookingValidator
import io.concert.booking.domain.concert.ConcertService
import io.concert.booking.domain.queue.TokenService
import io.concert.booking.domain.seat.SeatService
import io.concert.booking.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookingFacade(
    private val seatService: SeatService,
    private val bookingService: BookingService,
    private val concertService: ConcertService,
    private val tokenService: TokenService,
    private val userService: UserService,
    private val bookingValidator: BookingValidator,
) {

    @Transactional
    fun bookSeat(seatId: Long, uuid: UUID): BookingResult {
        val token = tokenService.getByUuid(uuid)
        val concert = concertService.get(token.concertId)

        bookingValidator.validate(token, concert)

        val user = userService.get(token.userId)
        val seat = seatService.get(seatId)

        return bookingService.create(user, seat)
            .let { BookingResult.from(it) }
    }

}
