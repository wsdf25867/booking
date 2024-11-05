package io.concert.booking.application.booking

import io.concert.booking.application.booking.dto.BookingResult
import io.concert.booking.domain.booking.BookingService
import io.concert.booking.domain.concert.ConcertService
import io.concert.booking.domain.queue.TokenService
import io.concert.booking.domain.seat.SeatService
import io.concert.booking.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional
class BookingFacade(
    private val seatService: SeatService,
    private val bookingService: BookingService,
    private val concertService: ConcertService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {

    fun bookSeat(seatId: Long, uuid: UUID): BookingResult {
        val token = tokenService.getByUuid(uuid)
        check(token.isPass()) {
            "아직 대기중인 토큰 입니다"
        }

        val concert = concertService.get(token.concertId)
        check(concert.isBookableAt(LocalDateTime.now())) {
            "예약할 수 없는 콘서트"
        }

        val user = userService.get(token.userId)
        val seat = seatService.get(seatId)

        val booking = bookingService.create(user, seat)
        return BookingResult.from(booking)
    }

}
