package io.concert.booking.application.seat

import io.concert.booking.application.seat.dto.SeatResult
import io.concert.booking.domain.seat.Seat
import io.concert.booking.domain.seat.SeatService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SeatFacade(
    private val seatService: SeatService
) {

    @Transactional(readOnly = true)
    fun getBookable(concertId: Long): List<SeatResult> {
        val seats: List<Seat> = seatService.getBookable(concertId)
        return seats.map(SeatResult::from)
    }
}
