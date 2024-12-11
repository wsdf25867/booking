package io.ryan.booking.domain.concert.application

import io.ryan.booking.domain.concert.dto.SeatResult
import io.ryan.booking.domain.concert.domain.Seat
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
