package io.ryan.booking.domain.booking.domain

import io.ryan.booking.domain.concert.domain.Concert
import io.ryan.booking.domain.concert.domain.ConcertRepository
import io.ryan.booking.domain.seat.domain.Seat
import io.ryan.booking.domain.seat.domain.SeatRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class BookingConcertValidator(
    private val seatRepository: SeatRepository,
    private val concertRepository: ConcertRepository,
    private val clock: Clock,
) : BookingValidator {

    override fun validate(booking: Booking) {
        val seat = getSeat(booking)
        val concert = getConcert(seat)

        check(concert.isBookableAt(LocalDateTime.now(clock))) { "예약할 수 없는 콘서트입니다." }
    }

    private fun getSeat(booking: Booking): Seat =
        seatRepository.findByIdOrNull(booking.seatId) ?: throw EntityNotFoundException("좌석정보 없음 id: ${booking.seatId}")

    private fun getConcert(seat: Seat): Concert =
        concertRepository.findByIdOrNull(seat.concertId) ?: throw EntityNotFoundException("콘서트정보 없음 id: ${seat.concertId}")

}
