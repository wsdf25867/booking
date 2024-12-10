package io.ryan.booking.domain.seat.service

import io.ryan.booking.domain.seat.dto.SeatResult
import io.ryan.booking.domain.seat.domain.Seat
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SeatService(
    private val seatService: SeatQueryService
) {

    @Transactional(readOnly = true)
    fun getBookable(concertId: Long): List<SeatResult> {
        val seats: List<Seat> = seatService.getAllEmpty(concertId)
        return seats.map(SeatResult::from)
    }
}
