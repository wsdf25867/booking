package io.concert.booking.application.booking.domain

import io.concert.booking.application.concert.domain.Concert
import io.concert.booking.application.concert.domain.ConcertRepository
import io.concert.booking.application.seat.domain.Seat
import io.concert.booking.application.seat.domain.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class DefaultBookingValidator(
    private val seatRepository: SeatRepository,
    private val concertRepository: ConcertRepository,
    private val clock: Clock,
) : BookingValidator {

    override fun validate(booking: Booking) {
        val seat = getSeat(booking)
        val concert = getConcert(seat)

        check(seat.isEmpty()) { "이미 선택된 좌석 입니다." }
        check(concert.isBookableAt(LocalDateTime.now(clock))) { "예약할 수 없는 콘서트입니다." }
    }

    private fun getSeat(booking: Booking): Seat =
        seatRepository.findByIdOrNull(booking.seatId) ?: throw EntityNotFoundException("좌석정보 없음 id: ${booking.seatId}")

    private fun getConcert(seat: Seat): Concert =
        concertRepository.findByIdOrNull(seat.concertId) ?: throw EntityNotFoundException("콘서트정보 없음 id: ${seat.concertId}")

}
