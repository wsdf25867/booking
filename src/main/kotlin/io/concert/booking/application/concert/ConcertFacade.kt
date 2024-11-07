package io.concert.booking.application.concert

import io.concert.booking.application.concert.dto.ConcertResult
import io.concert.booking.application.concert.dto.ConcertWithSeatsResult
import io.concert.booking.domain.concert.ConcertService
import io.concert.booking.domain.seat.SeatService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ConcertFacade(
    private val concertService: ConcertService,
    private val seatService: SeatService,
) {

    @Transactional(readOnly = true)
    fun getBookable(date: LocalDateTime): List<ConcertResult> =
        concertService.getBookable(date)
            .map(ConcertResult::from)

    @Transactional(readOnly = true)
    fun getBookableWithSeats(concertId: Long): ConcertWithSeatsResult {
        val concert = concertService.get(concertId)
        val seats = seatService.getBookable(concertId)

        return ConcertWithSeatsResult.of(concert, seats)
    }
}
