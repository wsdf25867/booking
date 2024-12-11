package io.ryan.booking.domain.concert.application

import io.ryan.booking.domain.concert.domain.Seat
import io.ryan.booking.domain.concert.domain.SeatRepository
import io.ryan.booking.domain.concert.domain.SeatStatus
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SeatQueryService(
    private val seatRepository: SeatRepository
) {

    fun get(seatId: Long): Seat =
        seatRepository.findByIdOrNull(seatId) ?: throw EntityNotFoundException("없는 좌석 정보 id: $seatId")

    fun getAllEmpty(concertId: Long): List<Seat> =
        seatRepository.findAllByConcertIdAndStatus(concertId, SeatStatus.EMPTY)

}