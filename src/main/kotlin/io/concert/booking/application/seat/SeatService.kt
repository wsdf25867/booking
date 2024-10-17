package io.concert.booking.application.seat

import io.concert.booking.application.seat.dto.SeatBookableDto
import io.concert.booking.application.seat.dto.SeatDto
import io.concert.booking.domain.queue.TokenRepository
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatRepository
import io.concert.booking.domain.seat.SeatStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SeatService(
    private val seatRepository: SeatRepository,
    private val tokenRepository: TokenRepository,
) {

    @Transactional(readOnly = true)
    fun findBookable(dto: SeatBookableDto): List<SeatDto> {
        val token = requireNotNull(tokenRepository.findByToken(dto.token)) { "토큰 정보가 없습니다." }
        check(token.isPass()) { "올바르지 않은 접근" }

        val seats: List<Seat> = seatRepository.findAllByConcertIdAndStatus(dto.concertId, SeatStatus.EMPTY)
        return seats.map(SeatDto::from)
    }
}
