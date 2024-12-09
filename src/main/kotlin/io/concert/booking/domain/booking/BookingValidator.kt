package io.concert.booking.domain.booking

import io.concert.booking.domain.concert.ConcertRepository
import io.concert.booking.domain.seat.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

@Component
class BookingValidator(
    private val seatRepository: SeatRepository,
    private val concertRepository: ConcertRepository,
    private val clock: Clock,
) {

    fun validate(seatId: Long, price: BigDecimal) {
        val seat = seatRepository.findById(seatId) ?: throw EntityNotFoundException("존재하지 않는 좌석 id: $seatId")
        val concert = concertRepository.findById(seat.concertId) ?: throw EntityNotFoundException("존재하지 않는 콘서트 id: ${seat.concertId}")

        check(seat.isEmpty()) { "이미 선택된 좌석 입니다." }
        check(concert.isBookableAt(LocalDateTime.now(clock))) { "예약할 수 없는 콘서트입니다." }
    }
}
